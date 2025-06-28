package dev.siroshun.mcmsgdef;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TranslatableComponent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

/**
 * A record holding the key of the message.
 *
 * @param key the key of the message
 */
public record MessageKey(@NotNull String key) implements ComponentLike {

    /**
     * Constructs a {@link MessageKey} with the specified key.
     *
     * @throws NullPointerException     if the key is null
     * @throws IllegalArgumentException if the key is empty
     */
    public MessageKey {
        Objects.requireNonNull(key, "key must not be null");
        if (key.isEmpty()) {
            throw new IllegalArgumentException("key must not be empty");
        }
    }

    /**
     * Creates a {@link MessageKey} from the given key.
     *
     * @param key the key of the message, not {@code null}
     * @return a {@link MessageKey} with the given key
     */
    @Contract("_ -> new")
    public static @NotNull MessageKey key(@NotNull String key) {
        return new MessageKey(key);
    }

    /**
     * Creates a {@link Arg1} from the given key and argument.
     *
     * @param key  the key of the message, not {@code null}
     * @param arg1 the argument of the message, not {@code null}
     * @param <A1> the type of the argument
     * @return a {@link Arg1} with the given key and argument
     */
    @Contract("_, _ -> new")
    public static <A1> @NotNull Arg1<A1> arg1(@NotNull String key, @NotNull Function<? super A1, ? extends ComponentLike> arg1) {
        return new Arg1<>(key, arg1);
    }

    /**
     * Creates a {@link Arg2} from the given key and arguments.
     *
     * @param key  the key of the message, not {@code null}
     * @param arg1 the first argument of the message, not {@code null}
     * @param arg2 the second argument of the message, not {@code null}
     * @param <A1> the type of the first argument
     * @param <A2> the type of the second argument
     * @return a {@link Arg2} with the given key and arguments
     */
    @Contract("_, _, _ -> new")
    public static <A1, A2> @NotNull Arg2<A1, A2> arg2(@NotNull String key, @NotNull Function<? super A1, ? extends ComponentLike> arg1, @NotNull Function<? super A2, ? extends ComponentLike> arg2) {
        return new Arg2<>(key, arg1, arg2);
    }

    /**
     * Creates a {@link Arg3} from the given key and arguments.
     *
     * @param key  the key of the message, not {@code null}
     * @param arg1 the first argument of the message, not {@code null}
     * @param arg2 the second argument of the message, not {@code null}
     * @param arg3 the third argument of the message, not {@code null}
     * @param <A1> the type of the first argument
     * @param <A2> the type of the second argument
     * @param <A3> the type of the third argument
     * @return a {@link Arg3} with the given key and arguments
     */
    @Contract("_, _, _, _ -> new")
    public static <A1, A2, A3> @NotNull Arg3<A1, A2, A3> arg3(@NotNull String key, @NotNull Function<? super A1, ? extends ComponentLike> arg1, @NotNull Function<? super A2, ? extends ComponentLike> arg2, @NotNull Function<? super A3, ? extends ComponentLike> arg3) {
        return new Arg3<>(key, arg1, arg2, arg3);
    }

    /**
     * Creates a {@link Arg4} from the given key and arguments.
     *
     * @param key  the key of the message, not {@code null}
     * @param arg1 the first argument of the message, not {@code null}
     * @param arg2 the second argument of the message, not {@code null}
     * @param arg3 the third argument of the message, not {@code null}
     * @param arg4 the fourth argument of the message, not {@code null}
     * @param <A1> the type of the first argument
     * @param <A2> the type of the second argument
     * @param <A3> the type of the third argument
     * @param <A4> the type of the fourth argument
     * @return a {@link Arg4} with the given key and arguments
     */
    @Contract("_, _, _, _, _ -> new")
    public static <A1, A2, A3, A4> @NotNull Arg4<A1, A2, A3, A4> arg4(@NotNull String key, @NotNull Function<? super A1, ? extends ComponentLike> arg1, @NotNull Function<? super A2, ? extends ComponentLike> arg2, @NotNull Function<? super A3, ? extends ComponentLike> arg3, @NotNull Function<? super A4, ? extends ComponentLike> arg4) {
        return new Arg4<>(key, arg1, arg2, arg3, arg4);
    }

