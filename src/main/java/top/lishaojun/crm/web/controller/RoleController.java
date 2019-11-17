package top.lishaojun.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lishaojun.crm.domain.Role;
import top.lishaojun.crm.domain.ResultData;
import top.lishaojun.crm.page.PageResult;
import top.lishaojun.crm.query.RoleQueryObject;
import top.lishaojun.crm.service.IRoleService;

import java.util.List;

@Controller
public class RoleController {

    @RequestMapping("/role")
    public String index() {
        return "role";
    }

    @Autowired
    private IRoleService service;

    @RequestMapping("/role_list")
    @ResponseBody
    public PageResult list(RoleQueryObject qo) {

        PageResult pageResult = service.selectByCondition(qo);


        return pageResult;
    }


    @RequestMapping("/role_queryRoleByEmp")
    @ResponseBody
    public List<Role> queryRoleByEmp() {

        List<Role> roles = service.selectAll();


        return roles;
    }

    @RequestMapping("/role_save")
    @ResponseBody
    public ResultData save(Role role) {
        ResultData resultData = new ResultData();
        try {
            int save = service.save(role);
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

    @RequestMapping("/role_update")
    @ResponseBody
    public ResultData update(Role role) {
        ResultData resultData = new ResultData();
        try {
            int update = service.update(role);
            if (update > 0) {
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


    @RequestMapping("/role_delete")
    @ResponseBody
    public ResultData delete(long id) {
        ResultData resultData = new ResultData();
        try {
            int delete = service.delete(id);
            if (delete > 0) {
                resultData.setSuccess(true);
                resultData.setMsg("删除成功");
            } else {
                resultData.setSuccess(false);
                resultData.setMsg("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setSuccess(false);
            resultData.setMsg("系统异常，请联系管理员");
        }
        return resultData;
    }



}
