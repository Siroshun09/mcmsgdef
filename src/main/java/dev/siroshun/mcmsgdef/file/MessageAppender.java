package dev.siroshun.mcmsgdef.file;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * An interface to append messages to something.
 *
 * @param <T> the type of destination that will be appended to
 * @param <S> the type of the message source
 */
@FunctionalInterface
public interface MessageAppender<T, S> {

    /**
     * Appends messages to the target.
     *
     * @param target     a target
     * @param messageMap a message map to append
     * @throws IOException if I/O error occurred
     */
    void append(@NotNull T target, @NotNull S messageMap) throws IOException;

}
