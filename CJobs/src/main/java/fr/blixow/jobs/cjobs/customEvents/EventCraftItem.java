package fr.blixow.jobs.cjobs.customEvents;

import fr.blixow.jobs.cjobs.CJobs;
import fr.blixow.jobs.cjobs.enumeration.combat_enum;
import fr.blixow.jobs.cjobs.utils.PlayerJobs;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

public class EventCraftItem extends Event implements Cancellable {
    private Player player;
    private PlayerJobs playerJobs;
    private double xp;
    private PrepareItemCraftEvent prepareItemCraftEvent;
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;

    public EventCraftItem(Player player, PrepareItemCraftEvent prepareItemCraftEvent) {
        this.player = player;
        this.playerJobs = CJobs.getInstance().getJobsManager().getFromPlayer(player);
        //this.xp = CJobs.getInstance().getJobsExperienceData());
        this.prepareItemCraftEvent = prepareItemCraftEvent;
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

    public double getXp() {
        return xp;
    }

    public PrepareItemCraftEvent getPrepareItemCraftEvent() {
        return prepareItemCraftEvent;
    }
}
