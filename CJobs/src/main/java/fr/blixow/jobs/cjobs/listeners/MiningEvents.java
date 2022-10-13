package fr.blixow.jobs.cjobs.listeners;

import fr.blixow.jobs.cjobs.customEvents.MiningBlockBreakEvent;
import fr.blixow.jobs.cjobs.utils.PlayerJobs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MiningEvents implements Listener {

    @EventHandler
    public void onMiningBreak(MiningBlockBreakEvent event){
        Player player = event.getPlayer();
        PlayerJobs playerJobs = event.getPlayerJobs();
        try {
            double fxp = event.getXp();
            playerJobs.increaseMiningXP(fxp);
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

}
