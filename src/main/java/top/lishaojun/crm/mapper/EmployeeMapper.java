package top.lishaojun.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import top.lishaojun.crm.domain.Employee;
import top.lishaojun.crm.domain.Role;
import top.lishaojun.crm.page.PageResult;
import top.lishaojun.crm.query.QueryObject;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    long selectByCondtionCount(QueryObject queryObject);

    List<Employee> selectByConditon(QueryObject queryObject);

    Employee getLogin(@Param("username") String username,@Param("password") String password);

    int updateState(Long id);

   List<Long> queryRoleIdByEid(long eid);

    int insertRelation(@Param("Eid")long eid,@Param("Rid")long rid);

    int deleteRelation(Long eid);

}