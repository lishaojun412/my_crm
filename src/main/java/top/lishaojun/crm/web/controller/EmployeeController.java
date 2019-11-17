package top.lishaojun.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lishaojun.crm.domain.Employee;
import top.lishaojun.crm.domain.Menu;
import top.lishaojun.crm.domain.ResultData;
import top.lishaojun.crm.page.PageResult;
import top.lishaojun.crm.query.EmployeeQueryObject;
import top.lishaojun.crm.service.IEmployeeService;
import top.lishaojun.crm.service.IMenuService;
import top.lishaojun.crm.util.PermissionUtils;
import top.lishaojun.crm.util.UserContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    @Autowired
    private IMenuService menuService;

    @RequestMapping("/login")
    @ResponseBody
    public ResultData login(String username, String password, HttpServletRequest request) {
        UserContext.setLocal(request);
        ResultData resultData = new ResultData();

        Employee employee = employeeService.getLogin(username, password);

        if (employee != null) {
            request.getSession().setAttribute(UserContext.USER_IN_SESSION, employee);

            // 用户menu
            List<Menu> menus = menuService.queryMenu();
            PermissionUtils.toUserMenu(menus);
            request.getSession().setAttribute(UserContext.MENU_IN_SESSION,menus);

            resultData.setSuccess(true);
            resultData.setMsg("登陆成功");

        } else {
            resultData.setSuccess(false);
            resultData.setMsg("账号或密码有误");

        }

        return resultData;
    }

    @RequestMapping("/employee")
    public String index() {
        return "employee";
    }

    @RequestMapping("/employee_list")
    @ResponseBody
    public PageResult employeeList(EmployeeQueryObject queryObject) {
        return employeeService.selectByCondition(queryObject);
    }


    @RequestMapping("/employee_save")
    @ResponseBody
    public ResultData save(Employee employee) {
        ResultData resultData = new ResultData();
        try {

            employee.setAdmin(false);
            employee.setInputtime(new Date());
            employee.setPassword("666666");
            employee.setState(true);

            int save = employeeService.save(employee);

            if (save > 0) {
                resultData.setSuccess(true);
                resultData.setMsg("保存成功");


            } else {
                resultData.setSuccess(false);
                resultData.setMsg("保存失败");

            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setSuccess(false);
            resultData.setMsg("系统异常，请联系管理员");

        }

        return resultData;

    }


    @RequestMapping("/employee_update")
    @ResponseBody
    public ResultData update(Employee employee) {
        ResultData resultData = new ResultData();
        try {

            int save = employeeService.update(employee);

            if (save > 0) {
                resultData.setSuccess(true);
                resultData.setMsg("修改成功");


            } else {
                resultData.setSuccess(false);
                resultData.setMsg("修改失败");


            }
        } catch (Exception e) {
            e.printStackTrace();

            resultData.setSuccess(false);
            resultData.setMsg("系统异常，请联系管理员");
        }

        return resultData;

    }

    @RequestMapping("/employee_delete")
    @ResponseBody
    public ResultData delete(Long id) {
        ResultData resultData = new ResultData();
        try {

            int update = employeeService.updateState(id);


            if (update > 0) {
                resultData.setSuccess(true);
                resultData.setMsg("离职成功");


            } else {
                resultData.setSuccess(false);
                resultData.setMsg("离职失败");

            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setSuccess(false);
            resultData.setMsg("系统异常，请联系管理员");

        }


        return resultData;

    }

    @RequestMapping("/employee_queryRoleIdByEid")
    @ResponseBody
    public List<Long> queryRoleIdByEid(long eid) {
        return employeeService.queryRoleIdByEid(eid);
    }


}
