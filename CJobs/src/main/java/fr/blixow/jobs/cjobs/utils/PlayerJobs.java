package fr.blixow.jobs.cjobs.utils;

import fr.blixow.jobs.cjobs.enumeration.level_enum;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerJobs {

    private final UUID playerUUID;
    private String playerName;
    private double farming_xp;
    private double mining_xp;
    private double foraging_xp;
    private double combat_xp;
    private double fishing_xp;
    private double enchanting_xp;

    public PlayerJobs(UUID uuid) {
        boolean exists = false;
        double data[] = new double[6];
        this.playerUUID = uuid;
        this.playerName = Bukkit.getPlayer(uuid) == null ? (Bukkit.getOfflinePlayer(uuid) == null ? "" : Bukkit.getOfflinePlayer(uuid).getName()) : Bukkit.getPlayer(uuid).getName();
        this.farming_xp = exists ? data[0] : 0;
        this.mining_xp = exists ? data[1] : 0;
        this.foraging_xp = exists ? data[2] : 0;
        this.combat_xp = exists ? data[3] : 0;
        this.fishing_xp = exists ? data[4] : 0;
        this.enchanting_xp = exists ? data[5] : 0;
    }

    public PlayerJobs(UUID uuid, double farming_xp, double mining_xp, double foraging_xp, double combat_xp, double fishing_xp, double enchanting_xp) {
        this.playerUUID = uuid;
        this.farming_xp = farming_xp;
        this.mining_xp = mining_xp;
        this.foraging_xp = foraging_xp;
        this.combat_xp = combat_xp;
        this.fishing_xp = fishing_xp;
        this.enchanting_xp = enchanting_xp;
    }

    public void increaseFarmingXP(double xp) {
        int base_level = level_enum.getLevel(getFarming_xp());
        this.farming_xp += xp;
        int updated_level = level_enum.getLevel(getFarming_xp());
        if (updated_level < 100) {
            int next_level = updated_level + 1;
            int xp_to_next = (int) (level_enum.valueOf("level" + next_level).getXp() - getFarming_xp());
            String xp_to_next_str = ConvertUtils.doubleToStringFormatted(xp_to_next);
            Messages.sendActionBar(this.playerUUID, "§b+" + ConvertUtils.doubleToStringFormatted(xp) + " xp de fermier §8(§fReste: §c" + xp_to_next_str + "§8)");
            if (base_level < updated_level) {
                Messages.sendMessages(playerUUID, "§8[§bMétier§8] §7Votre métier de fermier à évoluer §f(§a" + base_level + "§f§l ➜ §r§a" + updated_level + "§f)");
                int moneyReward = xp_to_next / 2;
                moneyReward = (int) (moneyReward + combat_xp / next_level);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + this.playerUUID + " " + moneyReward);
            }
        } else {
            Messages.sendActionBar(playerUUID, "§b+" + ConvertUtils.doubleToStringFormatted(xp) + " xp de fermier");
        }
    }

    public void increaseMiningXP(double xp) {
        int base_level = level_enum.getLevel(getMining_xp());
        this.mining_xp += xp;
        int updated_level = level_enum.getLevel(getMining_xp());
        if (updated_level < 100) {
            int next_level = updated_level + 1;
            int xp_to_next = (int) (level_enum.valueOf("level" + next_level).getXp() - getMining_xp());
            String xp_to_next_str = ConvertUtils.doubleToStringFormatted(xp_to_next);
            Messages.sendActionBar(this.playerUUID, "§b+" + ConvertUtils.doubleToStringFormatted(xp) + " xp de mineur §8(§fReste: §c" + xp_to_next_str + "§8)");
            if (base_level < updated_level) {
                Messages.sendMessages(playerUUID, "§8[§bMétier§8] §7Votre métier de mineur à évoluer §f(§a" + base_level + "§f§l ➜ §r§a" + updated_level + "§f)");
                int moneyReward = xp_to_next / 2;
                moneyReward = (int) (moneyReward + combat_xp / next_level);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + this.playerUUID + " " + moneyReward);
            }
        } else {
            Messages.sendActionBar(playerUUID, "§b+" + ConvertUtils.doubleToStringFormatted(xp) + " xp de mineur");
        }
    }

    public void increaseForagingXP(double xp) {
        int base_level = level_enum.getLevel(getForaging_xp());
        this.foraging_xp += xp;
        int updated_level = level_enum.getLevel(getForaging_xp());
        if (updated_level < 100) {

            int next_level = updated_level + 1;
            int xp_to_next = (int) (level_enum.valueOf("level" + next_level).getXp() - getForaging_xp());
            String xp_to_next_str = ConvertUtils.doubleToStringFormatted(xp_to_next);
            Messages.sendActionBar(this.playerUUID, "§b+" + ConvertUtils.doubleToStringFormatted(xp) + " xp de bûcheron §8(§fReste: §c" + xp_to_next_str + "§8)");
            if (base_level < updated_level) {
                Messages.sendMessages(playerUUID, "§8[§bMétier§8] §7Votre métier de bûcheron à évoluer §f(§a" + base_level + "§f§l ➜ §r§a" + updated_level + "§f)");
                int moneyReward = xp_to_next / 2;
                moneyReward = (int) (moneyReward + combat_xp / next_level);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + this.playerUUID + " " + moneyReward);
            }
        } else {
            Messages.sendActionBar(playerUUID, "§b+" + ConvertUtils.doubleToStringFormatted(xp) + " xp de bûcheron");
        }
    }

    public void increaseCombatXP(double xp) {
        int base_level = level_enum.getLevel(getCombat_xp());
        this.combat_xp += xp;
        int updated_level = level_enum.getLevel(getCombat_xp());
        if (updated_level < 100) {
            int next_level = updated_level + 1;
            int xp_to_next = (int) (level_enum.valueOf("level" + next_level).getXp() - getCombat_xp());
            String xp_to_next_str = ConvertUtils.doubleToStringFormatted(xp_to_next);
            Messages.sendActionBar(this.playerUUID, "§b+" + ConvertUtils.doubleToStringFormatted(xp) + " xp de combat §8(§fReste: §c" + xp_to_next_str + "§8)");
            if (base_level < updated_level) {
                Messages.sendMessages(playerUUID, "§8[§bMétier§8] §7Votre métier de combat à évoluer §f(§a" + base_level + "§f§l ➜ §r§a" + updated_level + "§f)");
                int moneyReward = xp_to_next / 2;
                moneyReward = (int) (moneyReward + combat_xp / next_level);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + this.playerUUID + " " + moneyReward);
            }
        } else {
            Messages.sendActionBar(playerUUID, "§b+" + ConvertUtils.doubleToStringFormatted(xp) + " xp de combat");
        }
    }

    public void increaseFishingXP(double xp) {
        int base_level = level_enum.getLevel(getFishing_xp());
        this.fishing_xp += xp;
        int updated_level = level_enum.getLevel(getFishing_xp());
        if (updated_level < 100) {
            int next_level = updated_level + 1;
            int xp_to_next = (int) (level_enum.valueOf("level" + next_level).getXp() - getFishing_xp());
            String xp_to_next_str = ConvertUtils.doubleToStringFormatted(xp_to_next);
            Messages.sendActionBar(this.playerUUID, "§b+" + ConvertUtils.doubleToStringFormatted(xp) + " xp de pêcheur §8(§fReste: §c" + xp_to_next_str + "§8)");
            if (base_level < updated_level) {
                Messages.sendMessages(playerUUID, "§8[§bMétier§8] §7Votre métier de pêcheur à évoluer §f(§a" + base_level + "§f§l ➜ §r§a" + updated_level + "§f)");
                int moneyReward = xp_to_next / 2;
                moneyReward = (int) (moneyReward + combat_xp / next_level);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + this.playerUUID + " " + moneyReward);
            }
        } else {
            Messages.sendActionBar(playerUUID, "§b+" + ConvertUtils.doubleToStringFormatted(xp) + " xp de pêcheur");
        }
    }

    public void increaseEnchantingXP(double xp) {
        int base_level = level_enum.getLevel(getEnchanting_xp());
        this.enchanting_xp += xp;
        int updated_level = level_enum.getLevel(getEnchanting_xp());
        if (updated_level < 100) {
            int next_level = updated_level + 1;
            int xp_to_next = (int) (level_enum.valueOf("level" + next_level).getXp() - getEnchanting_xp());
            String xp_to_next_str = ConvertUtils.doubleToStringFormatted(xp_to_next);
            Messages.sendActionBar(this.playerUUID, "§b+" + ConvertUtils.doubleToStringFormatted(xp) + " xp d'enchanteur §8(§fReste: §c" + xp_to_next_str + "§8)");
            if (base_level < updated_level) {
                Messages.sendMessages(playerUUID, "§8[§bMétier§8] §7Votre métier d'enchanteur à évoluer §f(§a" + base_level + "§f§l ➜ §r§a" + updated_level + "§f)");
                int moneyReward = xp_to_next / 2;
                moneyReward = (int) (moneyReward + combat_xp / next_level);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + this.playerUUID + " " + moneyReward);
            }
        } else {
            Messages.sendActionBar(playerUUID, "§b+" + ConvertUtils.doubleToStringFormatted(xp) + " xp d'enchanteur");
        }
    }

    public void decreaseMiningXP(double xp) {
        int base_level = level_enum.getLevel(getMining_xp());
        if (mining_xp < xp) {
            this.mining_xp = 0;
        } else {
            this.mining_xp -= xp;
        }
        int updated_level = level_enum.getLevel(getMining_xp());
        Messages.sendActionBar(this.playerUUID, "§b-" + ConvertUtils.doubleToStringFormatted(xp) + " xp de mineur");
        if (updated_level < 100) {
            if (base_level > updated_level) {
                Messages.sendMessages(playerUUID, "§8[§bMétier§8] §7Votre métier de mineur à été réduit §f(§c" + base_level + "§f§l ➜ §r§c" + updated_level + "§f)");
            }
        }
    }

    public void decreaseFarmingXP(double xp) {
        int base_level = level_enum.getLevel(getFarming_xp());
        if (farming_xp < xp) {
            this.farming_xp = 0;
        } else {
            this.farming_xp -= xp;
        }
        int updated_level = level_enum.getLevel(getFarming_xp());
        Messages.sendActionBar(this.playerUUID, "§b-" + ConvertUtils.doubleToStringFormatted(xp) + " xp de fermier");
        if (updated_level < 100) {
            if (base_level > updated_level) {
                Messages.sendMessages(playerUUID, "§8[§bMétier§8] §7Votre métier de fermier à été réduit §f(§c" + base_level + "§f§l ➜ §r§c" + updated_level + "§f)");
            }
        }
    }

    public void decreaseForagingXP(double xp) {
        int base_level = level_enum.getLevel(getForaging_xp());
        if (foraging_xp < xp) {
            this.foraging_xp = 0;
        } else {
            this.foraging_xp -= xp;
        }
        int updated_level = level_enum.getLevel(getForaging_xp());
        Messages.sendActionBar(this.playerUUID, "§b-" + ConvertUtils.doubleToStringFormatted(xp) + " xp de bûcheron");
        if (updated_level < 100) {
            if (base_level > updated_level) {
                Messages.sendMessages(playerUUID, "§8[§bMétier§8] §7Votre métier de bûcheron à été réduit §f(§c" + base_level + "§f§l ➜ §r§c" + updated_level + "§f)");
            }
        }
    }

    public void decreaseCombatXP(double xp) {
        int base_level = level_enum.getLevel(getCombat_xp());
        if (combat_xp < xp) {
            this.combat_xp = 0;
        } else {
            this.combat_xp -= xp;
        }
        int updated_level = level_enum.getLevel(getCombat_xp());
        Messages.sendActionBar(this.playerUUID, "§b-" + ConvertUtils.doubleToStringFormatted(xp) + " xp de combat");
        if (updated_level < 100) {
            if (base_level > updated_level) {
                Messages.sendMessages(playerUUID, "§8[§bMétier§8] §7Votre métier de combat à été réduit §f(§c" + base_level + "§f§l ➜ §r§c" + updated_level + "§f)");
            }
        }
    }

    public void decreaseFishingXP(double xp) {
        int base_level = level_enum.getLevel(getMining_xp());
        if (fishing_xp < xp) {
            this.fishing_xp = 0;
        } else {
            this.fishing_xp -= xp;
        }
        int updated_level = level_enum.getLevel(getMining_xp());
        Messages.sendActionBar(this.playerUUID, "§b-" + ConvertUtils.doubleToStringFormatted(xp) + " xp de pêcheur");
        if (updated_level < 100) {
            if (base_level > updated_level) {
                Messages.sendMessages(playerUUID, "§8[§bMétier§8] §7Votre métier de pêcheur à été réduit §f(§c" + base_level + "§f§l ➜ §r§c" + updated_level + "§f)");
            }
        }
    }

    public void decreaseEnchantingXP(double xp) {
        int base_level = level_enum.getLevel(getEnchanting_xp());
        if (enchanting_xp < xp) {
            this.enchanting_xp = 0;
        } else {
            this.enchanting_xp -= xp;
        }
        int updated_level = level_enum.getLevel(getEnchanting_xp());
        Messages.sendActionBar(this.playerUUID, "§b-" + ConvertUtils.doubleToStringFormatted(xp) + " xp d'enchanteur");
        if (updated_level < 100) {
            if (base_level > updated_level) {
                Messages.sendMessages(playerUUID, "§8[§bMétier§8] §7Votre métier d'enchanteur à été réduit §f(§c" + base_level + "§f§l ➜ §r§c" + updated_level + "§f)");
            }
        }
    }

    public void setMiningXP(double xp) {
        if (xp > mining_xp) {
            increaseMiningXP(xp - mining_xp);
        } else {
            if (xp < 0) {
                decreaseMiningXP(mining_xp);
            } else {
                decreaseMiningXP(mining_xp - xp);
            }
        }
    }

    public void setFarmingXP(double xp) {
        if (xp > farming_xp) {
            increaseFarmingXP(xp - farming_xp);
        } else {
            if (xp < 0) {
                decreaseFarmingXP(farming_xp);
            } else {
                decreaseFarmingXP(farming_xp - xp);
            }
        }
    }

    public void setForagingXP(double xp) {
        if (xp > foraging_xp) {
            increaseForagingXP(xp - foraging_xp);
        } else {
            if (xp < 0) {
                decreaseForagingXP(foraging_xp);
            } else {
                decreaseForagingXP(foraging_xp - xp);
            }
        }
    }

    public void setCombatXP(double xp) {
        if (xp > combat_xp) {
            increaseCombatXP(xp - combat_xp);
        } else {
            if (xp < 0) {
                decreaseCombatXP(combat_xp);
            } else {
                decreaseCombatXP(combat_xp - xp);
            }
        }
    }

    public void setFishingXP(double xp) {
        if (xp > fishing_xp) {
            increaseFishingXP(xp - fishing_xp);
        } else {
            if (xp < 0) {
                decreaseFishingXP(fishing_xp);
            } else {
                decreaseFishingXP(fishing_xp - xp);
            }
        }
    }

    public void setEnchantingXP(double xp) {
        if (xp > enchanting_xp) {
            increaseEnchantingXP(xp - enchanting_xp);
        } else {
            if (xp < 0) {
                decreaseEnchantingXP(enchanting_xp);
            } else {
                decreaseEnchantingXP(enchanting_xp - xp);
            }
        }
    }


    public HashMap<String, String> getLevels() {
        HashMap<String, String> maps = new HashMap<>();
        int farming_level = 0,
                mining_level = 0,
                foraging_level = 0,
                combat_level = 0,
                fishing_level = 0,
                enchanting_level = 0;
        try {
            farming_level = level_enum.getLevel(farming_xp);
            mining_level = level_enum.getLevel(mining_xp);
            foraging_level = level_enum.getLevel(foraging_xp);
            combat_level = level_enum.getLevel(combat_xp);
            fishing_level = level_enum.getLevel(fishing_xp);
            enchanting_level = level_enum.getLevel(enchanting_xp);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        maps.put("Paysan", farming_level + "|" + farming_xp);
        maps.put("Mineur", mining_level + "|" + mining_xp);
        maps.put("Bûcheron", foraging_level + "|" + foraging_xp);
        maps.put("Guerrier", combat_level + "|" + combat_xp);
        maps.put("Pêcheur", fishing_level + "|" + fishing_xp);
        maps.put("Enchanteur", enchanting_level + "|" + enchanting_xp);
        return maps;

    }

    @Override
    public String toString() {
        return "PlayerJobs{" +
                "playerUUID=" + playerUUID.toString() +
                ", farming_xp=" + farming_xp +
                ", mining_xp=" + mining_xp +
                ", foraging_xp=" + foraging_xp +
                ", combat_xp=" + combat_xp +
                ", fishing_xp=" + fishing_xp +
                ", enchanting_xp=" + enchanting_xp +
                '}';
    }

    public void sendMessages(Player player) {
        HashMap<String, String> maps = getLevels();
        double LevelXP = 0;

        for (Map.Entry<String, String> entry : maps.entrySet()) {
            String value = entry.getValue();
            LevelXP += ConvertUtils.isDouble(value.split("\\|")[1]) ? Double.parseDouble(value.split("\\|")[1]) : 0;
        }
        int Level = level_enum.getLevel((LevelXP / 10));
        player.sendMessage("§8§l§m-----§r§8§l[§r §cMétiers de " + Bukkit.getPlayer(this.playerUUID).getName() + " §8§l]§m-----");
        player.sendMessage("");
        player.sendMessage("§e» §3Niveau globale §7: §f" + Level);
        player.sendMessage("");
        maps.forEach((k, v) -> {
            try {
                String lvl = v.split("\\|")[0];
                String xp = v.split("\\|")[1];
                player.sendMessage("§e» §b" + k + " §7: §fNiveau " + lvl + " §8(§f" + ConvertUtils.tryFormatStringDouble(xp) + "§8)");
            } catch (Exception ignored) {
            }

        });
        player.sendMessage("");
    }


    public double getFarming_xp() {
        return farming_xp;
    }

    public double getCombat_xp() {
        return combat_xp;
    }

    public double getEnchanting_xp() {
        return enchanting_xp;
    }

    public double getFishing_xp() {
        return fishing_xp;
    }

    public double getForaging_xp() {
        return foraging_xp;
    }

    public double getMining_xp() {
        return mining_xp;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

}




