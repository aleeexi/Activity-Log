package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActivityTest {
    Activity a1;
    Activity a2;
    Activity a3;
    Activity a4;
    Activity a5;

    @BeforeEach
    void runBefore() {
        a1 = new Activity("Spikeball", "MacInnes Field", 90, true, false);
        a2 = new Activity("Going on a walk", "English Bay", 0, false, false);
        a3 = new Activity("Play Mario Kart", "Home", 0, false, true);
        a4 = new Activity("Try new food", "Richmond Night Market", 9, false, false);
        a5 = new Activity("Ice skating", "Robson Square", 5, true, false);
    }

    @Test
    void testActivity() {
        assertEquals("Spikeball", a1.getName());
        assertEquals("MacInnes Field", a1.getLocation());
        assertEquals(90, a1.getCost());
        assertTrue(a1.getCategory());
        assertFalse(a1.getInOrOut());
        assertFalse(a1.getCompletion());

    }

    @Test
    void testMarkAsDone() {
        a1.markAsDone();
        assertTrue(a1.getCompletion());
    }

    @Test
    void testMarkAsDoneMultiple() {
        a2.markAsDone();
        assertTrue(a2.getCompletion());
        a2.markAsDone();
        assertTrue(a2.getCompletion());
    }

    @Test
    void testGetFullCategoryActive() {
        assertEquals("Active", a5.getFullCategory());
    }

    @Test
    void testGetFullCategoryRelaxing() {
        assertEquals("Relaxing", a4.getFullCategory());
    }

    @Test
    void testGetFullInOrOutIndoors() {
        assertEquals("indoors", a3.getFullInOrOut());
    }

    @Test
    void testGetFullInOrOutOutdoors() {
        assertEquals("outdoors", a5.getFullInOrOut());
    }
}
