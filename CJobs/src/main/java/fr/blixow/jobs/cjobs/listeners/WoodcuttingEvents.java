package fr.blixow.jobs.cjobs.listeners;

import fr.blixow.jobs.cjobs.customEvents.WoodcuttingBlockBreakEvent;
import fr.blixow.jobs.cjobs.utils.PlayerJobs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WoodcuttingEvents implements Listener {

    @EventHandler
    public void onWoodBreak(WoodcuttingBlockBreakEvent event){

        Player player = event.getPlayer();
        PlayerJobs playerJobs = event.getPlayerJobs();
        try {
            double fxp = event.getXp();
            playerJobs.increaseForagingXP(fxp);
        } catch (Exception exception){
            exception.printStackTrace();
        }

    }

}
