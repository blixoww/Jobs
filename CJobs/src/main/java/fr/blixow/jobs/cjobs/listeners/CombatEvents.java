package fr.blixow.jobs.cjobs.listeners;

import fr.blixow.jobs.cjobs.customEvents.CombatKillEvent;
import fr.blixow.jobs.cjobs.utils.PlayerJobs;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CombatEvents implements Listener {

    @EventHandler
    public void onKillingEntities(CombatKillEvent event) {
        PlayerJobs playerJobs = event.getPlayerJobs();
        double fxp = event.getXp();
        playerJobs.increaseCombatXP(fxp);
    }
}
