package top.lishaojun.crm.mapper;

import java.util.List;
import top.lishaojun.crm.domain.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    List<Menu> selectRoot();

    List<Menu> selectByParentId(long id);
}