package dev.siroshun.mcmsgdef;

import net.kyori.adventure.text.ComponentLike;

import java.util.function.Function;

/**
 * An extended interface of the {@link Function} to create {@link ComponentLike} from the {@link T}.
 *
 * @param <T> the type of the source object
 */
public interface Placeholder<T> extends Function<T, ComponentLike> {
}
