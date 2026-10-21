package com.enderdevelopment.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnblessCommand implements CommandExecutor {
    private final Main plugin;

    public UnblessCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Usage: /unbless <player>");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("Player not found!");
            return false;
        }

        plugin.getConfig().set("players." + target.getUniqueId() + ".blessed", false);
        plugin.saveConfig();
        target.sendMessage("Your blessing has been removed!");
        sender.sendMessage(target.getName() + "'s blessing has been removed!");
        return true;
    }
}