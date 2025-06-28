package dev.siroshun.mcmsgdef.directory;

import dev.siroshun.mcmsgdef.file.FileExtension;
import dev.siroshun.mcmsgdef.file.Loader;
import dev.siroshun.mcmsgdef.file.PropertiesFile;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.minimessage.translation.MiniMessageTranslationStore;
import net.kyori.adventure.translation.GlobalTranslator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * A class for loading message maps from the directory.
 */
public final class DirectorySource {

    /**
     * Creates a new {@link DirectorySource}.
     *
     * @param directory a directory to load messages
     * @return a new {@link DirectorySource}
     */
    @Contract("_ -> new")
    public static @NotNull DirectorySource create(@NotNull Path directory) {
        return new DirectorySource(Objects.requireNonNull(directory), Collections.emptySet(), null, null);
    }

    /**
     * Creates a new {@link DirectorySource}.
     *
     * @param directory a directory to load messages
     * @return a new {@link DirectorySource}
     */
    @Contract("_ -> new")
    public static @NotNull DirectorySource forStringMessageMap(@NotNull Path directory) {
        return new DirectorySource(Objects.requireNonNull(directory), Collections.emptySet(), null, null);
    }

    /**
     * Creates a new {@link DirectorySource}, which loads messages from properties files.
     *
     * @param directory a directory to load messages
     * @return a new {@link DirectorySource}
     */
    @Contract("_ -> new")
    public static @NotNull DirectorySource propertiesFiles(@NotNull Path directory) {
        return DirectorySource.forStringMessageMap(directory).fileExtension(PropertiesFile.FILE_EXTENSION).messageLoader(PropertiesFile.DEFAULT_LOADER);
    }

    private final Path directory;
    private final @Unmodifiable Set<Locale> defaultLocales;
    private final @Nullable FileExtension fileExtension;
    private final @Nullable Loader<LoadContext, LoadedMessageMap> loader;

    private DirectorySource(@NotNull Path directory,
                            @NotNull Set<Locale> defaultLocales,
                            @Nullable FileExtension fileExtension,
                            @Nullable Loader<LoadContext, LoadedMessageMap> loader) {
        this.directory = directory;
        this.defaultLocales = defaultLocales;
        this.fileExtension = fileExtension;
        this.loader = loader;
    }

    /**
     * Sets {@link FileExtension}.
     *
     * @param fileExtension the {@link FileExtension}
     * @return a new {@link DirectorySource} instance
     */
    public @NotNull DirectorySource fileExtension(@NotNull FileExtension fileExtension) {
        return new DirectorySource(this.directory, this.defaultLocales, Objects.requireNonNull(fileExtension), this.loader);
    }

    /**
     * Adds a default {@link Locale}.
     *
     * @param locale a default {@link Locale}
     * @return a new {@link DirectorySource} instance
     */
    public @NotNull DirectorySource defaultLocale(@NotNull Locale locale) {
        return this.defaultLocale0(List.of(locale));
    }

    /**
     * Adds default {@link Locale}s.
     *
     * @param locales default {@link Locale}s
     * @return a new {@link DirectorySource} instance
     */
    public @NotNull DirectorySource defaultLocale(@NotNull Locale @NotNull ... locales) {
        return this.defaultLocale0(List.of(locales));
    }

    /**
     * Adds default {@link Locale}s.
     *
     * @param locales default {@link Locale}s
     * @return a new {@link DirectorySource} instance
     */
    public @NotNull DirectorySource defaultLocale(@NotNull Collection<Locale> locales) {
        return this.defaultLocale0(List.copyOf(locales));
    }

    private @NotNull DirectorySource defaultLocale0(@NotNull @Unmodifiable List<Locale> locales) {
        Set<Locale> newDefaultLocales;
        if (this.defaultLocales.isEmpty()) {
            newDefaultLocales = new HashSet<>(locales);
        } else {
            newDefaultLocales = new HashSet<>(this.defaultLocales.size() + locales.size(), 1.0f);
            newDefaultLocales.addAll(this.defaultLocales);
            newDefaultLocales.addAll(locales);
        }
        return new DirectorySource(this.directory, Collections.unmodifiableSet(newDefaultLocales), this.fileExtension, this.loader);
    }

