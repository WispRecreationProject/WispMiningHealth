package dev.yhdiamond.wispmininghealth;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class WispMiningHealth extends JavaPlugin {
    public static WispMiningHealth plugin;
    public static boolean isStarted = false;
    public static Map<UUID, Integer> playerToMinedBlocks = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new MiningListener(), this);
        getCommand("wispmininghealth").setExecutor(new StartCommand());
        getCommand("wispmininghealth").setTabCompleter(new CommandComplete());
    }

}
