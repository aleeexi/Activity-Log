package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e1;
    private Date d1;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e1 = new Event("New activity added to log."); // (1)
        d1 = Calendar.getInstance().getTime(); // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("New activity added to log.", e1.getDescription());
        assertEquals(d1, e1.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d1.toString() + "\n" + "New activity added to log.", e1.toString());
    }

    @Test
    public void testEqualsNull() {
        Event test = null;
        assertFalse(e1.equals(test));
    }

    @Test
    public void testEqualsTypeMismatch() {
        assertFalse(e1.equals(d1));
        assertFalse(e1.hashCode() == d1.hashCode());
    }
}
