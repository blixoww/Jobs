package fr.blixow.jobs.cjobs.commands.jobs;

import fr.blixow.jobs.cjobs.CJobs;
import fr.blixow.jobs.cjobs.managers.JobsManager;
import fr.blixow.jobs.cjobs.utils.CheckUtils;
import fr.blixow.jobs.cjobs.utils.ConvertUtils;
import fr.blixow.jobs.cjobs.utils.PlayerJobs;
import me.synatiks.jobs.cjobs.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class JobsCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            JobsManager jobsManager = CJobs.getInstance().getJobsManager();
            PlayerJobs playerJobs = jobsManager.getFromPlayer(player);
            if (args.length < 2) {
                if (args.length == 0) {
                    Bukkit.dispatchCommand(player, "cjobs " + player.getName());
                    return true;
                } else {
                    final Player targetPlayer = Bukkit.getPlayer(args[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(CheckUtils.target("§7Le joueur §c{target} §7n'a pas été trouvé.", args[0]));
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    PlayerJobs targetJobs = jobsManager.getFromPlayer(target);
                    targetJobs.sendMessages(player);
                    return true;
                }
            } else {
                if (args.length == 4) {
                    final Player targetPlayer = Bukkit.getPlayer(args[0]);
                    if (!(targetPlayer == null)) {
                        if (!ConvertUtils.isDouble(args[3]) && !ConvertUtils.isDoubleFormated(args[3])) {
                            player.sendMessage("Le troisième argumente doit être une valeur entière (double) ou une valeure entière formattés (100k, 100M, 100B)");
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        PlayerJobs targetJobs = jobsManager.getFromPlayer(target);
                        double db = ConvertUtils.isDoubleFormated(args[3]) ? ConvertUtils.getDoubleFromFormatted(args[3]) : Double.parseDouble(args[3]);
                        switch (args[1]) {
                            case "mining":
                                if (args[2].equalsIgnoreCase("add")) {
                                    targetJobs.increaseMiningXP(db);
                                } else if (args[2].equalsIgnoreCase("remove")) {
                                    targetJobs.decreaseMiningXP(db);
                                } else if (args[2].equalsIgnoreCase("set")) {
                                    targetJobs.setMiningXP(db);
                                } else {
                                    player.sendMessage("§7Les actions possible sont : §eadd§f/§eremove§f/§eset");
                                }
                                break;
                            case "farming":
                                if (args[2].equalsIgnoreCase("add")) {
                                    targetJobs.increaseFarmingXP(db);
                                } else if (args[2].equalsIgnoreCase("remove")) {
                                    targetJobs.decreaseFarmingXP(db);
                                } else if (args[2].equalsIgnoreCase("set")) {
                                    targetJobs.setFarmingXP(db);
                                } else {
                                    player.sendMessage("§7Les actions possible sont : §eadd§f/§eremove§f/§eset");
                                }
                                break;
                            case "foraging":
                                if (args[2].equalsIgnoreCase("add")) {
                                    targetJobs.increaseForagingXP(db);
                                } else if (args[2].equalsIgnoreCase("remove")) {
                                    targetJobs.decreaseForagingXP(db);
                                } else if (args[2].equalsIgnoreCase("set")) {
                                    targetJobs.setForagingXP(db);
                                } else {
                                    player.sendMessage("§7Les actions possible sont : §eadd§f/§eremove§f/§eset");
                                }
                                break;
                            case "combat":
                                if (args[2].equalsIgnoreCase("add")) {
                                    targetJobs.increaseCombatXP(db);
                                } else if (args[2].equalsIgnoreCase("remove")) {
                                    targetJobs.decreaseCombatXP(db);
                                } else if (args[2].equalsIgnoreCase("set")) {
                                    targetJobs.setCombatXP(db);
                                } else {
                                    player.sendMessage("§7Les actions possible sont : §eadd§f/§eremove§f/§eset");
                                }
                                break;
                            case "fishing":
                                if (args[2].equalsIgnoreCase("add")) {
                                    targetJobs.increaseFishingXP(db);
                                } else if (args[2].equalsIgnoreCase("remove")) {
                                    targetJobs.decreaseFishingXP(db);
                                } else if (args[2].equalsIgnoreCase("set")) {
                                    targetJobs.setFishingXP(db);
                                } else {
                                    player.sendMessage("§7Les actions possible sont : §eadd§f/§eremove§f/§eset");
                                }
                                break;
                            case "enchanting":
                                if (args[2].equalsIgnoreCase("add")) {
                                    targetJobs.increaseEnchantingXP(db);
                                } else if (args[2].equalsIgnoreCase("remove")) {
                                    targetJobs.decreaseEnchantingXP(db);
                                } else if (args[2].equalsIgnoreCase("set")) {
                                    targetJobs.setEnchantingXP(db);
                                } else {
                                    player.sendMessage("§7Les actions possible sont : §eadd§f/§eremove§f/§eset");
                                }
                                break;
                            default:
                                player.sendMessage("§7Le métier §c" + args[1] + " §7n'est pas reconnu.");
                                break;
                        }

                    } else {
                        player.sendMessage("§7Le joueur §c" + args[0] + " §7n'est pas connecté.");
                    }
                } else {
                    player.sendMessage("/cjobs <player> <jobs> <action> <amount>");
                }
            }
            return true;
        }
        sender.sendMessage("§cVous devez être un joueur pour effectuer cette commande.");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = new ArrayList<>();
        ArrayList<String> useful = new ArrayList<>();
        Player player = (Player) sender;
        if (args.length == 1 || args.length > 4) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                useful.add(p.getName());
            }

        } else if (args.length > 1) {
            if (player.hasPermission("jobs.admin")) {
                if (args.length == 2) {
                    useful.add("mining");
                    useful.add("farming");
                    useful.add("foraging");
                    useful.add("combat");
                    useful.add("fishing");
                    useful.add("enchanting");
                } else if (args.length == 3) {
                    useful.add("add");
                    useful.add("remove");
                    useful.add("set");
                }
            } else {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    useful.add(p.getName());
                }
                useful.forEach(playerName -> {
                    if (playerName.toLowerCase().startsWith(args[0])) {
                        arguments.add(playerName);
                    }
                });
            }
        }
        useful.forEach(value -> {
            if (value.toLowerCase().startsWith(args[args.length - 1])) {
                arguments.add(value);
            }
        });
        return arguments;
    }
}
