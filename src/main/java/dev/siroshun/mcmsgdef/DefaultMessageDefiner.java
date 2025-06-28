package dev.siroshun.mcmsgdef;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A utility class to define messages.
 */
public final class DefaultMessageDefiner {

    /**
     * Creates a new {@link DefaultMessageDefiner}.
     *
     * @return a new {@link DefaultMessageDefiner}
     */
    @Contract(" -> new")
    public static @NotNull DefaultMessageDefiner create() {
        return new DefaultMessageDefiner();
    }

    private final Map<String, String> map = new LinkedHashMap<>();

    private DefaultMessageDefiner() {
    }

    /**
     * Defines a message key with the default message and returns it.
     *
     * @param key            the key of the message
     * @param defaultMessage the default message
     * @return the key of the message
     */
    public @NotNull MessageKey define(@NotNull String key, @NotNull String defaultMessage) {
        Objects.requireNonNull(key, "key cannot be null");
        Objects.requireNonNull(defaultMessage, "defaultMessage cannot be null");
        this.map.put(key, defaultMessage);
        return MessageKey.key(key);
    }

    /**
     * Gets the map of defined keys and their default messages.
     *
     * @return the map of defined keys and their default messages
     */
    @Contract(pure = true)
    public @NotNull @UnmodifiableView Map<String, String> getCollectedMessages() {
        return Collections.unmodifiableMap(this.map);
    }
}
