package top.lishaojun.crm.service;

import top.lishaojun.crm.domain.Department;
import top.lishaojun.crm.domain.SystemLog;
import top.lishaojun.crm.page.PageResult;
import top.lishaojun.crm.query.QueryObject;

import java.util.List;

public interface ISystemLogService {

    int save(SystemLog systemLog);

}
