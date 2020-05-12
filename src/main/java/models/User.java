package models;

public class User {
    private String name;
    private String position;
    private String role;
    private String department;
    private int id;
    private int departmentId;

    public User(String name, String position, String role, String department, int departmentId) {
        this.name = name;
        this.position = position;
        this.role = role;
        this.department = department;
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (departmentId != user.departmentId) return false;
        if (!name.equals(user.name)) return false;
        if (!position.equals(user.position)) return false;
        if (!role.equals(user.role)) return false;
        return department.equals(user.department);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + id;
        result = 31 * result + departmentId;
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
