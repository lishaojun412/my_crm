package top.lishaojun.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lishaojun.crm.domain.Menu;
import top.lishaojun.crm.service.IMenuService;
import top.lishaojun.crm.util.UserContext;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private IMenuService service;

    @RequestMapping("/menu")
    @ResponseBody
    public List<Menu> queryMenu(HttpSession session) {
        Object attribute = session.getAttribute(UserContext.MENU_IN_SESSION);
        if (attribute != null) {
            return (List<Menu>) attribute;
        } else {
            List<Menu> menus = service.queryMenu();
            return menus;
        }
    }
}
