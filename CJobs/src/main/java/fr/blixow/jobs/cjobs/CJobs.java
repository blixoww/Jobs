package fr.blixow.jobs.cjobs;

import fr.blixow.jobs.cjobs.commands.jobs.JobsCommand;
import fr.blixow.jobs.cjobs.listeners.*;
import fr.blixow.jobs.cjobs.managers.JobsManager;
import fr.blixow.jobs.cjobs.utils.FileUtils;
import fr.blixow.jobs.cjobs.utils.JobsExperienceData;
import fr.blixow.jobs.cjobs.utils.PlayerJobs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CJobs extends JavaPlugin {

    private static CJobs instance;
    private JobsManager jobsManager;
    private JobsExperienceData jobsExperienceData;
    private FileConfiguration configFile;
    private FileUtils jobsDataFile;


    @Override
    public void onEnable() {
        configFile = getConfig();
        instance = this;
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[CJobs] Activation du plugins");
        init();
        initJobsEventCommands();
        saveConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[CJobs] Désactivation du plugins");
        for (Player player : Bukkit.getOnlinePlayers()) {
            savePlayerData(player);
        }
    }

    private void init(){
        this.jobsDataFile = new FileUtils("experienceData", getDataFolder(), "§8[§§cMétier§8]", CJobs.getInstance());
        this.jobsManager = new JobsManager();
        this.jobsExperienceData = new JobsExperienceData(jobsDataFile);
    }

    public void savePlayerData(Player player) {
            PlayerJobs playerJobs = CJobs.getInstance().getJobsManager().getPlayerJobsHashMap().get(player.getUniqueId());
            CJobs.getInstance().getFileFC().set(player.getName() + ".farming.xp", playerJobs.getFarming_xp());
            CJobs.getInstance().getFileFC().set(player.getName() + ".mining.xp", playerJobs.getMining_xp());
            CJobs.getInstance().getFileFC().set(player.getName() + ".foraging.xp", playerJobs.getForaging_xp());
            CJobs.getInstance().getFileFC().set(player.getName() + ".combat.xp", playerJobs.getCombat_xp());
            CJobs.getInstance().getFileFC().set(player.getName() + ".fishing.xp", playerJobs.getFishing_xp());
            CJobs.getInstance().getFileFC().set(player.getName() + ".enchanting.xp", playerJobs.getEnchanting_xp());
            CJobs.getInstance().saveConfig();
    }

    private void initJobsEventCommands(){

        /*  =====  Events  ======  */

        PluginManager pm = Bukkit.getPluginManager();

        // Event basique
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new LeaveEvent(), this);

        // Starting events
        pm.registerEvents(new JobsEventLaunch(), this);

        // Event custom
        pm.registerEvents(new FarmingEvents(), this);
        pm.registerEvents(new MiningEvents(), this);
        pm.registerEvents(new WoodcuttingEvents(), this);
        pm.registerEvents(new CombatEvents(), this);


        getCommand("cjobs").setExecutor(new JobsCommand());
        getCommand("cjobs").setTabCompleter(new JobsCommand());

    }

    public static CJobs getInstance() {
        return instance;
    }
    public FileConfiguration getFileFC() {
        return this.configFile;
    }
    public JobsManager getJobsManager() {
        return jobsManager;
    }
    public FileUtils getJobsDataFile() { return jobsDataFile; }
    public JobsExperienceData getJobsExperienceData() { return jobsExperienceData; }

}
