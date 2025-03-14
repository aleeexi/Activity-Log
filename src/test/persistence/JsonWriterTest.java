package persistence;

import model.Activities;
import model.Activity;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

// tests from JsonSerializationDemo
public class JsonWriterTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            new Activities();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyActivityLog() {
        try {
            Activities as = new Activities();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyActivityLog.json");
            writer.open();
            writer.write(as);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyActivityLog.json");
            as = reader.read();
            List<Activity> list = as.getActivities();
            List<Activity> completed = as.getCompletedActivities();
            assertEquals(0, list.size());
            assertEquals(0, completed.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterActivityLog() {
        try {
            Activities as = new Activities();
            List<Activity> list0 = as.getActivities();
            as.addNewActivity("Watch T.V.", "Home", 0, false, true);
            Activity a0 = list0.get(0);
            a0.markAsDone();
            as.addCompletedActivity(a0);
            as.addNewActivity("Have dinner", "Joe Fortes", 50, false, true);
            Activity a1 = list0.get(1);
            a1.markAsDone();
            as.addCompletedActivity(a1);
            JsonWriter writer = new JsonWriter("./data/testWriterActivityLog.json");
            writer.open();
            writer.write(as);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterActivityLog.json");
            as = reader.read();
            List<Activity> list = as.getActivities();
            List<Activity> completed = as.getCompletedActivities();
            Activity test0 = list.get(0);
            Activity test1 = list.get(1);
            assertEquals(2, list.size());
            assertEquals(2, completed.size());

            assertEquals("Watch T.V.", test0.getName());
            assertEquals("Home", test0.getLocation());
            assertEquals(0, test0.getCost());
            assertFalse(test0.getCategory());
            assertTrue(test0.getInOrOut());
            assertTrue(test0.getCompletion());

            assertEquals("Have dinner", test1.getName());
            assertEquals("Joe Fortes", test1.getLocation());
            assertEquals(50, test1.getCost());
            assertFalse(test1.getCategory());
            assertTrue(test1.getInOrOut());
            assertTrue(test1.getCompletion());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
