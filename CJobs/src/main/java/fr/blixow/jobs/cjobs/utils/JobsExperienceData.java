package fr.blixow.jobs.cjobs.utils;

import fr.blixow.jobs.cjobs.CJobs;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class JobsExperienceData {

    private final HashMap<Material, Double> miningBreak;
    private final HashMap<Material, Double> farmingBreak;
    private final HashMap<Material, Double> foragingBreak;

    private final HashMap<EntityType, Double> entityKill;

    public JobsExperienceData(FileUtils fileUtils) {
        miningBreak = new HashMap<>();
        farmingBreak = new HashMap<>();
        foragingBreak = new HashMap<>();
        entityKill = new HashMap<>();
        loadBreakMining(fileUtils);
        loadBreakFarming(fileUtils);
        loadBreakForaging(fileUtils);
        loadKillEntity(fileUtils);
    }

    public void loadBreakMining(FileUtils fileUtils) {
        String path = "break.mining";
        ConfigurationSection configurationSection = fileUtils.getConfigurationSection(path);
        if (configurationSection != null) {
            for (String key : configurationSection.getKeys(false)) {
                try {
                    String newpath = path + "." + key;
                    Material material = Material.valueOf(key);
                    double value = fileUtils.getDouble(newpath);
                    if (value > 0) {
                        if (miningBreak.containsKey(material)) {
                            value = (value > miningBreak.get(material)) ? value : miningBreak.get(material);
                        }
                        miningBreak.put(material, value);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public void loadBreakFarming(FileUtils fileUtils) {
        String path = "break.farming";
        ConfigurationSection configurationSection = fileUtils.getConfigurationSection(path);
        if (configurationSection != null) {
            for (String key : configurationSection.getKeys(false)) {
                try {
                    String newpath = path + "." + key;
                    Material material = Material.valueOf(key);
                    double value = fileUtils.getDouble(newpath);
                    if (value > 0) {
                        if (farmingBreak.containsKey(material)) {
                            value = (value > farmingBreak.get(material)) ? value : farmingBreak.get(material);
                        }
                        farmingBreak.put(material, value);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public void loadBreakForaging(FileUtils fileUtils) {
        String path = "break.foraging";
        ConfigurationSection configurationSection = fileUtils.getConfigurationSection(path);
        if (configurationSection != null) {
            for (String key : configurationSection.getKeys(false)) {
                try {
                    String newpath = path + "." + key;
                    Material material = Material.valueOf(key);
                    double value = fileUtils.getDouble(newpath);
                    if (value > 0) {
                        if (foragingBreak.containsKey(material)) {
                            value = (value > foragingBreak.get(material)) ? value : foragingBreak.get(material);
                        }
                        foragingBreak.put(material, value);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public void loadKillEntity(FileUtils fileUtils) {
        String path = "kill.entities";
        ConfigurationSection configurationSection = fileUtils.getConfigurationSection(path);
        if (configurationSection != null) {
            for (String key : configurationSection.getKeys(false)) {
                try {
                    String newpath = path + "." + key;
                    EntityType entityType = EntityType.valueOf(key);
                    double value = fileUtils.getDouble(newpath);
                    if (value > 0) {
                        if (entityKill.containsKey(entityType)) {
                            value = (value > entityKill.get(entityType)) ? value : entityKill.get(entityType);
                        }
                        entityKill.put(entityType, value);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }


    public void printData(Player player) {
        miningBreak.forEach((k, v) -> {
            player.sendMessage("§c" + k + " §f:§c " + v);
        });
    }

    public void update(String path, double value) {
        FileUtils fileUtils = CJobs.getInstance().getJobsDataFile();
        fileUtils.set(path, value);
    }

    public HashMap<Material, Double> getMiningBreak() {
        return miningBreak;
    }

    public HashMap<Material, Double> getForagingBreak() {
        return foragingBreak;
    }

    public HashMap<Material, Double> getFarmingBreak() {
        return farmingBreak;
    }

    public HashMap<EntityType, Double> getEntityKill() {
        return entityKill;
    }
}
