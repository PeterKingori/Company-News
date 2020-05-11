package dao;

import models.Department;

import java.util.List;

public interface DepartmentDao {
    //create
    void add(Department department);

    //read
    List<Department> getAll();
    Department findById(int id);

    //update

    //delete
}
