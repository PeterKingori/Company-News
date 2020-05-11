import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
import models.Department;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        Sql2oDepartmentDao departmentDao;
        Sql2oUserDao userDao;
        Sql2oNewsDao newsDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/company_news.db;INIT=RUNSCRIPT from " +
                "'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();

        post("/departments/new", "application/json", (request, response) -> {
            Department department = gson.fromJson(request.body(), Department.class);
            departmentDao.add(department);
            response.status(201);
            response.type("application/json");
            return gson.toJson(department);
        });

        post("/news/new", "application/json", (request, response) -> {
            News news = gson.fromJson(request.body(), News.class);
            newsDao.add(news);
            response.status(201);
            response.type("application/json");
            return gson.toJson(news);
        });

        post("/users/new", "application/json", (request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
            userDao.add(user);
            response.status(201);
            response.type("application/json");
            return gson.toJson(user);
        });

        get("/departments", "application/json", (request, response) -> {
            response.type("application/json");
            return gson.toJson(departmentDao.getAll());
        });

        get("/users", "application/json", (request, response) -> {
            response.type("application/json");
            return gson.toJson(userDao.getAllUsers());
        });

        get("/news", "application/json", (request, response) -> {
            response.type("application/json");
            return gson.toJson(newsDao.getAllNews());
        });

        get("/users/:id", "application/json", (request, response) -> {
            response.type("application/json");
            int userId = Integer.parseInt(request.params("id"));
            response.type("application/json");
            return gson.toJson(userDao.findById(userId));
        });

        get("/departments/:id", "application/json", (request, response) -> {
            response.type("application/json");
            int departmentId = Integer.parseInt(request.params("id"));
            response.type("application/json");
            return gson.toJson(departmentDao.findById(departmentId));
        });



    }
}
