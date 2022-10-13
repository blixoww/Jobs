package fr.blixow.jobs.cjobs.listeners;

import fr.blixow.jobs.cjobs.customEvents.*;
import fr.blixow.jobs.cjobs.enumeration.combat_enum;
import fr.blixow.jobs.cjobs.enumeration.farming_enum;
import fr.blixow.jobs.cjobs.enumeration.mining_enum;
import fr.blixow.jobs.cjobs.enumeration.woodcutting_enum;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.Inventory;

public class JobsEventLaunch implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        //player.sendMessage("Blocktype = " + event.getBlock().getType().name());
        if (farming_enum.exists(block.getType())) {
            FarmingCropsBreakEvent eventLauncher = new FarmingCropsBreakEvent(player, block, event);
            Bukkit.getPluginManager().callEvent(eventLauncher);
        } else if (mining_enum.exists(block.getType())) {
            MiningBlockBreakEvent eventLauncher = new MiningBlockBreakEvent(player, block);
            Bukkit.getPluginManager().callEvent(eventLauncher);
        } else if (woodcutting_enum.exists(block.getType())) {
            WoodcuttingBlockBreakEvent eventLauncher = new WoodcuttingBlockBreakEvent(player, block);
            Bukkit.getPluginManager().callEvent(eventLauncher);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        EntityType entityType = event.getEntityType();
        Player killer = event.getEntity().getKiller();
        if (combat_enum.exists(entityType) && (killer != null)) {
            CombatKillEvent eventLauncher = new CombatKillEvent(killer, entityType, event);
            Bukkit.getPluginManager().callEvent(eventLauncher);
        }
    }

//    @EventHandler
//    public void onCraftingItems(PrepareItemCraftEvent event) {
//        Player player = (Player) event.getView().getPlayer();
//        EventCraftItem eventLauncher = new EventCraftItem(player, event);
//        Bukkit.getPluginManager().callEvent(eventLauncher);
//    }

}
