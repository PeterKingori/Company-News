import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
import exceptions.ApiException;
import models.Department;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        Sql2oDepartmentDao departmentDao;
        Sql2oUserDao userDao;
        Sql2oNewsDao newsDao;
        Connection conn;
        Gson gson = new Gson();

        //String connectionString = "jdbc:h2:~/company_news.db;INIT=RUNSCRIPT from " +
          //      "'classpath:db/create.sql'";
        String connectionString = "jdbc:postgresql://localhost:5432/company_news";
        Sql2o sql2o = new Sql2o(connectionString, null, null);

        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();

        post("/departments/new", "application/json", (request, response) -> {
            Department department = gson.fromJson(request.body(), Department.class);
            departmentDao.add(department);
            response.status(201);
            return gson.toJson(department);
        });

        post("/departments/:departmentId/news/new", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            News news = gson.fromJson(request.body(), News.class);
            news.setDepartmentId(departmentId);
            newsDao.add(news);
            response.status(201);
            return gson.toJson(news);
        });

        post("/departments/:departmentId/users/new", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            User user = gson.fromJson(request.body(), User.class);
            user.setDepartmentId(departmentId);
            userDao.add(user);
            response.status(201);
            return gson.toJson(user);
        });

        get("/departments", "application/json", (request, response) -> {
            return gson.toJson(departmentDao.getAll());
        });

        get("/users", "application/json", (request, response) -> {
            return gson.toJson(userDao.getAllUsers());
        });

        //get a specific user and show his/her details
        get("/users/:id", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            return gson.toJson(userDao.findById(userId));
        });

        get("/news", "application/json", (request, response) -> {
            return gson.toJson(newsDao.getAllNews());
        });

        //get all users from a specific department
        get("/departments/:departmentId/users", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentId <= 0 || departmentToFind == null) {
                return "{\"I'm sorry but that department does not exist in the company database" +
                        ".\"}";
            }
            else {
                return gson.toJson(userDao.getAllUsersByDepartment(departmentId));
            }
        });

        //get all news from a specific department
        get("/departments/:departmentId/news", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" " +
                        "exists", request.params("id")));
            }
            return gson.toJson(newsDao.getAllNewsByDepartment(departmentId));
        });

        //get a specific department and show the details
        get("/departments/:id", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" " +
                        "exists", request.params("id")));
            }
            return gson.toJson(departmentDao.findById(departmentId));
        });

        exception(ApiException.class, (exception, request, response) -> {
            ApiException err = (ApiException) exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            response.type("application/json");
            response.status(err.getStatusCode());
            response.body(gson.toJson(jsonMap));
        });

        //Filters
        after((request, response) -> {
            response.type("application/json");
        });


    }

}
