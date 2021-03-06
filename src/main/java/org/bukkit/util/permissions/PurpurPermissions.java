package org.bukkit.util.permissions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class PurpurPermissions {
    private static final String ROOT = "purpur";
    private static final String PREFIX = ROOT + ".";
    private static final Set<String> mobs = new HashSet<>();

    private PurpurPermissions() {
        for (EntityType mob : EntityType.values()) {
            Class<? extends Entity> clazz = mob.getEntityClass();
            if (clazz != null && clazz.isAssignableFrom(Mob.class)) {
                mobs.add(mob.getName());
            }
        }
    }

    @NotNull
    public static Permission registerPermissions() {
        Permission purpur = DefaultPermissions.registerPermission(ROOT, "Gives the user the ability to use all Purpur utilities and commands");

        DefaultPermissions.registerPermission(PREFIX + "enderchest.rows.six", "Gives the user six rows of enderchest space", org.bukkit.permissions.PermissionDefault.FALSE);
        DefaultPermissions.registerPermission(PREFIX + "enderchest.rows.five", "Gives the user five rows of enderchest space", org.bukkit.permissions.PermissionDefault.FALSE);
        DefaultPermissions.registerPermission(PREFIX + "enderchest.rows.four", "Gives the user four rows of enderchest space", org.bukkit.permissions.PermissionDefault.FALSE);
        DefaultPermissions.registerPermission(PREFIX + "enderchest.rows.three", "Gives the user three rows of enderchest space", org.bukkit.permissions.PermissionDefault.FALSE);
        DefaultPermissions.registerPermission(PREFIX + "enderchest.rows.two", "Gives the user two rows of enderchest space", org.bukkit.permissions.PermissionDefault.FALSE);
        DefaultPermissions.registerPermission(PREFIX + "enderchest.rows.one", "Gives the user one row of enderchest space", org.bukkit.permissions.PermissionDefault.FALSE);

        DefaultPermissions.registerPermission(PREFIX + "debug.f3n", "Allows the user to use F3N keybind to swap gamemodes", PermissionDefault.FALSE, purpur);

        DefaultPermissions.registerPermission(PREFIX + "drop.spawner", "Allows the user to drop spawner cage when broken with diamond pickaxe with silk touch", PermissionDefault.FALSE, purpur);
        DefaultPermissions.registerPermission(PREFIX + "place.spawner", "Allows the user to place spawner cage in the world", PermissionDefault.FALSE, purpur);

        Permission sign = DefaultPermissions.registerPermission(PREFIX + "sign", "Allows the user to use all sign abilities", PermissionDefault.FALSE, purpur);
        DefaultPermissions.registerPermission(PREFIX + "sign.click.opens.editor", "Allows the user to click signs to open sign editor", PermissionDefault.FALSE, sign);
        DefaultPermissions.registerPermission(PREFIX + "sign.color", "Allows the user to use color codes on signs", PermissionDefault.FALSE, sign);
        DefaultPermissions.registerPermission(PREFIX + "sign.style", "Allows the user to use style codes on signs", PermissionDefault.FALSE, sign);
        DefaultPermissions.registerPermission(PREFIX + "sign.magic", "Allows the user to use magic/obfuscate code on signs", PermissionDefault.FALSE, sign);
        sign.recalculatePermissibles();

        Permission ride = DefaultPermissions.registerPermission("allow.ride", "Allows the user to ride all mobs", PermissionDefault.FALSE);
        for (String mob : mobs) {
            DefaultPermissions.registerPermission("allow.ride." + mob, "Allows the user to ride " + mob, PermissionDefault.FALSE, ride);
        }
        ride.recalculatePermissibles();

        Permission special = DefaultPermissions.registerPermission("allow.special", "Allows the user to use all mobs special abilities", PermissionDefault.FALSE);
        for (String mob : mobs) {
            DefaultPermissions.registerPermission("allow.special." + mob, "Allows the user to use " + mob + " special ability", PermissionDefault.FALSE, special);
        }
        special.recalculatePermissibles();

        Permission powered = DefaultPermissions.registerPermission("allow.powered", "Allows the user to toggle all mobs powered state", PermissionDefault.FALSE);
        DefaultPermissions.registerPermission("allow.powered.creeper", "Allows the user to toggle creeper powered state", PermissionDefault.FALSE, powered);
        powered.recalculatePermissibles();

        purpur.recalculatePermissibles();
        return purpur;
    }
}
