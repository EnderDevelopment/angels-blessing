package com.enderdevelopment.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Angel's Blessing plugin enabled!");
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        getCommand("bless").setExecutor(new BlessCommand(this));
        getCommand("unbless").setExecutor(new UnblessCommand(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("Angel's Blessing plugin disabled!");
    }
}