package top.lishaojun.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lishaojun.crm.domain.Permission;
import top.lishaojun.crm.page.PageResult;
import top.lishaojun.crm.query.PermissionQueryObject;
import top.lishaojun.crm.service.IPermissionService;

import java.util.List;

@Controller
public class PermissionController {

    @Autowired
    private IPermissionService service;

    @RequestMapping("/permission_list")
    @ResponseBody
    public PageResult list(PermissionQueryObject qo) {

        PageResult pageResult = service.selectByCondition(qo);

        return pageResult;
    }

    @RequestMapping("/permission_queryByRid")
    @ResponseBody
    public List<Permission> queryByRid(long rid) {

        List<Permission> permissions = service.queryByRid(rid);

        return permissions;
    }

}
