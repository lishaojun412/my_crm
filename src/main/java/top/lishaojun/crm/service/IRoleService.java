package top.lishaojun.crm.service;

import top.lishaojun.crm.domain.Role;
import top.lishaojun.crm.page.PageResult;
import top.lishaojun.crm.query.QueryObject;

import java.util.List;

public interface IRoleService {


    int save(Role role);

    int update(Role role);

    int delete(Long id);

    Role get(Long id);

    List<Role> selectAll();

    PageResult selectByCondition(QueryObject queryObject);

}
