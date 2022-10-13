package fr.blixow.jobs.cjobs.utils;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {


    private File file;
    private FileConfiguration fc;
    private String defaultPrefix;

    public FileUtils(String fileName, File dataFolder, String defaultPrefix){
        this.defaultPrefix = defaultPrefix;
        File file = new File(dataFolder, fileName + ".yml");
        if (!file.exists()) { file.getParentFile().mkdirs(); }
        this.file = file;
        FileConfiguration fileConfiguration = new YamlConfiguration();
        try { fileConfiguration.load(file); } catch (IOException | InvalidConfigurationException e) { e.printStackTrace(); }
        this.fc = fileConfiguration;
    }

    public FileUtils(String fileName, File dataFolder, String defaultPrefix, JavaPlugin javaPlugin){
        this.defaultPrefix = defaultPrefix;
        File file = new File(dataFolder, fileName + ".yml");
        if(!file.exists()){ javaPlugin.saveResource(fileName + ".yml", true); }
        try {
            this.file = file;
            this.fc = YamlConfiguration.loadConfiguration(file);
        } catch (Exception exception){ exception.printStackTrace(); }
    }

    public ConfigurationSection getConfigurationSection(String path){

        try {
            if(contains(path)){
                System.out.println("Section path = " + path);
                return this.fc.getConfigurationSection(path);
            }
        } catch (Exception ignored){}
        return null;
    }

    public String getString(String path, String defaultMessage, boolean prefix){
        String prefixMessage = "";
        if(prefix){
            try { prefixMessage = getString("messages.prefix", defaultPrefix, false);
            } catch (Exception exception){ exception.printStackTrace(); }
        }
        try {
            if(fc.contains(path)){ return prefixMessage + fc.getString(path); }
        } catch (Exception exception){ exception.printStackTrace(); }
        return prefixMessage + defaultMessage;
    }

    public String getString(String path){
        try { if(fc.contains(path)){ return fc.getString(path); } } catch (Exception exception){ exception.printStackTrace(); }
        return "";
    }

    public double getDouble(String path, double defaultValue){
        try {
            if(fc.contains(path)){
                return fc.getDouble(path);
            }
        } catch (Exception exception){ exception.printStackTrace(); }
        return defaultValue;
    }

    public double getDouble(String path){
        return getDouble(path, 0);
    }

    public long getLong(String path, long defaultValue){
        try { if(fc.contains(path)){ return fc.getLong(path); } } catch (Exception exception){ exception.printStackTrace(); }
        return defaultValue;
    }

    public long getLong(String path){
        return getLong(path, 0);
    }

    public List<String> getStringList(String path){
        List<String> stringList = new ArrayList<>();
        if(contains(path)){ try { stringList = fc.getStringList(path); } catch (Exception exception){ exception.printStackTrace(); } }
        return stringList;
    }

    public boolean contains(String path){
        try {
            return fc.contains(path);
        } catch (Exception ignored){}
        return false;

    }


    public void reloadFile(){
        if(this.fc != null){
            this.fc = YamlConfiguration.loadConfiguration(file);
        }
    }

    public void set(String path, Object value){
        this.fc.set(path, value);
        save();

    }

    public void save(){
        try { this.fc.save(file); reloadFile(); } catch (Exception exception) { exception.printStackTrace(); }
    }

    public FileConfiguration getFc() { return fc; }
    public File getFile(){ return file; }


}
