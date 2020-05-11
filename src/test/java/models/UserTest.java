package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void initializeCorrectly(){
        User testUser = setupUser();
        assertTrue(testUser instanceof User);
    }

    @Test
    public void getterMethodsWorkCorrectly_String() {
        User testUser = setupUser();
        assertEquals("Joan", testUser.getName());
        assertEquals("Finance Assistant", testUser.getPosition());
        assertEquals("Financial Analysis", testUser.getRole());
        assertEquals("Finance", testUser.getDepartment());
        assertEquals(1,testUser.getDepartmentId());
    }

    @Test
    public void setName() {
        User testUser = setupUser();
        testUser.setName("Peter");
        assertNotEquals("Joan", testUser.getName());
    }

    @Test
    public void setPosition() {
        User testUser = setupUser();
        testUser.setPosition("CEO");
        assertNotEquals("Finance Assistant", testUser.getPosition());
    }


    @Test
    public void setRole() {
        User testUser = setupUser();
        testUser.setRole("Administrative work");
        assertNotEquals("Financial Analysis", testUser.getRole());
    }

    @Test
    public void setDepartment() {
        User testUser = setupUser();
        testUser.setDepartment("Administration");
        assertNotEquals("Finance", testUser.getDepartment());
    }

    @Test
    public void setDepartmentId() {
        User testUser = setupUser();
        testUser.setDepartmentId(2);
        assertNotEquals(1, testUser.getDepartmentId());
    }



    //helper method
    private User setupUser() {
        return new User("Joan", "Finance Assistant", "Financial Analysis", "Finance", 1);
    }
}
