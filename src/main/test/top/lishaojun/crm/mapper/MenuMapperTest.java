package top.lishaojun.crm.mapper;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.lishaojun.crm.domain.Menu;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-mvc.xml")
public class MenuMapperTest extends TestCase {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testSelectRoot() {
        List<Menu> menus = menuMapper.selectRoot();
        System.out.println(menus);
    }

    @Test
    public void testSelectByParentId() {
        List<Menu> menus = menuMapper.selectByParentId(1);
        System.out.println(menus);
    }
}