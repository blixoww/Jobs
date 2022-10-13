package fr.blixow.jobs.cjobs.customEvents;

import fr.blixow.jobs.cjobs.CJobs;
import fr.blixow.jobs.cjobs.enumeration.combat_enum;
import fr.blixow.jobs.cjobs.utils.PlayerJobs;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDeathEvent;

public class CombatKillEvent extends Event implements Cancellable {
    private Player player;
    private PlayerJobs playerJobs;
    private double xp;
    private EntityDeathEvent entityDeathEvent;
    private EntityType entityType;
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;

    public CombatKillEvent(Player player, EntityType entityType, EntityDeathEvent entityDeathEvent) {
        this.player = player;
        this.playerJobs = CJobs.getInstance().getJobsManager().getFromPlayer(player);
        this.xp = CJobs.getInstance().getJobsExperienceData().getEntityKill().containsKey(entityDeathEvent.getEntityType()) ? CJobs.getInstance().getJobsExperienceData().getEntityKill().get(entityDeathEvent.getEntityType()) : combat_enum.getValue(entityDeathEvent.getEntityType());
        this.entityDeathEvent = entityDeathEvent;
        this.entityType = entityType;
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

    public EntityType getEntityType() {
        return entityType;
    }

    public EntityDeathEvent getEntityDeathEvent() {
        return entityDeathEvent;
    }
}
