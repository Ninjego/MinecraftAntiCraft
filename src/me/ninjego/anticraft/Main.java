package me.ninjego.anticraft;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
    @EventHandler
    public void craftItem(PrepareItemCraftEvent e) {
        Material itemType = e.getRecipe().getResult().getType();
        @SuppressWarnings("deprecation")
		Byte itemData = e.getRecipe().getResult().getData().getData();
        if(itemType==Material.ENDER_CHEST||itemType==Material.HOPPER||(itemType==Material.GOLDEN_APPLE&&itemData==1||itemType==Material.MAGMA_CREAM)) {
            e.getInventory().setResult(new ItemStack(Material.AIR));
            for(HumanEntity he:e.getViewers()) {
                if(he instanceof Player) {
                    ((Player)he).sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("cannotcraft-message")));
                }
            }
        }
    }
}
