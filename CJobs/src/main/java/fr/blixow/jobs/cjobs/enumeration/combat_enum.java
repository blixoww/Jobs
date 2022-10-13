package fr.blixow.jobs.cjobs.enumeration;

import org.bukkit.entity.EntityType;

public enum combat_enum {
    PLAYER(200),
    WITHER(150),
    MUSHROOM_COW(100),
    PIG_ZOMBIE(50),
    CREEPER(40),
    ENDERMAN(40),
    BLAZE(40),
    SKELETON(30),
    WITCH(30),
    SPIDER(30),
    CAVE_SPIDER(30),
    WOLF(20),
    ZOMBIE(20),
    SILVERFISH(20),
    SLIME(20),
    PIG(5),
    SHEEP(5),
    COW(5),
    CHICKEN(5),
    VILLAGER(5);

    public double xp;
    combat_enum(double xp) {
        this.xp = xp;
    }

    public double getXp() {
        return xp;
    }

    public static double getValue(EntityType entityType){
        try {
            return  combat_enum.valueOf(entityType.name()).getXp();
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return 0;
    }

    public static boolean exists(EntityType entityType){
        try { combat_enum.valueOf(entityType.name()).getXp(); return true; } catch (Exception ignored){}
        return false;
    }


}
