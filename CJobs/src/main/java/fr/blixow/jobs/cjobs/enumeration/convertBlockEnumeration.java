package fr.blixow.jobs.cjobs.enumeration;

import org.bukkit.Material;

public enum convertBlockEnumeration {

    CARROT(Material.CARROT_ITEM),
    POTATO(Material.POTATO_ITEM),
    CROPS(Material.WHEAT),
    COCOA(Material.COCOA),
    NETHER_WARTS(Material.NETHER_STALK),
    PUMPKIN(Material.PUMPKIN),
    MELON_BLOCK(Material.MELON_BLOCK),
    LOG2(Material.LOG_2),
    LOG_2(Material.LOG_2);

    Material displayableMaterial;

    public Material getDisplayableMaterials(){
        return this.displayableMaterial;
    }

    convertBlockEnumeration(Material displayableMaterial) { this.displayableMaterial = displayableMaterial; }

}
