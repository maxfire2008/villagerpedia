package net.maxstuff.villagerpedia;

import java.util.List;

import org.bukkit.Bukkit;
// import org.bukkit.entity.Villager;
// import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
// import org.bukkit.event.player.PlayerInteractEntityEvent;
// import net.maxstuff.villagerpedia.Database;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Inventory;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.bukkit.enchantments.Enchantment;
import java.util.Map;
import com.google.gson.Gson;
import org.bukkit.entity.Player;

public class VillagerClick implements Listener {
   @EventHandler
   public void onInventoryCloseEvent(InventoryCloseEvent event) {
      Player player = (Player) event.getPlayer();
      if (player.hasPermission("villagerpedia.registervillagers")) {
         Inventory event_inventory = event.getInventory();
         if (event_inventory.getType() == InventoryType.MERCHANT) {
            Merchant merchant = (Merchant) event_inventory.getHolder();
            List<MerchantRecipe> recipes = merchant.getRecipes();

            JSONObject trader = new JSONObject();
            trader.put("name", ((Entity) merchant).getName());
            trader.put("uuid", ((Entity) merchant).getUniqueId().toString());

            JSONArray trades = new JSONArray();
            for (MerchantRecipe recipe : recipes) {
               JSONObject json = new JSONObject();

               JSONArray want = new JSONArray();
               for (ItemStack item : recipe.getIngredients()) {
                  if (item.getType().toString() != "AIR") {
                     want.add(new Gson().toJsonTree(item));
                  }
               }

               json.put("want", want);

               JSONObject get = new JSONObject();

               String get_name = recipe.getResult().getType().toString();
               String get_count = Integer.toString(recipe.getResult().getAmount());

               get.put("name", get_name);
               get.put("count", get_count);

               if (!recipe.getResult().getEnchantments().isEmpty()) {
                  JSONObject enchants_json = new JSONObject();
                  Map<Enchantment, Integer> give_enchants = recipe.getResult().getEnchantments();

                  for (Map.Entry<Enchantment, Integer> enchantment : give_enchants.entrySet()) {
                     String enchantment_name = enchantment.getKey().toString();
                     Integer enchantment_level = enchantment.getValue();
                     enchants_json.put(enchantment_name, enchantment_level);
                  }
                  get.put("enchants", enchants_json);
               }

               json.put("get", get);
               trades.add(json);
            }
            trader.put("trades", trades);

            Database database = Database.getInstance();
            database.updateVillager(player.getUniqueId(), trader);
            Bukkit.broadcastMessage(trader.toJSONString());
         }
      }
   }
}