    /**
     * Creates a {@link Arg5} from the given key and arguments.
     *
     * @param key  the key of the message, not {@code null}
     * @param arg1 the first argument of the message, not {@code null}
     * @param arg2 the second argument of the message, not {@code null}
     * @param arg3 the third argument of the message, not {@code null}
     * @param arg4 the fourth argument of the message, not {@code null}
     * @param arg5 the fifth argument of the message, not {@code null}
     * @param <A1> the type of the first argument
     * @param <A2> the type of the second argument
     * @param <A3> the type of the third argument
     * @param <A4> the type of the fourth argument
     * @param <A5> the type of the fifth argument
     * @return a {@link Arg5} with the given key and arguments
     */
    @Contract("_, _, _, _, _, _ -> new")
    public static <A1, A2, A3, A4, A5> @NotNull Arg5<A1, A2, A3, A4, A5> arg5(@NotNull String key, @NotNull Function<? super A1, ? extends ComponentLike> arg1, @NotNull Function<? super A2, ? extends ComponentLike> arg2, @NotNull Function<? super A3, ? extends ComponentLike> arg3, @NotNull Function<? super A4, ? extends ComponentLike> arg4, @NotNull Function<? super A5, ? extends ComponentLike> arg5) {
        return new Arg5<>(key, arg1, arg2, arg3, arg4, arg5);
    }

    /**
     * Creates a {@link Arg1} from this key and the given argument.
     *
     * @param arg1 the argument of the message, not {@code null}
     * @param <A1> the type of the argument
     * @return a {@link Arg1} with this key and the given argument
     */
    @Contract("_ -> new")
    public <A1> @NotNull Arg1<A1> with(@NotNull Function<? super A1, ? extends ComponentLike> arg1) {
        return arg1(this.key, arg1);
    }

    /**
     * Creates a {@link Arg2} from this key and the given arguments.
     *
     * @param arg1 the first argument of the message, not {@code null}
     * @param arg2 the second argument of the message, not {@code null}
     * @param <A1> the type of the first argument
     * @param <A2> the type of the second argument
     * @return a {@link Arg2} with this key and the given arguments
     */
    @Contract("_, _ -> new")
    public <A1, A2> @NotNull Arg2<A1, A2> with(@NotNull Function<? super A1, ? extends ComponentLike> arg1, @NotNull Function<? super A2, ? extends ComponentLike> arg2) {
        return arg2(this.key, arg1, arg2);
    }

    /**
     * Creates a {@link Arg3} from this key and the given arguments.
     *
     * @param arg1 the first argument of the message, not {@code null}
     * @param arg2 the second argument of the message, not {@code null}
     * @param arg3 the third argument of the message, not {@code null}
     * @param <A1> the type of the first argument
     * @param <A2> the type of the second argument
     * @param <A3> the type of the third argument
     * @return a {@link Arg3} with this key and the given arguments
     */
    @Contract("_, _, _ -> new")
    public <A1, A2, A3> @NotNull Arg3<A1, A2, A3> with(@NotNull Function<? super A1, ? extends ComponentLike> arg1, @NotNull Function<? super A2, ? extends ComponentLike> arg2, @NotNull Function<? super A3, ? extends ComponentLike> arg3) {
        return arg3(this.key, arg1, arg2, arg3);
    }

    /**
     * Creates a {@link Arg4} from this key and the given arguments.
     *
     * @param arg1 the first argument of the message, not {@code null}
     * @param arg2 the second argument of the message, not {@code null}
     * @param arg3 the third argument of the message, not {@code null}
     * @param arg4 the fourth argument of the message, not {@code null}
     * @param <A1> the type of the first argument
     * @param <A2> the type of the second argument
     * @param <A3> the type of the third argument
     * @param <A4> the type of the fourth argument
     * @return a {@link Arg4} with this key and the given arguments
     */
    @Contract("_, _, _, _ -> new")
    public <A1, A2, A3, A4> @NotNull Arg4<A1, A2, A3, A4> with(@NotNull Function<? super A1, ? extends ComponentLike> arg1, @NotNull Function<? super A2, ? extends ComponentLike> arg2, @NotNull Function<? super A3, ? extends ComponentLike> arg3, @NotNull Function<? super A4, ? extends ComponentLike> arg4) {
        return arg4(this.key, arg1, arg2, arg3, arg4);
    }

    /**
     * Creates a {@link Arg5} from this key and the given arguments.
     *
     * @param arg1 the first argument of the message, not {@code null}
     * @param arg2 the second argument of the message, not {@code null}
     * @param arg3 the third argument of the message, not {@code null}
     * @param arg4 the fourth argument of the message, not {@code null}
     * @param arg5 the fifth argument of the message, not {@code null}
     * @param <A1> the type of the first argument
     * @param <A2> the type of the second argument
     * @param <A3> the type of the third argument
     * @param <A4> the type of the fourth argument
     * @param <A5> the type of the fifth argument
     * @return a {@link Arg5} with this key and the given arguments
     */
    @Contract("_, _, _, _, _ -> new")
    public <A1, A2, A3, A4, A5> @NotNull Arg5<A1, A2, A3, A4, A5> with(@NotNull Function<? super A1, ? extends ComponentLike> arg1, @NotNull Function<? super A2, ? extends ComponentLike> arg2, @NotNull Function<? super A3, ? extends ComponentLike> arg3, @NotNull Function<? super A4, ? extends ComponentLike> arg4, @NotNull Function<? super A5, ? extends ComponentLike> arg5) {
        return arg5(this.key, arg1, arg2, arg3, arg4, arg5);
    }

