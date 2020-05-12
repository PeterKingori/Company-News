package dao;

import models.Department;
import models.News;
import models.User;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

public class Sql2oUserDaoTest {
    private static Sql2oUserDao userDao;
    private static Sql2oDepartmentDao departmentDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/company_news_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        userDao = new Sql2oUserDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        userDao.clearAllUsers();
        departmentDao.clearAllDepartments();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
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