    /**
     * Sets the {@link Loader} to load messages from the file.
     *
     * @param loader the {@link Loader} to load messages from the file
     * @return a new {@link DirectorySource} instance
     */
    public @NotNull DirectorySource messageLoader(@NotNull Loader<Path, Map<String, String>> loader) {
        Objects.requireNonNull(loader);

        if (this.loader != null) {
            throw new IllegalStateException("The message loader is already set.");
        }

        return new DirectorySource(
            this.directory,
            this.defaultLocales,
            this.fileExtension,
            context -> new LoadedMessageMap(context.filepath, context.locale, loader.apply(context.filepath))
        );
    }

    /**
     * Adds a {@link Loader} that processes loaded messages.
     *
     * @param processor a {@link Loader} that processes loaded messages.
     * @return a new {@link DirectorySource} instance
     */
    public @NotNull DirectorySource messageProcessor(@NotNull Loader<LoadedMessageMap, Map<String, String>> processor) {
        if (this.loader == null) {
            throw new IllegalStateException("The message loader is not set.");
        }

        return new DirectorySource(
            this.directory,
            this.defaultLocales,
            this.fileExtension,
            context -> new LoadedMessageMap(context.filepath, context.locale, processor.apply(this.loader.apply(context)))
        );
    }

    /**
     * Performs loading.
     * <p>
     * {@link #fileExtension} and {@link #loader} must be set.
     *
     * @param consumer a {@link Consumer} to consume loaded message map
     * @throws IOException if I/O error occurred
     */
    public void load(@NotNull Consumer<LoadedMessageMap> consumer) throws IOException {
        if (this.fileExtension == null) {
            throw new IllegalStateException("localeParser is not set");
        }

        if (this.loader == null) {
            throw new IllegalStateException("loader is not set");
        }

        Objects.requireNonNull(consumer);

        Map<Path, Locale> file2LocaleMap = collectPath(this.directory, this.fileExtension);

        if (file2LocaleMap.isEmpty()) {
            if (this.defaultLocales.isEmpty()) {
                return;
            } else {
                Files.createDirectories(this.directory); // At this time, the directory may not exist.
            }
        }

        for (Locale locale : this.defaultLocales) {
            Objects.requireNonNull(locale);
            Path filepath = this.directory.resolve(this.fileExtension.toFilename(locale));
            file2LocaleMap.putIfAbsent(filepath, locale);
        }

        for (Map.Entry<Path, Locale> entry : file2LocaleMap.entrySet()) {
            consumer.accept(this.loader.load(new LoadContext(entry.getKey(), entry.getValue())));
        }
    }

    /**
     * Performs loading and returns the loaded message map as {@link MiniMessageTranslationStore}.
     *
     * @param key the {@link Key} of the {@link MiniMessageTranslationStore}
     * @return the loaded message map as {@link MiniMessageTranslationStore}
     * @throws IOException if I/O error occurred
     */
    public @NotNull MiniMessageTranslationStore loadAsMiniMessageTranslationStore(@NotNull Key key) throws IOException {
        MiniMessageTranslationStore store = MiniMessageTranslationStore.create(key);
        this.load(source -> store.registerAll(source.locale(), source.messageMap()));
        return store;
    }

    /**
     * Performs loading and registers the loaded message map as {@link MiniMessageTranslationStore} to the {@link GlobalTranslator}.
     *
     * @param key the {@link Key} of the {@link MiniMessageTranslationStore}
     * @throws IOException if I/O error occurred
     */
    public void loadAndRegister(@NotNull Key key) throws IOException {
        MiniMessageTranslationStore store = this.loadAsMiniMessageTranslationStore(key);
        GlobalTranslator.translator().addSource(store);
    }

    private static @NotNull Map<Path, Locale> collectPath(@NotNull Path directory,
                                                          @NotNull FileExtension fileExtension) throws IOException {
        if (!Files.isDirectory(directory)) {
            return new HashMap<>();
        }

        Map<Path, Locale> fileLocaleMap = new HashMap<>();
        try (Stream<Path> list = Files.list(directory)) {
            list.forEach(path -> {
                if (Files.isRegularFile(path)) {
                    Locale locale = fileExtension.parse(path.getFileName().toString());
                    if (locale != null) {
                        fileLocaleMap.put(path, locale);
                    }
                }
            });
        }
        return fileLocaleMap;
    }

    private record LoadContext(@NotNull Path filepath, @NotNull Locale locale) {
    }
}
