package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void initializeCorrectly(){
        News testNews = setupNews();
        assertTrue(testNews instanceof News);
    }

    @Test
    public void getterMethodsWorkCorrectly_String() {
        News testNews = setupNews();
        assertEquals("Company lunch", testNews.getTitle());
        assertEquals("There will be company lunch offered for free.", testNews.getContent());
        assertEquals("General", testNews.getType());
        assertEquals("Mark", testNews.getAuthor());
        assertEquals(1, testNews.getDepartmentId());
    }

    @Test
    public void setTitle() {
        News testNews = setupNews();
        testNews.setTitle("Salary raise");
        assertNotEquals("Company lunch", testNews.getTitle());
    }

    @Test
    public void setContent() {
        News testNews = setupNews();
        testNews.setContent("CEO");
        assertNotEquals("A quarterly 5% raise", testNews.getContent());
    }


    @Test
    public void setType() {
        News testNews = setupNews();
        testNews.setType("General");
        assertNotEquals("Finance", testNews.getType());
    }

    @Test
    public void setAuthor() {
        News testNews = setupNews();
        testNews.setAuthor("Joan");
        assertNotEquals("Mark", testNews.getAuthor());
    }



    //helper method
    private News setupNews() {
        return new News("Company lunch", "There will be company lunch offered for free.",
                "General", "Mark", 1);
    }
}