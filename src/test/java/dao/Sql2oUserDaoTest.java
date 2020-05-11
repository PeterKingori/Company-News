package dao;

import models.Department;
import models.News;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

public class Sql2oUserDaoTest {
    private Sql2oUserDao userDao;
    private Sql2oDepartmentDao departmentDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingUserSetsId() throws Exception {
        User testUser = setupUser();
        assertEquals(1, testUser.getId());
    }

    @Test
    public void getAll() throws Exception {
        User firstUser = setupUser();
        User secondUser = setupUser();
        assertEquals(2, userDao.getAllUsers().size());
    }

    @Test
    public void getAllUsersByDepartment() throws Exception {
        Department testDepartment = setupDepartment();
        Department otherDepartment = setupDepartment();
        User firstUser = setupUserForDepartment(testDepartment);
        User secondUser = setupUserForDepartment(testDepartment);
        User thirdUser = setupUserForDepartment(otherDepartment);
        assertEquals(2, userDao.getAllUsersByDepartment(testDepartment.getId()).size());
    }

    @Test
    public void deleteById() throws Exception {
        User firstUser = setupUser();
        User secondUser = setupUser();
        assertEquals(2, userDao.getAllUsers().size());
        userDao.deleteById(firstUser.getId());
        assertEquals(1, userDao.getAllUsers().size());
    }

    //helper methods
    public User setupUser() {
        User user = new User("Mark", "CFO", "Company wide financials", "Finance", 2);
        userDao.add(user);
        return user;
    }

    public User setupUserForDepartment(Department department) {
        User user = new User("Joan", "CEO", "Company main manager", "CEO", department.getId());
        userDao.add(user);
        return user;
    }

    public Department setupDepartment() {
        Department department = new Department("HR", "Employee matters", 5);
        departmentDao.add(department);
        return department;
    }

}