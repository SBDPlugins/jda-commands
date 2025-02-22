package com.github.kaktushose.jda.commands.data.slash;

import com.github.kaktushose.jda.commands.reflect.CommandDefinition;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.Collection;
import java.util.List;

/**
 * A tree data structure representing Commands sorted into Subcommands and SubcommandGroups. Each {@link TreeNode} can
 * have <em>n</em> children, however the maximum level is <em>3</em> due to Discords limitations on SubcommandGroups.
 *
 * @author Kaktushose
 * @version 2.3.0
 * @see TreeNode
 * @see <a href="https://discord.com/developers/docs/interactions/application-commands#subcommands-and-subcommand-groups">Discord Subcommands and Subcommand Groups Documentation</a>
 * @since 2.3.0
 */
public class CommandTree {

    private final TreeNode root;

    /**
     * Constructs an empty CommandTree.
     */
    public CommandTree() {
        root = new TreeNode();
    }

    /**
     * Constructs a new CommandTree.
     */
    public CommandTree(Collection<CommandDefinition> commands) {
        root = new TreeNode();
        addAll(commands);
    }

    /**
     * Adds a {@link CommandDefinition} to the {@link CommandTree}. The label of the {@link CommandDefinition} will be
     * sanitized to match the regex {@code ^[\w-]+$}. Furthermore, if the label consists of more than three spaces any
     * additional space will be replaced with {@code _} due to Discords limitations on SubcommandGroups.
     *
     * @param command the {@link CommandDefinition} to add
     */
    public void add(CommandDefinition command) {
        root.addChild(resolveLabel(command.getLabels().get(0)), command);
    }

    /**
     * Adds all {@link CommandDefinition CommandDefinitions} of the {@link Collection} to the {@link CommandTree}.
     *
     * @param commands a {@link Collection} of {@link CommandDefinition CommandDefinitions} to add
     * @see #add(CommandDefinition)
     */
    public void addAll(Collection<CommandDefinition> commands) {
        commands.forEach(this::add);
    }

    private String[] resolveLabel(String label) {
        String[] split = label.split(" ", 3);
        if (split.length > 3) {
            split[4] = split[4].replaceAll(" ", "_");
        }
        for (int i = 0; i < split.length; i++) {
            for (char c : split[i].toCharArray()) {
                if (String.valueOf(c).equals(" ")) {
                    split[i] = split[i].replace(String.valueOf(c), "_");
                }
                if (!String.valueOf(c).matches("^[\\w-]+$")) {
                    split[i] = split[i].replace(String.valueOf(c), "");
                }
                split[i] = split[i].toLowerCase();
            }
        }
        return split;
    }

    /**
     * Gets all {@link SlashCommandData}.This will only return the {@link SlashCommandData} of the leaf nodes.
     *
     * @return a {@link List} of {@link SlashCommandData}
     */
    public List<SlashCommandData> getCommands() {
        return root.getCommandData();
    }

    /**
     * Gets the sanitized labels of all {@link SlashCommandData} returned by {@link #getCommands()}.
     * The labels will match the regex {@code ^[\w-]+$}. Furthermore, if the label consists of more than three spaces
     * any additional space will be replaced with {@code _} due to Discords limitations on SubcommandGroups.
     *
     * @return a {@link List} of labels
     */
    public List<String> getNames() {
        return root.getNames();
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
