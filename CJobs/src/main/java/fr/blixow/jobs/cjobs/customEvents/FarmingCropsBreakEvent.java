package fr.blixow.jobs.cjobs.customEvents;

import fr.blixow.jobs.cjobs.CJobs;
import fr.blixow.jobs.cjobs.utils.PlayerJobs;
import fr.blixow.jobs.cjobs.enumeration.farming_enum;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;


public class FarmingCropsBreakEvent extends Event implements Cancellable {

    private Player player;
    private PlayerJobs playerJobs;
    private Block block;
    private double xp;
    private BlockBreakEvent blockBreakEvent;

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;

    public FarmingCropsBreakEvent(Player player, Block block, BlockBreakEvent blockBreakEvent){
        this.player = player;
        this.playerJobs = CJobs.getInstance().getJobsManager().getFromPlayer(player);
        this.block = block;
        this.xp = CJobs.getInstance().getJobsExperienceData().getFarmingBreak().containsKey(block.getType()) ? CJobs.getInstance().getJobsExperienceData().getFarmingBreak().get(block.getType()) : farming_enum.getValue(block.getType());
        this.blockBreakEvent = blockBreakEvent;
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

    public BlockBreakEvent getBlockBreakEvent() { return blockBreakEvent; }
}
