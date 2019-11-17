package top.lishaojun.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lishaojun.crm.domain.SystemLog;
import top.lishaojun.crm.mapper.SystemLogMapper;
import top.lishaojun.crm.service.ISystemLogService;

import java.util.regex.Matcher;

@Service
public class ISystemLogServiceImpl implements ISystemLogService {

    @Autowired
    SystemLogMapper systemLogMapper;

    @Override
    public int save(SystemLog systemLog) {
        return systemLogMapper.insert(systemLog);
    }
}
