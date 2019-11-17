package top.lishaojun.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lishaojun.crm.domain.Department;
import top.lishaojun.crm.service.IDepartmentService;

import java.util.List;

@Controller
public class DepartmentController {


    @Autowired
    IDepartmentService departmentService;

    @RequestMapping("/department_queryForEmp")
    @ResponseBody
    public List queryForEmp(){

        List<Department> departments = departmentService.selectAll();

        return departments;
    }

}


