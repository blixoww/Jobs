package fr.blixow.jobs.cjobs.listeners;

import fr.blixow.jobs.cjobs.CJobs;
import fr.blixow.jobs.cjobs.managers.JobsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        JobsManager jobsManager = CJobs.getInstance().getJobsManager();
        CJobs.getInstance().savePlayerData(player);
        jobsManager.remove(player);
    }

}
