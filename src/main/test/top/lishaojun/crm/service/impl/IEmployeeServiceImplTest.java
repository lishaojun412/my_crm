package top.lishaojun.crm.service.impl;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.lishaojun.crm.domain.Employee;
import top.lishaojun.crm.service.IEmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-mvc.xml")
public class IEmployeeServiceImplTest extends TestCase {

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void testSave() {

        Employee employee = new Employee();
        employee.setRealname("zhangsan");
        employee.setEmail("123");
        employee.setAdmin(true);

        employeeService.save(employee);

    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testGet() {
    }

    @Test
    public void testSelectAll() {
    }

    @Test
    public void testSelectByCondition() {
    }
}