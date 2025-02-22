package com.github.kaktushose.jda.commands.dispatching.adapter.impl;

import com.github.kaktushose.jda.commands.dispatching.CommandContext;
import com.github.kaktushose.jda.commands.dispatching.adapter.TypeAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Type adapter for integer values.
 *
 * @author Kaktushose
 * @version 2.0.0
 * @since 2.0.0
 */
public class IntegerAdapter implements TypeAdapter<Integer> {

    /**
     * Attempts to parse a String to an Integer.
     *
     * @param raw     the String to parse
     * @param context the {@link CommandContext}
     * @return the parsed Integer or an empty Optional if the parsing fails
     */
    @Override
    public Optional<Integer> parse(@NotNull String raw, @NotNull CommandContext context) {
        try {
            return Optional.of((int) Double.parseDouble(raw));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }
}
