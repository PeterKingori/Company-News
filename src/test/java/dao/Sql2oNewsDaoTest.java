package dao;

import models.Department;
import models.News;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.awt.*;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    private static Connection conn;
    private static Sql2oNewsDao newsDao;
    private static Sql2oDepartmentDao departmentDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/company_news_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        newsDao = new Sql2oNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        newsDao.clearAllNews();
        departmentDao.clearAllDepartments();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void addingNewsSetsId() throws Exception {
        News testNews = setupNews();
        assertEquals(1, testNews.getId());
    }

    @Test
    public void getAll() throws Exception {
        News testNews = setupNews();
        News testNews2 = setupNews();
        assertEquals(2, newsDao.getAllNews().size());
    }

    @Test
    public void getAllNewsByDepartment() throws Exception {
        Department firstDepartment = setupDepartment();
        Department secondDepartment = setupDepartment();
        News firstNews = setupNewsForDepartment(firstDepartment);
        News secondNews = setupNewsForDepartment(firstDepartment);
        News otherNews = setupNewsForDepartment(secondDepartment);
        assertEquals(2, newsDao.getAllNewsByDepartment(firstDepartment.getId()).size());
    }

    @Test
    public void deleteById() throws Exception {
        News testNews = setupNews();
        News testNews2 = setupNews();
        assertEquals(2, newsDao.getAllNews().size());
        newsDao.deleteById(testNews.getId());
        assertEquals(1, newsDao.getAllNews().size());
    }


    //helper methods
    public News setupNews() {
        News news = new News("Company lunch", "There will be company lunch offered for free.",
                "General", "Mark", 1);
        newsDao.add(news);
        return news;
    }

    public News setupNewsForDepartment(Department department) {
        News news = new News("Company lunch", "There will be company lunch offered for free.",
                "General", "Mark", department.getId());
        newsDao.add(news);
        return news;
    }

    public Department setupDepartment() {
        Department department = new Department("HR", "Employee matters", 5);
        departmentDao.add(department);
        return department;
    }


}
