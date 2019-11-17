package top.lishaojun.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lishaojun.crm.domain.Permission;
import top.lishaojun.crm.mapper.PermissionMapper;
import top.lishaojun.crm.page.PageResult;
import top.lishaojun.crm.query.QueryObject;
import top.lishaojun.crm.service.IPermissionService;

import java.util.List;

@Service
public class IPermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper dao;

    @Override
    public int save(Permission permission) {
        int insert = dao.insert(permission);
        return insert;
    }

    @Override
    public int update(Permission permission) {
        int update = dao.updateByPrimaryKey(permission);
        return update;
    }

    @Override
    public int delete(Long id) {
        int i = dao.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public Permission get(Long id) {
        return null;
    }

    @Override
    public List<Permission> selectAll() {
        List<Permission> permissions = dao.selectAll();

        return permissions;
    }

    @Override
    public PageResult selectByCondition(QueryObject queryObject) {
        long count = dao.selectByCondtionCount(queryObject);

        if (count > 0) {
            List<Permission> permissions = dao.selectByConditon(queryObject);

            return new PageResult(count, permissions);
        }
        return PageResult.EMPTY;
    }

    @Override
    public List<Permission> queryByRid(long rid) {

        List<Permission> permissions = dao.queryByRid(rid);

        return permissions;
    }

    @Override
    public Permission queryByResource(String function) {
       Permission permission = dao.queryByResource(function);
       return permission;
    }

    @Override
    public List<Permission> queryPermissionByEid(Long id) {

       return dao.queryPermissionByEid(id);
    }

}
