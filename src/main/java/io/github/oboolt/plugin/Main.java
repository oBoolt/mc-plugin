package io.github.oboolt.plugin;

import io.github.oboolt.plugin.commands.VaultCommand;
import io.github.oboolt.plugin.listeners.JoinLeaveListener;
import io.github.oboolt.plugin.listeners.VaultListener;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        // Commands
        getCommand("vault").setExecutor(new VaultCommand());

        // Listeners
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new VaultListener(), this);

        getComponentLogger().info("Plugin started successfully");
    }

    @Override
    public void onLoad() {
        getComponentLogger().info("Plugin starting...");
    }

    @Override
    public void onDisable() {
        getComponentLogger().info("Plugin ended");
    }

    public static Main getPlugin() {
        return plugin;
    }

}
