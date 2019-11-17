package top.lishaojun.crm.service;

import top.lishaojun.crm.domain.Employee;
import top.lishaojun.crm.domain.Role;
import top.lishaojun.crm.page.PageResult;
import top.lishaojun.crm.query.QueryObject;

import java.util.List;

public interface IEmployeeService {

    int save(Employee employee);

    int update(Employee employee);

    int delete(Long id);

    Employee get(Long id);

    List<Employee> selectAll();

    PageResult selectByCondition(QueryObject queryObject);


    Employee getLogin(String username, String password);

    int updateState(Long id);

    List<Long> queryRoleIdByEid(long eid);
}
