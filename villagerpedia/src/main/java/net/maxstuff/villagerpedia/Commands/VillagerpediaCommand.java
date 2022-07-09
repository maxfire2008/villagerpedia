package net.maxstuff.villagerpedia.Commands;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.maxstuff.villagerpedia.Database;

@Command("villagerpedia")
public class VillagerpediaCommand {
    @Default
    public static void villagerpedia(CommandSender sender) {
        Player senderPlayer = (Player) sender;
        Database database = Database.getInstance();
        Object default_interface = database.getDefaultInterface(senderPlayer.getUniqueId());
        if (default_interface == "web") {
            sender.sendMessage("<link:https://maxstuff.net>");
        }
    }

    @Subcommand("help")
    @Permission("villagerpedia.help")
    public static void createWarp(Player player, @AStringArgument String warpName) {
        warps.put(warpName, player.getLocation());
    }
}