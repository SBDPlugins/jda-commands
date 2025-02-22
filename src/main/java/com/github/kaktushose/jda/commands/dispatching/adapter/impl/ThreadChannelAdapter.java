package com.github.kaktushose.jda.commands.dispatching.adapter.impl;

import com.github.kaktushose.jda.commands.dispatching.CommandContext;
import com.github.kaktushose.jda.commands.dispatching.adapter.TypeAdapter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Type adapter for JDAs {@link net.dv8tion.jda.api.entities.ThreadChannel}.
 *
 * @author Kaktushose
 * @version 2.3.0
 * @since 2.3.0
 */
public class ThreadChannelAdapter implements TypeAdapter<ThreadChannel> {

    /**
     * Attempts to parse a String to a {@link ThreadChannel}. Accepts both the channel id and name.
     *
     * @param raw     the String to parse
     * @param context the {@link CommandContext}
     * @return the parsed {@link ThreadChannel} or an empty Optional if the parsing fails
     */
    @Override
    public Optional<ThreadChannel> parse(@NotNull String raw, @NotNull CommandContext context) {
        if (!context.getEvent().isFromType(ChannelType.TEXT)) {
            return Optional.empty();
        }

        ThreadChannel threadChannel;
        raw = sanitizeMention(raw);

        Guild guild = context.getEvent().getGuild();
        if (raw.matches("\\d+")) {
            threadChannel = guild.getThreadChannelById(raw);
        } else {
            threadChannel = guild.getThreadChannelsByName(raw, true).stream().findFirst().orElse(null);
        }
        if (threadChannel == null) {
            return Optional.empty();
        }
        return Optional.of(threadChannel);
    }
}
