package fr.blixow.jobs.cjobs.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class CheckUtils {

    public static boolean isPlayerExist(String name) {
        return isPlayerOnline(name) || isPlayerOffline(name);
    }

    public static boolean isPlayerOnline(String name) {
        try {
            Player player = Bukkit.getPlayerExact(name);
            return true;
        } catch(Exception ignored) {}
        return false;
    }

    public static boolean isPlayerOffline(String name) {
        try {
            OfflinePlayer player = Bukkit.getOfflinePlayer(name);
            return true;
        } catch(Exception ignored) {}
        return false;
    }


    public static String target(String message, String targetName){
        return message.replaceAll("\\{target}", targetName);
    }

}
