package com.enderdevelopment.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
    private final Main plugin;

    public EventListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("players." + player.getUniqueId() + ".blessed")) {
            player.sendMessage("You are protected by Angel's Blessing!");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("players." + player.getUniqueId() + ".blessed")) {
            player.sendMessage("Your protection has been removed.");
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            Player victim = (Player) event.getEntity();

            if (plugin.getConfig().getBoolean("players." + victim.getUniqueId() + ".blessed")) {
                if (isAlly(attacker, victim)) {
                    event.setCancelled(true);
                    attacker.sendMessage("You cannot attack your ally!");
                } else {
                    plugin.getConfig().set("players." + victim.getUniqueId() + ".blessed", false);
                    plugin.saveConfig();
                    victim.sendMessage("Your innocence has been broken!");
                }
            }
        }
    }

    private boolean isAlly(Player attacker, Player victim) {
        // Implement ally detection logic here
        return false;
    }
}