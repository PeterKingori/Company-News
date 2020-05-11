package dao;

import models.Department;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {
    private static Connection conn;
    private static Sql2oDepartmentDao departmentDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/company_news_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        departmentDao.clearAllDepartments();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void addingDepartmentSetsId() throws Exception {
        Department testDepartment = setupDepartment();
        assertNotEquals(0, testDepartment.getId());
    }

    @Test
    public void addedDepartmentsAreReturnedFromGetAll() throws Exception {
        Department testDepartment = setupDepartment();
        assertEquals(1, departmentDao.getAll().size());
    }

    @Test
    public void noRestaurantsReturnsEmptyList() throws Exception {
        assertEquals(0, departmentDao.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectDepartment() throws Exception {
        Department testDepartment = setupDepartment();
        Department otherDepartment = new Department("HR", "Employee matters", 3);
        assertEquals(testDepartment, departmentDao.findById(testDepartment.getId()));
    }

    //helper methods
    public Department setupDepartment() {
        Department department = new Department("Finance", "Company financial matters", 5);
        departmentDao.add(department);
        return department;
    }

}
