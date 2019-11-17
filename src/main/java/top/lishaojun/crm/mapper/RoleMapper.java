package top.lishaojun.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import top.lishaojun.crm.domain.Employee;
import top.lishaojun.crm.domain.Role;
import top.lishaojun.crm.query.QueryObject;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    long selectByCondtionCount(QueryObject queryObject);

    List<Role> selectByConditon(QueryObject queryObject);

    int insertRelation(@Param("Rid")long rid,@Param("Pid")long pid);

    int deleteRelation(Long rid);
}