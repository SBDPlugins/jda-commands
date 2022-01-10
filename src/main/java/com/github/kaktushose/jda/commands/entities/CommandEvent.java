package com.github.kaktushose.jda.commands.entities;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * This class is a subclass of the {@code GuildMessageReceivedEvent} from JDA.
 * It provides some additional features for sending messages and also grants
 * access to the {@link CommandCallable} object which describes the command that is executed.
 *
 * @author Kaktushose
 * @version 1.0.0
 * @since 1.0.0
 */
public class CommandEvent extends GuildMessageReceivedEvent {

    private final CommandCallable commandCallable;
    private final JDACommands jdaCommands;

    /**
     * Constructs a CommandEvent.
     *
     * @param api             the {@code JDA}, needed for the {@code GuildMessageReceivedEvent}
     * @param responseNumber  the responseNumber, needed for the {@code GuildMessageReceivedEvent}
     * @param message         the {@code Message}, needed for the {@code GuildMessageReceivedEvent}
     * @param commandCallable the underlying {@link CommandCallable} object
     * @param jdaCommands     the {@link JDACommands} object
     */
    public CommandEvent(@Nonnull JDA api, long responseNumber, @Nonnull Message message, @Nonnull CommandCallable commandCallable, JDACommands jdaCommands) {
        super(api, responseNumber, message);
        this.commandCallable = commandCallable;
        this.jdaCommands = jdaCommands;
    }

    /**
     * Sends a message to the TextChannel where the command was called.
     *
     * @param message the message to send
     */
    public void reply(@Nonnull String message) {
        getChannel().sendMessage(message).queue();
    }

    /**
     * Sends a formatted message using the specified format string and arguments to the TextChannel where the command was called.
     *
     * @param format the message to send
     * @param args   Arguments referenced by the format specifiers in the format string. If there are more arguments than
     *               format specifiers, the extra arguments are ignored. The number of arguments is variable and may be
     *               zero.
     * @throws java.util.IllegalFormatException If a format string contains an illegal syntax, a format specifier that
     *                                          is incompatible with the given arguments, insufficient arguments given
     *                                          the format string, or other illegal conditions.
     */
    public void reply(@Nonnull String format, @Nullable Object... args) {
        reply(String.format(format, args));
    }

    /**
     * Sends a message to the TextChannel where the command was called. This method also allows to access the JDA RestAction
     * consumer.
     *
     * @param message the message to send
     * @param success the JDA RestAction success consumer
     * @see <a href="https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/requests/RestAction.html">JDA RestAction Documentation</a>
     */
    public void reply(@Nonnull String message, @Nullable Consumer<Message> success) {
        getChannel().sendMessage(message).queue(success);
    }

    /**
     * Sends a message to the TextChannel where the command was called.
     *
     * @param message the {@code Message} to send
     */
    public void reply(@Nonnull Message message) {
        getChannel().sendMessage(message).queue();
    }

    /**
     * Sends a message to the TextChannel where the command was called. This method also allows to access the JDA RestAction
     * consumer.
     *
     * @param message the {@code Message} to send
     * @param success the JDA RestAction success consumer
     * @see <a href="https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/requests/RestAction.html">JDA RestAction Documentation</a>
     */
    public void reply(@Nonnull Message message, @Nullable Consumer<Message> success) {
        getChannel().sendMessage(message).queue(success);
    }

    /**
     * Sends a message to the TextChannel where the command was called.
     *
     * @param messageBuilder the {@code MessageBuilder} to send
     */
    public void reply(@Nonnull MessageBuilder messageBuilder) {
        getChannel().sendMessage(messageBuilder.build()).queue();
    }

    /**
     * Sends a message to the TextChannel where the command was called. This method also allows to access the JDA RestAction
     * consumer.
     *
     * @param messageBuilder the {@code MessageBuilder} to send
     * @param success        the JDA RestAction success consumer
     * @see <a href="https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/requests/RestAction.html">JDA RestAction Documentation</a>
     */
    public void reply(@Nonnull MessageBuilder messageBuilder, @Nullable Consumer<Message> success) {
        getChannel().sendMessage(messageBuilder.build()).queue(success);
    }

    /**
     * Sends a message to the TextChannel where the command was called.
     *
     * @param embedBuilder the {@code EmbedBuilder} to send
     */
    public void reply(@Nonnull EmbedBuilder embedBuilder) {
        getChannel().sendMessage(embedBuilder.build()).queue();
    }

    /**
     * Sends a message to the TextChannel where the command was called. This method also allows to access the JDA RestAction
     * consumer.
     *
     * @param embedBuilder the {@code EmbedBuilder} to send
     * @param success      the JDA RestAction success consumer
     * @see <a href="https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/requests/RestAction.html">JDA RestAction Documentation</a>
     */
    public void reply(@Nonnull EmbedBuilder embedBuilder, @Nullable Consumer<Message> success) {
        getChannel().sendMessage(embedBuilder.build()).queue(success);
    }

    /**
     * Sends a message to the TextChannel where the command was called.
     *
     * @param embedBuilder the {@code EmbedBuilder} to send
     */
    public void reply(@Nonnull EmbedDTO embedBuilder) {
        getChannel().sendMessage(embedBuilder.toMessageEmbed()).queue();
    }

    /**
     * Sends a message to the TextChannel where the command was called. This method also allows to access the JDA RestAction
     * consumer.
     *
     * @param embedBuilder the {@code EmbedBuilder} to send
     * @param success      the JDA RestAction success consumer
     * @see <a href="https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/requests/RestAction.html">JDA RestAction Documentation</a>
     */
    public void reply(@Nonnull EmbedDTO embedBuilder, @Nullable Consumer<Message> success) {
        getChannel().sendMessage(embedBuilder.toMessageEmbed()).queue(success);
    }

    /**
     * Get the {@link CommandCallable} object which describes the command that is executed.
     *
     * @return the underlying {@link CommandCallable} object
     */
    public CommandCallable getCommandCallable() {
        return commandCallable;
    }

    /**
     * Get the {@link JDACommands} object.
     *
     * @return the {@link JDACommands} object
     */
    public JDACommands getJdaCommands() {
        return jdaCommands;
    }
}
