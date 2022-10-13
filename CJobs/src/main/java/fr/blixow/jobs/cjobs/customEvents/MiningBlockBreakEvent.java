package fr.blixow.jobs.cjobs.customEvents;

import fr.blixow.jobs.cjobs.CJobs;
import fr.blixow.jobs.cjobs.utils.PlayerJobs;
import fr.blixow.jobs.cjobs.enumeration.mining_enum;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.HashMap;

public class MiningBlockBreakEvent extends Event implements Cancellable {

    private Player player;
    private PlayerJobs playerJobs;
    private Block block;
    private double xp;

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;

    public MiningBlockBreakEvent(Player player, Block block){
        HashMap<Material, Double> map = CJobs.getInstance().getJobsExperienceData().getMiningBreak();
        this.player = player;
        this.playerJobs = CJobs.getInstance().getJobsManager().getFromPlayer(player);
        this.block = block;
        this.xp = map.containsKey(block.getType()) ? map.get(block.getType()) : mining_enum.getValue(block.getType());
    }



    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerJobs getPlayerJobs() {
        return playerJobs;
    }

    public Block getBlock() {
        return block;
    }

    public double getXp() {
        return xp;
    }
}
