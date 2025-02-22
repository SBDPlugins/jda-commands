package com.github.kaktushose.jda.commands.permissions;

import com.github.kaktushose.jda.commands.dispatching.CommandContext;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for performing permission checks.
 *
 * @author Kaktushose
 * @version 2.3.0
 * @see DefaultPermissionsProvider
 * @see com.github.kaktushose.jda.commands.annotations.Permission Permission
 * @since 2.0.0
 */
public interface PermissionsProvider {

    /**
     * Checks if a {@link User} is muted. Useful for ban lists, where the {@link CommandContext} should be cancelled even before
     * routing, permission checks, etc.
     *
     * @param user    the {@link User} to perform the check against
     * @param context the corresponding {@link CommandContext}
     * @return {@code true} if the user is muted
     * @see com.github.kaktushose.jda.commands.dispatching.filter.impl.UserMuteFilter UserMuteFilter
     */
    boolean isMuted(@NotNull User user, @NotNull CommandContext context);

    /**
     * Checks if a {@link User} has permissions. Compared to {@link #hasPermission(Member, CommandContext)} this method will be
     * called if the command gets executed in a non-guild context, where no member object is available.
     *
     * @param user    the {@link User} to perform the check against
     * @param context the corresponding {@link CommandContext}
     * @return {@code true} if the user has the permission to execute the command
     * @see #hasPermission(Member, CommandContext)
     */
    boolean hasPermission(@NotNull User user, @NotNull CommandContext context);

    /**
     * Checks if a {@link Member} has permissions.
     *
     * @param member  the {@link Member} to perform the check against
     * @param context the corresponding {@link CommandContext}
     * @return {@code true} if the user has the permission to execute the command
     */
    boolean hasPermission(@NotNull Member member, @NotNull CommandContext context);

    /**
     * Gets a {@link List} of user ids that map to the given permission string.
     *
     * @param guild      the corresponding guild
     * @param permission the corresponding string
     * @return a {@link List} of user ids
     */
    default List<Long> getUsersWithPermission(Guild guild, String permission) {
        return new ArrayList<>();
    }

    /**
     * Gets a {@link List} of role ids that map to the given permission string.
     *
     * @param guild      the corresponding guild
     * @param permission the corresponding string
     * @return a {@link List} of role ids
     */
    default List<Long> getRolesWithPermission(Guild guild, String permission) {
        return new ArrayList<>();
    }

}