    @Contract(pure = true)
    @Override
    public @NotNull TranslatableComponent asComponent() {
        return Component.translatable(this.key);
    }

    /**
     * A record holding a message key and one argument function.
     *
     * @param key  the key of the message
     * @param arg1 the function to convert the argument to a component
     * @param <A1> the type of the argument
     */
    public record Arg1<A1>(@NotNull String key, @NotNull Function<? super A1, ? extends ComponentLike> arg1) {
        /**
         * Constructs an Arg1 with the specified key and argument function.
         *
         * @throws NullPointerException if the key or argument function is null
         */
        public Arg1 {
            Objects.requireNonNull(key, "key must not be null");
            Objects.requireNonNull(arg1, "arg1 must not be null");
            if (key.isEmpty()) {
                throw new IllegalArgumentException("key must not be empty");
            }
        }

        /**
         * Creates a translatable component with the given argument.
         *
         * @param a the argument value
         * @return a translatable component with the key and the converted argument
         */
        @Contract("_ -> new")
        public @NotNull TranslatableComponent apply(A1 a) {
            return Component.translatable(this.key, this.arg1.apply(a));
        }
    }

    /**
     * A record holding a message key and two argument functions.
     *
     * @param key  the key of the message
     * @param arg1 the function to convert the first argument to a component
     * @param arg2 the function to convert the second argument to a component
     * @param <A1> the type of the first argument
     * @param <A2> the type of the second argument
     */
    public record Arg2<A1, A2>(@NotNull String key, @NotNull Function<? super A1, ? extends ComponentLike> arg1,
                               @NotNull Function<? super A2, ? extends ComponentLike> arg2) {
        /**
         * Constructs an Arg2 with the specified key and argument functions.
         *
         * @throws NullPointerException     if the key or any argument function is null
         * @throws IllegalArgumentException if the key is empty
         */
        public Arg2 {
            Objects.requireNonNull(key, "key must not be null");
            Objects.requireNonNull(arg1, "arg1 must not be null");
            Objects.requireNonNull(arg2, "arg2 must not be null");
            if (key.isEmpty()) {
                throw new IllegalArgumentException("key must not be empty");
            }
        }

        /**
         * Creates a translatable component with the given arguments.
         *
         * @param a1 the first argument value
         * @param a2 the second argument value
         * @return a translatable component with the key and the converted arguments
         */
        @Contract("_, _ -> new")
        public @NotNull TranslatableComponent apply(A1 a1, A2 a2) {
            return Component.translatable(this.key, this.arg1.apply(a1), this.arg2.apply(a2));
        }
    }

    /**
     * A record holding a message key and three argument functions.
     *
     * @param key  the key of the message
     * @param arg1 the function to convert the first argument to a component
     * @param arg2 the function to convert the second argument to a component
     * @param arg3 the function to convert the third argument to a component
     * @param <A1> the type of the first argument
     * @param <A2> the type of the second argument
     * @param <A3> the type of the third argument
     */
    public record Arg3<A1, A2, A3>(@NotNull String key, @NotNull Function<? super A1, ? extends ComponentLike> arg1,
                                   @NotNull Function<? super A2, ? extends ComponentLike> arg2,
                                   @NotNull Function<? super A3, ? extends ComponentLike> arg3) {
        /**
         * Constructs an Arg3 with the specified key and argument functions.
         *
         * @throws NullPointerException     if the key or any argument function is null
         * @throws IllegalArgumentException if the key is empty
         */
        public Arg3 {
            Objects.requireNonNull(key, "key must not be null");
            Objects.requireNonNull(arg1, "arg1 must not be null");
            Objects.requireNonNull(arg2, "arg2 must not be null");
            Objects.requireNonNull(arg3, "arg3 must not be null");
            if (key.isEmpty()) {
                throw new IllegalArgumentException("key must not be empty");
            }
        }

        /**
         * Creates a translatable component with the given arguments.
         *
         * @param a1 the first argument value
         * @param a2 the second argument value
         * @param a3 the third argument value
         * @return a translatable component with the key and the converted arguments
         */
        @Contract("_, _, _ -> new")
        public @NotNull TranslatableComponent apply(A1 a1, A2 a2, A3 a3) {
            return Component.translatable(this.key, this.arg1.apply(a1), this.arg2.apply(a2), this.arg3.apply(a3));
        }
    }

