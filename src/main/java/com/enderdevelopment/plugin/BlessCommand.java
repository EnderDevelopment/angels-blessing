package com.enderdevelopment.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlessCommand implements CommandExecutor {
    private final Main plugin;

    public BlessCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Usage: /bless <player>");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("Player not found!");
            return false;
        }

        plugin.getConfig().set("players." + target.getUniqueId() + ".blessed", true);
        plugin.saveConfig();
        target.sendMessage("You have been blessed by Angel's Blessing!");
        sender.sendMessage(target.getName() + " has been blessed!");
        return true;
    }
}