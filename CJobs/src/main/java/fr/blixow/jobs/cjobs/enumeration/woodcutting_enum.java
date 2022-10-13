package fr.blixow.jobs.cjobs.enumeration;

import org.bukkit.Material;

public enum woodcutting_enum {

    LOG(5),
    LOG_2(5),
    LOG2(5);

    double xp;

    public double getXp(){
        return this.xp;
    }

    woodcutting_enum(double xp) { this.xp = xp; }

    public static double getValue(Material material){
        try {
            return woodcutting_enum.valueOf(material.name()).getXp();
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return 0;
    }

    public static boolean exists(Material material){
        try { double xp = woodcutting_enum.valueOf(material.name()).getXp(); return true; } catch (Exception ignored){}
        return false;
    }

}
