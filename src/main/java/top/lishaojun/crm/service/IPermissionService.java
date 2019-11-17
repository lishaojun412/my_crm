package top.lishaojun.crm.service;

import top.lishaojun.crm.domain.Permission;
import top.lishaojun.crm.page.PageResult;
import top.lishaojun.crm.query.QueryObject;

import java.util.List;

public interface IPermissionService {


    int save(Permission permission);

    int update(Permission permission);

    int delete(Long id);

    Permission get(Long id);

    List<Permission> selectAll();

    PageResult selectByCondition(QueryObject queryObject);

    List<Permission> queryByRid(long rid);

    Permission queryByResource(String function);

    List<Permission> queryPermissionByEid(Long id);
}
