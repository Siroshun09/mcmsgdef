package dev.siroshun.mcmsgdef.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LocaleParserTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void parse(TestCase testCase) {
        assertEquals(testCase.expected(), LocaleParser.parse(testCase.input()));
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
            new TestCase(null, null),
            new TestCase("", null),
            new TestCase("en", Locale.of("en")),
            new TestCase("ja", Locale.of("ja")),
            new TestCase("en_US", Locale.of("en", "US")),
            new TestCase("ja_JP", Locale.of("ja", "JP")),
            new TestCase("en_US_WIN", Locale.of("en", "US", "WIN"))
        );
    }

    private record TestCase(String input, Locale expected) {
    }
}
