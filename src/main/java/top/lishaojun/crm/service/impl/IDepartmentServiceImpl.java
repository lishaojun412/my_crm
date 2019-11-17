package top.lishaojun.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lishaojun.crm.domain.Department;
import top.lishaojun.crm.domain.Employee;
import top.lishaojun.crm.mapper.DepartmentMapper;
import top.lishaojun.crm.mapper.EmployeeMapper;
import top.lishaojun.crm.page.PageResult;
import top.lishaojun.crm.query.QueryObject;
import top.lishaojun.crm.service.IDepartmentService;

import java.util.List;

@Service
public class IDepartmentServiceImpl implements IDepartmentService {

    @Autowired
    DepartmentMapper dao;

    @Override
    public int save(Department department) {
        return dao.insert(department);
    }

    @Override
    public int update(Department department) {
        return dao.updateByPrimaryKey(department);
    }

    @Override
    public int delete(Long id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public Department get(Long id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> selectAll() {
        return dao.selectAll();
    }

    @Override
    public PageResult selectByCondition(QueryObject queryObject) {
        return null;
    }


}
