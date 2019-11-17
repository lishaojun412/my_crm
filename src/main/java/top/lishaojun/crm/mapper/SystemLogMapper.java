package top.lishaojun.crm.mapper;

import java.util.List;
import top.lishaojun.crm.domain.SystemLog;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemLog record);

    SystemLog selectByPrimaryKey(Long id);

    List<SystemLog> selectAll();

    int updateByPrimaryKey(SystemLog record);
}