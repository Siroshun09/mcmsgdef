package dev.siroshun.mcmsgdef;

import net.kyori.adventure.text.Component;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MessageKeyTest {

    private static final String KEY = "test";
    private static final Placeholder<String> PLACEHOLDER_1 = Component::text;
    private static final Placeholder<Integer> PLACEHOLDER_2 = Component::text;
    private static final Placeholder<Double> PLACEHOLDER_3 = Component::text;
    private static final Placeholder<Boolean> PLACEHOLDER_4 = Component::text;
    private static final Placeholder<UUID> PLACEHOLDER_5 = id -> Component.text(id.toString());

    @Test
    void testKey() {
        MessageKey msg = MessageKey.key(KEY);
        assertEquals(KEY, msg.key());
        assertEquals(Component.translatable(KEY), msg.asComponent());

        assertThrows(IllegalArgumentException.class, () -> MessageKey.key(""));
    }

    @Test
    void testArg1() {
        MessageKey.Arg1<String> msg = MessageKey.arg1(KEY, PLACEHOLDER_1);
        assertEquals(KEY, msg.key());
        assertEquals(
            Component.translatable(KEY, PLACEHOLDER_1.apply("test")),
            msg.apply("test")
        );

        assertThrows(IllegalArgumentException.class, () -> MessageKey.arg1("", PLACEHOLDER_1));
    }

    @Test
    void testArg2() {
        MessageKey.Arg2<String, Integer> msg = MessageKey.arg2(KEY, PLACEHOLDER_1, PLACEHOLDER_2);
        assertEquals(KEY, msg.key());
        assertEquals(
            Component.translatable(KEY, PLACEHOLDER_1.apply("test"), PLACEHOLDER_2.apply(42)),
            msg.apply("test", 42)
        );

        assertThrows(IllegalArgumentException.class, () -> MessageKey.arg2("", PLACEHOLDER_1, PLACEHOLDER_2));
    }

    @Test
    void testArg3() {
        MessageKey.Arg3<String, Integer, Double> msg = MessageKey.arg3(KEY, PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3);
        assertEquals(KEY, msg.key());
        assertEquals(
            Component.translatable(KEY, PLACEHOLDER_1.apply("test"), PLACEHOLDER_2.apply(42), PLACEHOLDER_3.apply(3.14)),
            msg.apply("test", 42, 3.14)
        );

        assertThrows(IllegalArgumentException.class, () -> MessageKey.arg3("", PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3));
    }

    @Test
    void testArg4() {
        MessageKey.Arg4<String, Integer, Double, Boolean> msg = MessageKey.arg4(KEY, PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4);
        assertEquals(KEY, msg.key());
        assertEquals(
            Component.translatable(KEY, PLACEHOLDER_1.apply("test"), PLACEHOLDER_2.apply(42), PLACEHOLDER_3.apply(3.14), PLACEHOLDER_4.apply(true)),
            msg.apply("test", 42, 3.14, true)
        );

        assertThrows(IllegalArgumentException.class, () -> MessageKey.arg4("", PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4));
    }

    @Test
    void testArg5() {
        UUID uuid = UUID.fromString("b56fa849-8960-46cf-8c47-7f2f2d522c0a");
        MessageKey.Arg5<String, Integer, Double, Boolean, UUID> msg = MessageKey.arg5(KEY, PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4, PLACEHOLDER_5);
        assertEquals(KEY, msg.key());
        assertEquals(
            Component.translatable(KEY, PLACEHOLDER_1.apply("test"), PLACEHOLDER_2.apply(42), PLACEHOLDER_3.apply(3.14), PLACEHOLDER_4.apply(true), PLACEHOLDER_5.apply(uuid)),
            msg.apply("test", 42, 3.14, true, uuid)
        );

        assertThrows(IllegalArgumentException.class, () -> MessageKey.arg5("", PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4, PLACEHOLDER_5));
    }

    @Test
    void testArg1_With() {
        MessageKey.Arg1<String> msg = MessageKey.key(KEY).with(PLACEHOLDER_1);
        assertEquals(KEY, msg.key());
        assertEquals(
            Component.translatable(KEY, PLACEHOLDER_1.apply("test")),
            msg.apply("test")
        );
    }

    @Test
    void testArg2_With() {
        MessageKey.Arg2<String, Integer> msg = MessageKey.key(KEY).with(PLACEHOLDER_1, PLACEHOLDER_2);
        assertEquals(KEY, msg.key());
        assertEquals(
            Component.translatable(KEY, PLACEHOLDER_1.apply("test"), PLACEHOLDER_2.apply(42)),
            msg.apply("test", 42)
        );
    }

    @Test
    void testArg3_With() {
        MessageKey.Arg3<String, Integer, Double> msg = MessageKey.key(KEY).with(PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3);
        assertEquals(KEY, msg.key());
        assertEquals(
            Component.translatable(KEY, PLACEHOLDER_1.apply("test"), PLACEHOLDER_2.apply(42), PLACEHOLDER_3.apply(3.14)),
            msg.apply("test", 42, 3.14)
        );
    }

    @Test
    void testArg4_With() {
        MessageKey.Arg4<String, Integer, Double, Boolean> msg = MessageKey.key(KEY).with(PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4);
        assertEquals(KEY, msg.key());
        assertEquals(
            Component.translatable(KEY, PLACEHOLDER_1.apply("test"), PLACEHOLDER_2.apply(42), PLACEHOLDER_3.apply(3.14), PLACEHOLDER_4.apply(true)),
            msg.apply("test", 42, 3.14, true)
        );
    }

    @Test
    void testArg5_With() {
        UUID uuid = UUID.fromString("b56fa849-8960-46cf-8c47-7f2f2d522c0a");
        MessageKey.Arg5<String, Integer, Double, Boolean, UUID> msg = MessageKey.key(KEY).with(PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4, PLACEHOLDER_5);
        assertEquals(KEY, msg.key());
        assertEquals(
            Component.translatable(KEY, PLACEHOLDER_1.apply("test"), PLACEHOLDER_2.apply(42), PLACEHOLDER_3.apply(3.14), PLACEHOLDER_4.apply(true), PLACEHOLDER_5.apply(uuid)),
            msg.apply("test", 42, 3.14, true, uuid)
        );
    }


    @SuppressWarnings("DataFlowIssue")
    @Test
    void testNullArgs() {
        // key
        assertThrows(NullPointerException.class, () -> MessageKey.key(null));

        // arg1
        assertThrows(NullPointerException.class, () -> MessageKey.arg1(null, PLACEHOLDER_1));
        assertThrows(NullPointerException.class, () -> MessageKey.arg1(KEY, null));

        // arg2
        assertThrows(NullPointerException.class, () -> MessageKey.arg2(null, PLACEHOLDER_1, PLACEHOLDER_2));
        assertThrows(NullPointerException.class, () -> MessageKey.arg2(KEY, null, PLACEHOLDER_2));
        assertThrows(NullPointerException.class, () -> MessageKey.arg2(KEY, PLACEHOLDER_1, null));

        // arg3
        assertThrows(NullPointerException.class, () -> MessageKey.arg3(null, PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3));
        assertThrows(NullPointerException.class, () -> MessageKey.arg3(KEY, null, PLACEHOLDER_2, PLACEHOLDER_3));
        assertThrows(NullPointerException.class, () -> MessageKey.arg3(KEY, PLACEHOLDER_1, null, PLACEHOLDER_3));
        assertThrows(NullPointerException.class, () -> MessageKey.arg3(KEY, PLACEHOLDER_1, PLACEHOLDER_2, null));

        // arg4
        assertThrows(NullPointerException.class, () -> MessageKey.arg4(null, PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4));
        assertThrows(NullPointerException.class, () -> MessageKey.arg4(KEY, null, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4));
        assertThrows(NullPointerException.class, () -> MessageKey.arg4(KEY, PLACEHOLDER_1, null, PLACEHOLDER_3, PLACEHOLDER_4));
        assertThrows(NullPointerException.class, () -> MessageKey.arg4(KEY, PLACEHOLDER_1, PLACEHOLDER_2, null, PLACEHOLDER_4));
        assertThrows(NullPointerException.class, () -> MessageKey.arg4(KEY, PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, null));

        // arg5
        assertThrows(NullPointerException.class, () -> MessageKey.arg5(null, PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4, PLACEHOLDER_5));
        assertThrows(NullPointerException.class, () -> MessageKey.arg5(KEY, null, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4, PLACEHOLDER_5));
        assertThrows(NullPointerException.class, () -> MessageKey.arg5(KEY, PLACEHOLDER_1, null, PLACEHOLDER_3, PLACEHOLDER_4, PLACEHOLDER_5));
        assertThrows(NullPointerException.class, () -> MessageKey.arg5(KEY, PLACEHOLDER_1, PLACEHOLDER_2, null, PLACEHOLDER_4, PLACEHOLDER_5));
        assertThrows(NullPointerException.class, () -> MessageKey.arg5(KEY, PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, null, PLACEHOLDER_5));
        assertThrows(NullPointerException.class, () -> MessageKey.arg5(KEY, PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4, null));

        // with 1 argument
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(null));

        // with 2 arguments
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(null, PLACEHOLDER_2));
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(PLACEHOLDER_1, null));

        // with 3 arguments
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(null, PLACEHOLDER_2, PLACEHOLDER_3));
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(PLACEHOLDER_1, null, PLACEHOLDER_3));
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(PLACEHOLDER_1, PLACEHOLDER_2, null));

        // with 4 arguments
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(null, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4));
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(PLACEHOLDER_1, null, PLACEHOLDER_3, PLACEHOLDER_4));
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(PLACEHOLDER_1, PLACEHOLDER_2, null, PLACEHOLDER_4));
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, null));

        // with 5 arguments
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(null, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4, PLACEHOLDER_5));
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(PLACEHOLDER_1, null, PLACEHOLDER_3, PLACEHOLDER_4, PLACEHOLDER_5));
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(PLACEHOLDER_1, PLACEHOLDER_2, null, PLACEHOLDER_4, PLACEHOLDER_5));
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, null, PLACEHOLDER_5));
        assertThrows(NullPointerException.class, () -> MessageKey.key(KEY).with(PLACEHOLDER_1, PLACEHOLDER_2, PLACEHOLDER_3, PLACEHOLDER_4, null));
    }
}
