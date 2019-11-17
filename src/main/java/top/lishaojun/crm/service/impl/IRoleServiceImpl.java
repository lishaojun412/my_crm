package top.lishaojun.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lishaojun.crm.domain.Employee;
import top.lishaojun.crm.domain.Permission;
import top.lishaojun.crm.domain.Role;
import top.lishaojun.crm.mapper.RoleMapper;
import top.lishaojun.crm.page.PageResult;
import top.lishaojun.crm.query.QueryObject;
import top.lishaojun.crm.service.IRoleService;

import java.util.List;

@Service
public class IRoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper dao;

    @Override
    public int save(Role role) {
        int insert = dao.insert(role);

        for (Permission permission : role.getPermissions()) {
            int i = dao.insertRelation(role.getId(), permission.getId());
        }

        return insert;
    }

    @Override
    public int update(Role role) {

        dao.deleteRelation(role.getId());

        for (Permission permission : role.getPermissions()) {
            int i = dao.insertRelation(role.getId(), permission.getId());
        }

        int update = dao.updateByPrimaryKey(role);

        return update;
    }

    @Override
    public int delete(Long id) {
        int delete = dao.deleteRelation(id);

        int i = dao.deleteByPrimaryKey(id);

        return i;
    }

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public List<Role> selectAll() {
        List<Role> roles = dao.selectAll();


        return roles;
    }

    @Override
    public PageResult selectByCondition(QueryObject queryObject) {
        long count = dao.selectByCondtionCount(queryObject);

        if (count > 0) {
            List<Role> roles = dao.selectByConditon(queryObject);

            return new PageResult(count, roles);
        }
        return PageResult.EMPTY;
    }


}
