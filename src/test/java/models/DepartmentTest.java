package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void initializeCorrectly(){
        Department newDepartment = setupDepartment();
        assertTrue(newDepartment instanceof Department);
    }

    @Test
    public void getterMethodsWorkCorrectly_String_int() {
        Department testDepartment = setupDepartment();
        assertEquals("Finance", testDepartment.getName());
        assertEquals("We inform you on financial matters of the company",
                testDepartment.getDescription());
        assertEquals(5, testDepartment.getTotalemployees());
    }

    @Test
    public void setName() {
        Department testDepartment = setupDepartment();
        testDepartment.setName("HR");
        assertNotEquals("Finance", testDepartment.getName());
    }

    @Test
    public void setDescription() {
        Department testDepartment = setupDepartment();
        testDepartment.setDescription("Employee matters");
        assertNotEquals("We inform you on financial matters of the company",
                testDepartment.getDescription());
    }

    @Test
    public void setTotalEmployees() {
        Department testDepartment = setupDepartment();
        testDepartment.setTotalemployees(10);
        assertNotEquals(5, testDepartment.getTotalemployees());
    }


    //helper method
    private Department setupDepartment() {
        return new Department("Finance", "We inform you on financial matters of the company", 5);
    }
}
