package demo.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReadOnlyTest {
    @Test
    void testSend() {
        ReadOnly ro = new ReadOnly("John", "123 Main St");
        assertEquals("send John to 123 Main St", ro.send());
    }

    @Test
    void testEquals() {
        ReadOnly r = new ReadOnly("John", "123 Main St");
        ReadOnly o = new ReadOnly("John", "123 Main St");
        assertEquals(r, o);
    }

    @Test
    void testRecordEquals() {
        ReadOnlyRecord r = new ReadOnlyRecord("John", "123 Main St");
        ReadOnlyRecord o = new ReadOnlyRecord("John", "123 Main St");
        assertEquals(r, o);
    }

    @Test
    void testNewRecord() {
        assertThrows(AssertionError.class, () -> new ReadOnlyRecord("", ""));
    }
}
