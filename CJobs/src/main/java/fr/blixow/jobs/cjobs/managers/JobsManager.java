package fr.blixow.jobs.cjobs.managers;

import fr.blixow.jobs.cjobs.utils.PlayerJobs;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class JobsManager {

    HashMap<UUID, PlayerJobs> playerJobsHashMap;

    public JobsManager() {
        this.playerJobsHashMap = new HashMap<>();
    }

    public HashMap<UUID, PlayerJobs> getPlayerJobsHashMap() {
        return playerJobsHashMap;
    }

    public void remove(Player player) {
        playerJobsHashMap.remove(player.getUniqueId());
    }

    public PlayerJobs getFromPlayer(Player player) {
        if (playerJobsHashMap.containsKey(player.getUniqueId())) {
            return playerJobsHashMap.get(player.getUniqueId());
        }
        return null;
    }

    public void loadPlayerData(Player player) {
        PlayerJobs playerJobs;
        playerJobs = new PlayerJobs(player.getUniqueId());
        this.playerJobsHashMap.remove(player.getUniqueId());
        this.playerJobsHashMap.put(player.getUniqueId(), playerJobs);
    }
}
