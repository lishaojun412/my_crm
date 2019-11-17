package top.lishaojun.crm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.lishaojun.crm.domain.Employee;
import top.lishaojun.crm.domain.Menu;
import top.lishaojun.crm.domain.Permission;
import top.lishaojun.crm.service.IPermissionService;

import java.util.List;

@Component
public class PermissionUtils {

    private static IPermissionService permissionService;

    public static boolean checkPermission(String function) {

        Employee employee = (Employee) UserContext.getLocal().getSession().getAttribute(UserContext.USER_IN_SESSION);


        if (employee.getAdmin()) {
            return true;
        }

        Permission permission = permissionService.queryByResource(function);


        if (permission != null) {
            List<Permission> permissions = permissionService.queryPermissionByEid(employee.getId());
            for (Permission p : permissions) {
                if (p.getResource().equals(function) || p.getResource().equals(function.split(":")[0] + ":ALL")) {
                    return true;
                }
            }


            return false;
        }


        // permission为null，不需要权限控制
        return true;

    }

    public static void toUserMenu(List<Menu> menus) {
        for (int i = menus.size() - 1; i >= 0; i--) {
            Menu menu = menus.get(i);

            if (checkPermission(menu.getFunction())) {
                if (menu.getChildren() != null && menu.getChildren().size() > 0) {
                    toUserMenu(menu.getChildren());
                }
            } else {
                menus.remove(i);
            }
        }

    }

    @Autowired
    public void setPermissionService(IPermissionService permissionService) {
        PermissionUtils.permissionService = permissionService;
    }
}
