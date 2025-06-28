package dev.siroshun.mcmsgdef.directory;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * A record of the loaded message map.
 *
 * @param filepath   a loaded filepath
 * @param locale     a {@link Locale} of the loaded message map
 * @param messageMap a loaded message map
 */
public record LoadedMessageMap(@NotNull Path filepath,
                               @NotNull Locale locale,
                               @NotNull Map<String, String> messageMap) {

    /**
     * Creates a new {@link LoadedMessageMap}.
     *
     * @param filepath   a loaded filepath
     * @param locale     a {@link Locale} of the message map
     * @param messageMap a loaded message map
     */
    public LoadedMessageMap {
        Objects.requireNonNull(filepath);
        Objects.requireNonNull(locale);
        Objects.requireNonNull(messageMap);
    }
}
