package top.lishaojun.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lishaojun.crm.domain.Menu;
import top.lishaojun.crm.mapper.MenuMapper;
import top.lishaojun.crm.service.IMenuService;

import java.util.List;

@Service
public class IMenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper dao;

    @Override
    public List<Menu> queryMenu() {
        List<Menu> menus = dao.selectRoot();
        return menus;
    }
}
