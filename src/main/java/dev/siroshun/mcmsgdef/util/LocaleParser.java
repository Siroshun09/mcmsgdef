package dev.siroshun.mcmsgdef.util;

import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * A class to parse a string to {@link Locale}.
 */
public final class LocaleParser {

    private static final Pattern SPLITTER = Pattern.compile("_");

    /**
     * Parses a string to {@link Locale} (format: {@code language-region}).
     *
     * @param string a string representation of the locale (format: {@code language-region})
     * @return a locale
     */
    public static @Nullable Locale parse(@Nullable String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }

        String[] segments = SPLITTER.split(string, 3);
        return switch (segments.length) {
            case 1 -> Locale.of(segments[0]);
            case 2 -> Locale.of(segments[0], segments[1]);
            case 3 -> Locale.of(segments[0], segments[1], segments[2]);
            default -> null;
        };
    }

    private LocaleParser() {
        throw new UnsupportedOperationException();
    }
}
