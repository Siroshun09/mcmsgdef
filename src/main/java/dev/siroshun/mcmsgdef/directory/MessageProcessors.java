package dev.siroshun.mcmsgdef.directory;

import dev.siroshun.mcmsgdef.file.Loader;
import dev.siroshun.mcmsgdef.file.MessageAppender;
import dev.siroshun.mcmsgdef.file.PropertiesFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A class that provides utilities of message processing.
 */
public final class MessageProcessors {

    /**
     * Creates a {@link Loader} to append missing messages to the loaded message map.
     *
     * @param defaultMessageMap a map of default messages
     * @return a {@link Loader} to append missing messages to the loaded message map
     */
    public static @NotNull Loader<LoadedMessageMap, Map<String, String>> appendMissingMessages(@NotNull Map<String, String> defaultMessageMap) {
        return loaded -> appendMissingMessages(loaded, defaultMessageMap, null);
    }

    /**
     * Creates a {@link Loader} to append missing messages to the loaded message map and the given {@link MessageAppender}.
     *
     * @param defaultMessageMap a map of default messages
     * @param messageAppender   a {@link MessageAppender}
     * @return a {@link Loader} to append missing messages to the loaded message map and the given {@link MessageAppender}
     */
    public static @NotNull Loader<LoadedMessageMap, Map<String, String>> appendMissingMessages(@NotNull Map<String, String> defaultMessageMap, @Nullable MessageAppender<Path, Map<String, String>> messageAppender) {
        return loaded -> appendMissingMessages(loaded, defaultMessageMap, messageAppender);
    }

    /**
     * Creates a {@link Loader} to append missing messages to the loaded message map and something such as files.
     *
     * @param defaultMessageLoader a {@link Loader} to get default messages
     * @param messageAppender      a {@link MessageAppender}
     * @return a {@link Loader} to append missing messages to the loaded message map and something such as files
     */
    public static @NotNull Loader<LoadedMessageMap, Map<String, String>> appendMissingMessages(@NotNull Loader<Locale, @Nullable Map<String, String>> defaultMessageLoader, @Nullable MessageAppender<Path, Map<String, String>> messageAppender) {
        return loaded -> {
            Map<String, String> defaultMessageMap = defaultMessageLoader.load(loaded.locale());
            appendMissingMessages(loaded, defaultMessageMap, messageAppender);
            return loaded.messageMap();
        };
    }

    /**
     * Creates a {@link Loader} to append missing messages to the loaded message map and the properties file.
     *
     * @param defaultMessageMap a map of default messages
     * @return a {@link Loader} to append missing messages to the loaded message map and the properties file
     */
    public static @NotNull Loader<LoadedMessageMap, Map<String, String>> appendMissingMessagesToPropertiesFile(@NotNull Map<String, String> defaultMessageMap) {
        return loaded -> appendMissingMessages(loaded, defaultMessageMap, PropertiesFile.DEFAULT_APPENDER);
    }

    /**
     * Creates a {@link Loader} to append missing messages to the loaded message map and the properties file.
     *
     * @param defaultMessageLoader a {@link Loader} to get default messages
     * @return a {@link Loader} to append missing messages to the loaded message map and the properties file
     */
    public static @NotNull Loader<LoadedMessageMap, Map<String, String>> appendMissingMessagesToPropertiesFile(@NotNull Loader<Locale, @Nullable Map<String, String>> defaultMessageLoader) {
        return appendMissingMessages(defaultMessageLoader, PropertiesFile.DEFAULT_APPENDER);
    }

    private static @NotNull Map<String, String> appendMissingMessages(LoadedMessageMap loaded, @NotNull Map<String, String> defaultMessageMap, @Nullable MessageAppender<Path, Map<String, String>> messageAppender) throws IOException {
        Map<String, String> missingMessages = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : defaultMessageMap.entrySet()) {
            if (loaded.messageMap().putIfAbsent(entry.getKey(), entry.getValue()) == null) {
                missingMessages.put(entry.getKey(), entry.getValue());
            }
        }
        if (messageAppender != null && !missingMessages.isEmpty()) {
            messageAppender.append(loaded.filepath(), missingMessages);
        }

        return loaded.messageMap();
    }

    private MessageProcessors() {
        throw new UnsupportedOperationException();
    }
}