    /**
     * A record holding a message key and four argument functions.
     *
     * @param key  the key of the message
     * @param arg1 the function to convert the first argument to a component
     * @param arg2 the function to convert the second argument to a component
     * @param arg3 the function to convert the third argument to a component
     * @param arg4 the function to convert the fourth argument to a component
     * @param <A1> the type of the first argument
     * @param <A2> the type of the second argument
     * @param <A3> the type of the third argument
     * @param <A4> the type of the fourth argument
     */
    public record Arg4<A1, A2, A3, A4>(@NotNull String key, @NotNull Function<? super A1, ? extends ComponentLike> arg1,
                                       @NotNull Function<? super A2, ? extends ComponentLike> arg2,
                                       @NotNull Function<? super A3, ? extends ComponentLike> arg3,
                                       @NotNull Function<? super A4, ? extends ComponentLike> arg4) {
        /**
         * Constructs an Arg4 with the specified key and argument functions.
         *
         * @throws NullPointerException     if the key or any argument function is null
         * @throws IllegalArgumentException if the key is empty
         */
        public Arg4 {
            Objects.requireNonNull(key, "key must not be null");
            Objects.requireNonNull(arg1, "arg1 must not be null");
            Objects.requireNonNull(arg2, "arg2 must not be null");
            Objects.requireNonNull(arg3, "arg3 must not be null");
            Objects.requireNonNull(arg4, "arg4 must not be null");
            if (key.isEmpty()) {
                throw new IllegalArgumentException("key must not be empty");
            }
        }

        /**
         * Creates a translatable component with the given arguments.
         *
         * @param a1 the first argument value
         * @param a2 the second argument value
         * @param a3 the third argument value
         * @param a4 the fourth argument value
         * @return a translatable component with the key and the converted arguments
         */
        @Contract("_, _, _, _ -> new")
        public @NotNull TranslatableComponent apply(A1 a1, A2 a2, A3 a3, A4 a4) {
            return Component.translatable(this.key, this.arg1.apply(a1), this.arg2.apply(a2), this.arg3.apply(a3), this.arg4.apply(a4));
        }
    }

    /**
     * A record holding a message key and five argument functions.
     *
     * @param key  the key of the message
     * @param arg1 the function to convert the first argument to a component
     * @param arg2 the function to convert the second argument to a component
     * @param arg3 the function to convert the third argument to a component
     * @param arg4 the function to convert the fourth argument to a component
     * @param arg5 the function to convert the fifth argument to a component
     * @param <A1> the type of the first argument
     * @param <A2> the type of the second argument
     * @param <A3> the type of the third argument
     * @param <A4> the type of the fourth argument
     * @param <A5> the type of the fifth argument
     */
    public record Arg5<A1, A2, A3, A4, A5>(@NotNull String key,
                                           @NotNull Function<? super A1, ? extends ComponentLike> arg1,
                                           @NotNull Function<? super A2, ? extends ComponentLike> arg2,
                                           @NotNull Function<? super A3, ? extends ComponentLike> arg3,
                                           @NotNull Function<? super A4, ? extends ComponentLike> arg4,
                                           @NotNull Function<? super A5, ? extends ComponentLike> arg5) {
        /**
         * Constructs an Arg5 with the specified key and argument functions.
         *
         * @throws NullPointerException     if the key or any argument function is null
         * @throws IllegalArgumentException if the key is empty
         */
        public Arg5 {
            Objects.requireNonNull(key, "key must not be null");
            Objects.requireNonNull(arg1, "arg1 must not be null");
            Objects.requireNonNull(arg2, "arg2 must not be null");
            Objects.requireNonNull(arg3, "arg3 must not be null");
            Objects.requireNonNull(arg4, "arg4 must not be null");
            Objects.requireNonNull(arg5, "arg5 must not be null");
            if (key.isEmpty()) {
                throw new IllegalArgumentException("key must not be empty");
            }
        }

        /**
         * Creates a translatable component with the given arguments.
         *
         * @param a1 the first argument value
         * @param a2 the second argument value
         * @param a3 the third argument value
         * @param a4 the fourth argument value
         * @param a5 the fifth argument value
         * @return a translatable component with the key and the converted arguments
         */
        @Contract("_, _, _, _, _ -> new")
        public @NotNull TranslatableComponent apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5) {
            return Component.translatable(this.key, this.arg1.apply(a1), this.arg2.apply(a2), this.arg3.apply(a3), this.arg4.apply(a4), this.arg5.apply(a5));
        }
    }
}
