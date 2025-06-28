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
        Locale.Builder builder = new Locale.Builder();

        switch (segments.length) {
            case 1 -> builder.setLanguage(segments[0]);
            case 2 -> builder.setLanguage(segments[0]).setRegion(segments[1]);
            case 3 -> builder.setLanguage(segments[0]).setRegion(segments[1]).setVariant(segments[2]);
            default -> {
                return null;
            }
        }

        return builder.build();
    }

    private LocaleParser() {
        throw new UnsupportedOperationException();
    }
}
