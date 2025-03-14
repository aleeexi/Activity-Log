package persistence;

import model.Activities;
import model.Activity;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

// tests from JsonSerializationDemo
public class JsonReaderTest{

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyActivityLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyActivityLog.json");
        try {
            Activities as = reader.read();
            List<Activity> list = as.getActivities();
            List<Activity> completed = as.getCompletedActivities();
            assertEquals(0, list.size());
            assertEquals(0, completed.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderActivityLog() {
        JsonReader reader = new JsonReader("./data/testReaderActivityLog.json");
        try {
            Activities as = reader.read();
            List<Activity> list = as.getActivities();
            List<Activity> completed = as.getCompletedActivities();
            Activity a1 = list.get(0);
            Activity a2 = list.get(1);
            Activity a3 = list.get(2);
            assertEquals(3, list.size());
            assertEquals(2, completed.size());

            assertEquals("Watch T.V.", a1.getName());
            assertEquals("Home", a1.getLocation());
            assertEquals(0, a1.getCost());
            assertFalse(a1.getCategory());
            assertTrue(a1.getInOrOut());
            assertFalse(a1.getCompletion());

            assertEquals("Have dinner", a2.getName());
            assertEquals("Joe Fortes", a2.getLocation());
            assertEquals(50, a2.getCost());
            assertFalse(a2.getCategory());
            assertTrue(a2.getInOrOut());
            assertTrue(a2.getCompletion());

            assertEquals("Play DDR", a3.getName());
            assertEquals("Metrotown", a3.getLocation());
            assertEquals(5, a3.getCost());
            assertTrue(a3.getCategory());
            assertTrue(a3.getInOrOut());
            assertTrue(a3.getCompletion());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
