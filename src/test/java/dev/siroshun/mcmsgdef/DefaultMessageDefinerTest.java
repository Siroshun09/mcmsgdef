package dev.siroshun.mcmsgdef;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DefaultMessageDefinerTest {

    @Test
    void test() {
        DefaultMessageDefiner definer = DefaultMessageDefiner.create();

        Map<String, String> map = definer.getCollectedMessages();
        assertTrue(map.isEmpty());

        MessageKey key = definer.define("test1", "test1 message");
        assertNotNull(key);
        assertEquals("test1", key.key());
        assertEquals("test1 message", map.get("test1"));

        MessageKey key2 = definer.define("test2", "test2 message");
        assertNotNull(key2);
        assertEquals("test2", key2.key());
        assertEquals("test2 message", map.get("test2"));

        MessageKey key3 = definer.define("test3", "test3 message");
        assertNotNull(key3);
        assertEquals("test3", key3.key());

        // ordered entries
        assertEquals(
            List.of(Map.entry("test1", "test1 message"), Map.entry("test2", "test2 message"), Map.entry("test3", "test3 message")),
            map.entrySet().stream().toList()
        );
    }

    @Test
    void testEmptyKey() {
        DefaultMessageDefiner definer = DefaultMessageDefiner.create();
        assertThrows(IllegalArgumentException.class, () -> definer.define("", "test message"));
    }

    @SuppressWarnings("DataFlowIssue")
    @Test
    void testNullKey() {
        DefaultMessageDefiner definer = DefaultMessageDefiner.create();
        assertThrows(NullPointerException.class, () -> definer.define(null, "test message"));
    }

    @SuppressWarnings("DataFlowIssue")
    @Test
    void testUnmodifiableMap() {
        DefaultMessageDefiner definer = DefaultMessageDefiner.create();
        Map<String, String> map = definer.getCollectedMessages();
        assertThrows(UnsupportedOperationException.class, () -> map.put("test", "test message"));
    }
}
