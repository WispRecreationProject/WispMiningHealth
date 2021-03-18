package dev.yhdiamond.wispmininghealth;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class MiningListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (!(e.getBlock().getType().equals(Material.GRASS) ||
        e.getBlock().getType().equals(Material.KELP) ||
        e.getBlock().getType().equals(Material.FERN) ||
        e.getBlock().getType().equals(Material.TALL_GRASS) ||
        e.getBlock().getType().equals(Material.LARGE_FERN) ||
        e.getBlock().getType().equals(Material.SEAGRASS) ||
        e.getBlock().getType().equals(Material.TALL_SEAGRASS)) && WispMiningHealth.isStarted) {
            if (WispMiningHealth.playerToMinedBlocks.get(e.getPlayer().getUniqueId()) != null) {
                if (WispMiningHealth.playerToMinedBlocks.get(e.getPlayer().getUniqueId()) >= 25) {
                    e.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(e.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() * 2);
                    WispMiningHealth.playerToMinedBlocks.put(e.getPlayer().getUniqueId(), 0);
                } else {
                    WispMiningHealth.playerToMinedBlocks.put(e.getPlayer().getUniqueId(), WispMiningHealth.playerToMinedBlocks.get(e.getPlayer().getUniqueId()) + 1);
                }
            } else {
                WispMiningHealth.playerToMinedBlocks.put(e.getPlayer().getUniqueId(), 0);
            }
        }
    }
}
