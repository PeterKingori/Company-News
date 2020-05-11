package models;

public class Department {
    private String name;
    private String description;
    private int totalemployees;
    private int id;

    public Department(String name, String description, int totalEmployees) {
        this.name = name;
        this.description = description;
        this.totalemployees = totalEmployees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (totalemployees != that.totalemployees) return false;
        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + totalemployees;
        result = 31 * result + id;
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalemployees() {
        return totalemployees;
    }

    public void setTotalemployees(int totalemployees) {
        this.totalemployees = totalemployees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
