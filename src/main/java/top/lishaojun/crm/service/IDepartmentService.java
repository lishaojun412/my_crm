package top.lishaojun.crm.service;

import top.lishaojun.crm.domain.Department;
import top.lishaojun.crm.domain.Employee;
import top.lishaojun.crm.page.PageResult;
import top.lishaojun.crm.query.QueryObject;

import java.util.List;

public interface IDepartmentService {

    int save(Department department);

    int update(Department department);

    int delete(Long id);

    Department get(Long id);

    List<Department> selectAll();

    PageResult selectByCondition(QueryObject queryObject);


}
