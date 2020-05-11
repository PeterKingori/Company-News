package dao;

import models.User;

import java.util.List;

public interface UserDao {
    //create
    void add(User user);
//    void addUserToDepartment(User user, Department department);

    //read
    List<User> getAllUsers();
    User findById(int id);
    List<User> getAllUsersByDepartment(int departmentId);

    //update

    //delete
    void deleteById(int id);
}
