package fr.blixow.jobs.cjobs.enumeration;

import org.bukkit.Material;

public enum farming_enum {
    CARROT(2),
    POTATO(2),
    CROPS(2),
    COCOA(4),
    NETHER_WARTS(4),
    PUMPKIN(5),
    MELON_BLOCK(5);

    double xp;

    public double getXp(){
        return this.xp;
    }

    farming_enum(double xp) { this.xp = xp; }

    public static double getValue(Material material){
        try {
            return  farming_enum.valueOf(material.name()).getXp();
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return 0;
    }

    public static boolean exists(Material material){
        try { farming_enum.valueOf(material.name()).getXp(); return true; } catch (Exception ignored){}
        return false;
    }

}
