package fr.blixow.jobs.cjobs.listeners;

import fr.blixow.jobs.cjobs.CJobs;
import fr.blixow.jobs.cjobs.managers.JobsManager;
import fr.blixow.jobs.cjobs.utils.PlayerJobs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        JobsManager jobsManager = CJobs.getInstance().getJobsManager();
        if (CJobs.getInstance().getFileFC().contains(player.getName())) {
            PlayerJobs playerJobs = new PlayerJobs(player.getUniqueId(), CJobs.getInstance().getFileFC().getDouble(player.getName() + ".farming.xp"), CJobs.getInstance().getFileFC().getDouble(player.getName() + ".mining.xp"), CJobs.getInstance().getFileFC().getDouble(player.getName() + ".foraging.xp"), CJobs.getInstance().getFileFC().getDouble(player.getName() + ".combat.xp"), CJobs.getInstance().getFileFC().getDouble(player.getName() + ".fishing.xp"), CJobs.getInstance().getFileFC().getDouble(player.getName() + ".enchanting.xp"));
            CJobs.getInstance().getJobsManager().getPlayerJobsHashMap().put(player.getUniqueId(), playerJobs);
        } else {
            jobsManager.loadPlayerData(player);
        }
    }

}
