package top.lishaojun.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lishaojun.crm.domain.Employee;
import top.lishaojun.crm.domain.Role;
import top.lishaojun.crm.mapper.EmployeeMapper;
import top.lishaojun.crm.page.PageResult;
import top.lishaojun.crm.query.QueryObject;
import top.lishaojun.crm.service.IEmployeeService;

import java.util.List;

@Service
public class IEmployeeServiceImpl implements IEmployeeService {

    @Autowired
    EmployeeMapper dao;

    @Override
    public int save(Employee employee) {

        int insert = dao.insert(employee);

        for (Role role : employee.getRoles()) {

            int i = dao.insertRelation(employee.getId(), role.getId());

        }

        return insert;
    }

    @Override
    public int update(Employee employee) {

        int i = dao.deleteRelation(employee.getId());

        for (Role role : employee.getRoles()) {

            int insertRelation = dao.insertRelation(employee.getId(), role.getId());
        }

        return dao.updateByPrimaryKey(employee);
    }

    @Override
    public int delete(Long id) {
        int i = dao.deleteRelation(id);

        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public Employee get(Long id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return dao.selectAll();
    }

    @Override
    public PageResult selectByCondition(QueryObject queryObject) {
        long count = dao.selectByCondtionCount(queryObject);

        if (count > 0) {
            List<Employee> employees = dao.selectByConditon(queryObject);

            return new PageResult(count, employees);
        }
        return PageResult.EMPTY;
    }

    @Override
    public Employee getLogin(String username, String password) {
        return dao.getLogin(username, password);
    }

    @Override
    public int updateState(Long id) {
        int update = dao.updateState(id);
        return update;
    }

    @Override
    public List<Long> queryRoleIdByEid(long eid) {

        List<Long> roles = dao.queryRoleIdByEid(eid);

        return roles;


    }
}
