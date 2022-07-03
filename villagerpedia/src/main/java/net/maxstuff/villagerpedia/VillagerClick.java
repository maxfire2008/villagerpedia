package net.maxstuff.villagerpedia;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import net.maxstuff.villagerpedia.Database;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class VillagerClick implements Listener {
   // @EventHandler
   // public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
   // // Entity entity = event.getRightClicked();
   // Bukkit.broadcastMessage(event.getRightClicked().toString());
   // Entity entity = event.getRightClicked();
   // if (entity.toString() == "CraftVillager") {
   // Database database = Database.getInstance();
   // // Alert only the player they clicked a villager
   // Player player = event.getPlayer();
   // player.sendMessage(player.getUniqueId() + " clicked " +
   // entity.getUniqueId());
   // try {
   // entity
   // player.sendMessage(merchant.toString());
   // } catch (Exception e) {
   // player.sendMessage("No recipes");
   // }
   // // database.addVillager(player.getUniqueId(),entity.getUniqueId());
   // }
   // }
   @EventHandler
   public void onInventoryOpenEvent(InventoryOpenEvent event) {
      if (event.getInventory().getType() == InventoryType.MERCHANT) {
         Merchant merchant = (Merchant) event.getInventory().getHolder();
         List<MerchantRecipe> recipes = merchant.getRecipes();
         for (MerchantRecipe recipe : recipes) {
            Bukkit.broadcastMessage(recipe.toString());
         }
      }
   }
}