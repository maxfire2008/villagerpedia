package net.maxstuff.villagerpedia;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import net.maxstuff.villagerpedia.AddToEntitlements;

public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
        getServer().getPluginManager().registerEvents(new AddToEntitlements(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}