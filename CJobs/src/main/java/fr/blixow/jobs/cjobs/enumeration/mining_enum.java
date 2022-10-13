package fr.blixow.jobs.cjobs.enumeration;

import org.bukkit.Material;

public enum mining_enum {

    STONE(0.5),
    COBBLESTONE(0.5),
    DIAMOND_ORE(50),
    GOLD_ORE(5),
    IRON_ORE(10),
    COAL_ORE(5),
    EMERALD_ORE(50);

    double xp;

    public double getXp(){
        return this.xp;
    }

    mining_enum(double xp) { this.xp = xp; }

    public static double getValue(Material material){
        try {
            return  mining_enum.valueOf(material.name()).getXp();
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return 0;
    }

    public static boolean exists(Material material){
        try { mining_enum.valueOf(material.name()).getXp(); return true; } catch (Exception ignored){}
        return false;
    }


}
