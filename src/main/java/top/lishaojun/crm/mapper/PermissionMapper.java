package top.lishaojun.crm.mapper;

import java.util.List;
import top.lishaojun.crm.domain.Permission;
import top.lishaojun.crm.query.QueryObject;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    long selectByCondtionCount(QueryObject queryObject);

    List<Permission> selectByConditon(QueryObject queryObject);

    List<Permission>  queryByRid(long rid);

    Permission queryByResource(String function);

    List<Permission> queryPermissionByEid(Long id);
}