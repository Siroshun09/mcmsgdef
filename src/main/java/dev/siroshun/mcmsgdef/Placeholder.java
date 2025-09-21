package dev.siroshun.mcmsgdef;

import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

/**
 * An extended interface of the {@link Function} to create {@link ComponentLike} from the {@link T}.
 *
 * @param <T> the type of the source object
 */
public interface Placeholder<T> extends Function<T, ComponentLike> {

    @Override
    default <V> @NotNull Placeholder<V> compose(@NotNull Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> this.apply(before.apply(v));
    }

}
