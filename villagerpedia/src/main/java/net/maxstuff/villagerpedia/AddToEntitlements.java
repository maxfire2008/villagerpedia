package net.maxstuff.villagerpedia;

import org.bukkit.Bukkit;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class AddToEntitlements implements Listener {
   @EventHandler
   public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
      // Entity entity = event.getRightClicked();
      // Bukkit.broadcastMessage(event.getRightClicked().toString());
      if (event.getRightClicked().toString() == "CraftVillager") {
         //Alert only the player they clicked a villager
         event.getPlayer().sendMessage("You clicked a Villager");
      }
   }
}