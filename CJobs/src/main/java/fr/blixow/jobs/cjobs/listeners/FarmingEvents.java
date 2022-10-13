package fr.blixow.jobs.cjobs.listeners;


import fr.blixow.jobs.cjobs.CJobs;
import fr.blixow.jobs.cjobs.customEvents.FarmingCropsBreakEvent;
import fr.blixow.jobs.cjobs.utils.PlayerJobs;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class FarmingEvents implements Listener {

    private boolean isFullyGrown(Block block) {
        if (block == null) {
            return false;
        }
        //Messages.debugDevMessages("Block data = " + block.getData());
        try {
            switch (block.getType()) {
                case COCOA:
                    return block.getData() >= 9;
                case NETHER_WARTS:
                    return block.getData() == 3;
                case MELON_BLOCK:
                    return true;
                case PUMPKIN:
                    return true;
                default:
                    return block.getData() == 7;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;

    }

    private List<Material> hoeList = Arrays.asList(
            Material.DIAMOND_HOE,
            Material.IRON_HOE,
            Material.GOLD_HOE,
            Material.STONE_HOE,
            Material.WOOD_HOE
    );

    @EventHandler
    public void onCropsBreak(FarmingCropsBreakEvent event) {
        PlayerJobs playerJobs = event.getPlayerJobs();
        Block blockBroken = event.getBlock();
        try {
            boolean holdingHoe = hoeList.contains(event.getPlayer().getInventory().getItemInHand().getType());
            boolean canAutoReplant = true; // todo : check if farming level > X
            if (isFullyGrown(blockBroken)) {
                double fxp = event.getXp();
                playerJobs.increaseFarmingXP(fxp);
                if (holdingHoe && canAutoReplant) {
                    replantCrops(blockBroken.getType(), blockBroken.getLocation());
                }
            } else {
                if (holdingHoe && canAutoReplant) {
                    event.getBlockBreakEvent().setCancelled(true);
                }
            }
            //Messages.debugPlayerMessages(player, "+ " + fxp + " farming xp");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void replantCrops(Material material, Location location) {
        if (material.equals(Material.MELON_BLOCK) || material.equals(Material.PUMPKIN)) {
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                location.getBlock().setType(material);
            }
        }.runTaskLater(CJobs.getInstance(), 5L);
    }

}
