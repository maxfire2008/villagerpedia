package net.maxstuff.villagerpedia;

import org.bukkit.plugin.java.JavaPlugin;
import net.maxstuff.villagerpedia.VillagerClick;
import net.maxstuff.villagerpedia.Database;

import dev.jorel.commandapi.CommandAPI;
import net.maxstuff.villagerpedia.Commands.VillagerpediaCommand;

public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
        getServer().getPluginManager().registerEvents(new VillagerClick(), this);
        CommandAPI.registerCommand(VillagerpediaCommand.class);
    }

    @Override
    public void onDisable() {
        Database database = Database.getInstance();
        database.save();
        getLogger().info("See you again, SpigotMC!");
    }
}