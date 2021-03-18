package dev.yhdiamond.wispmininghealth;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {
    WispMiningHealth plugin = WispMiningHealth.plugin;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (sender.hasPermission("wispmininghealth.toggle")){
                if (args.length == 1){
                    if (args[0].equals("start")){
                        if (!plugin.isStarted) {
                            plugin.isStarted = true;
                            Bukkit.broadcastMessage(ChatColor.GREEN + "Wisp Mining Doubles your Health challenge has started!");
                            for (Player others : Bukkit.getOnlinePlayers()) {
                                others.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2);
                                WispMiningHealth.playerToMinedBlocks.put(others.getUniqueId(), 0);
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "The challenge is already started!");
                        }
                    } else if (args[0].equals("stop")){
                        if (plugin.isStarted) {
                            plugin.isStarted = false;
                            Bukkit.broadcastMessage(ChatColor.GREEN + "Wisp Mining Doubles your Health challenge has ended!");
                            for (Player others : Bukkit.getOnlinePlayers()) {
                                others.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                                others.setHealth(20);
                            }
                            WispMiningHealth.playerToMinedBlocks.clear();
                        } else {
                            sender.sendMessage(ChatColor.RED + "The plugin hasn't started!");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED+"/wispmininghealth <start/stop>");
                    }
                } else {
                    p.sendMessage(ChatColor.RED+"/wispmininghealth <start/stop>");
                }
            } else {
                p.sendMessage(ChatColor.RED+"You do not have the required permission to run this command!");
            }
        } else {
            sender.sendMessage(ChatColor.RED+"This command is for players only!");
        }

        return true;
    }
}