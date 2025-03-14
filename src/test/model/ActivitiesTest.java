package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActivitiesTest {
    Activities as1;
    List<Activity> listAS1;
    List<Activity> completedAS1;
    Activity a0;
    Activity ac;

    @BeforeEach
    void runBefore() {
        as1 = new Activities();
        listAS1 = as1.getActivities();
        completedAS1 = as1.getCompletedActivities();
        a0 = new Activity("Watch T.V.", "Home", 0, false, true);
        ac = new Activity("Have dinner", "Joe Fortes", 50, false, true);
        ac.markAsDone();
    }

    @Test
    void testActivities() {
        assertTrue(listAS1.isEmpty());
        assertTrue(completedAS1.isEmpty());
    }

    @Test
    void testAddNewActivity() {
        as1.addNewActivity("Get brunch", "Jam Cafe", 20, false, true);
        assertFalse(listAS1.isEmpty());
        assertTrue(completedAS1.isEmpty());
        assertEquals(1, listAS1.size());
        Activity a1 = listAS1.get(0);
        assertEquals("Get brunch", a1.getName());
        assertEquals("Jam Cafe", a1.getLocation());
        assertEquals(20, a1.getCost());
        assertFalse(a1.getCategory());
        assertTrue(a1.getInOrOut());
    }

    @Test
    void testAddNewActivityMultiple() {
        as1.addNewActivity("Get brunch", "Jam Cafe", 20, false, true);
        as1.addNewActivity("Bike around", "Stanley Park", 0, true, false);
        assertFalse(listAS1.isEmpty());
        assertTrue(completedAS1.isEmpty());
        assertEquals(2, listAS1.size());
        Activity a1 = listAS1.get(0);
        Activity a2 = listAS1.get(1);
        assertEquals("Get brunch", a1.getName());
        assertEquals("Jam Cafe", a1.getLocation());
        assertEquals(20, a1.getCost());
        assertFalse(a1.getCategory());
        assertTrue(a1.getInOrOut());
        assertEquals("Bike around", a2.getName());
        assertEquals("Stanley Park", a2.getLocation());
        assertEquals(0, a2.getCost());
        assertTrue(a2.getCategory());
        assertFalse(a2.getInOrOut());
    }

    @Test
    void testAddCompletedActivity() {
        as1.addNewActivity("Get brunch", "Jam Cafe", 20, false, true);
        Activity a1 = listAS1.get(0);
        as1.addCompletedActivity(a1);
        assertEquals(1, completedAS1.size());
    }

    @Test
    void testAddCompletedActivityMultiple() {
        as1.addNewActivity("Get brunch", "Jam Cafe", 20, false, true);
        as1.addNewActivity("Bike around", "Stanley Park", 0, true, false);
        Activity a1 = listAS1.get(0);
        Activity a2 = listAS1.get(1);
        as1.addCompletedActivity(a1);
        as1.addCompletedActivity(a2);
        assertEquals(2, completedAS1.size());
    }

    @Test
    void testAddActivity() {
        as1.addActivity(a0);
        assertEquals(1, listAS1.size());
    }

    @Test
    void testAddActivityMultiple() {
        as1.addActivity(a0);
        as1.addActivity(ac);
        assertEquals(2, listAS1.size());
    }
}
