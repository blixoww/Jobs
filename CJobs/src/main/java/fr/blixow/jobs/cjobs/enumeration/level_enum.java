package fr.blixow.jobs.cjobs.enumeration;

import fr.blixow.jobs.cjobs.utils.PlayerJobs;

public enum level_enum {

    level0(0),
    level1(50),
    level2(175),
    level3(375),
    level4(675),
    level5(1175),
    level6(1925),
    level7(2925),
    level8(4425),
    level9(6425),
    level10(9925),
    level11(14925),
    level12(22425),
    level13(32425),
    level14(47425),
    level15(67425),
    level16(97425),
    level17(147425),
    level18(222425),
    level19(322425),
    level20(522425),
    level21(822425),
    level22(1222425),
    level23(1722425),
    level24(2322425),
    level25(3022425),
    level26(3822425),
    level27(4722425),
    level28(5722425),
    level29(6822425),
    level30(8022425),
    level31(9322425),
    level32(10722425),
    level33(12222425),
    level34(13822425),
    level35(15522425),
    level36(17322425),
    level37(19222425),
    level38(21222425),
    level39(23322425),
    level40(25522425),
    level41(27822425),
    level42(30222425),
    level43(32722425),
    level44(35322425),
    level45(38022425),
    level46(40822425),
    level47(43722425),
    level48(46722425),
    level49(49822425),
    level50(53022425),
    level51(56322425),
    level52(59722425),
    level53(63222425),
    level54(66822425),
    level55(70522425),
    level56(74322425),
    level57(78222425),
    level58(82222425),
    level59(86322425),
    level60(90522425),
    level61(94822425),
    level62(99222425),
    level63(103722425),
    level64(108322425),
    level65(113022425),
    level66(117822425),
    level67(122722425),
    level68(127722425),
    level69(132822425),
    level70(138022425),
    level71(143322425),
    level72(148722425),
    level73(154222425),
    level74(159822425),
    level75(165522425),
    level76(171322425),
    level77(177222425),
    level78(183222425),
    level79(189322425),
    level80(195522425),
    level81(201822425),
    level82(208222425),
    level83(214722425),
    level84(221322425),
    level85(228022425),
    level86(234822425),
    level87(241722425),
    level88(248722425),
    level89(255822425),
    level90(263022425),
    level91(270322425),
    level92(277722425),
    level93(285222425),
    level94(292822425),
    level95(300522425),
    level96(308322425),
    level97(316222425),
    level98(324222425),
    level99(332322425),
    level100(340522425);

    double xp;

    public double getXp() {
        return this.xp;
    }

    level_enum(double xp) {
        this.xp = xp;
    }

    public static int getGlobalLevel(PlayerJobs playerJobs) {
        int mining_level = getLevel(playerJobs.getMining_xp());
        int farming_level = getLevel(playerJobs.getFarming_xp());
        int foraging_level = getLevel(playerJobs.getForaging_xp());
        int combat_level = getLevel(playerJobs.getCombat_xp());
        int fishing_level = getLevel(playerJobs.getFishing_xp());
        int enchanting_level = getLevel(playerJobs.getEnchanting_xp());
        return (mining_level + farming_level + foraging_level + combat_level + fishing_level + enchanting_level) / 6;
    }

    public static int getGlobalLevel(int mining_level, int farming_level, int foraging_level, int combat_level, int fishing_level, int enchanting_level) {
        return (mining_level + farming_level + foraging_level + combat_level + fishing_level + enchanting_level) / 6;
    }

    public static int getLevel(double playerXP) {

        boolean found = false;
        int baseLevel = 0;
        int nextLevel = 1;
        try {
            while (nextLevel < 101 && !found) {
                double baseXp = level_enum.valueOf("level" + baseLevel).getXp();
                double nextXp = level_enum.valueOf("level" + nextLevel).getXp();

                //Messages.debugMessages("[" + baseLevel + "/" + nextLevel + "] " + "§a" + baseXp + "§f/§e"+playerXP + "§f/§b" + nextXp);

                if (baseXp <= playerXP && playerXP < nextXp) {
                    return baseLevel;
                }
                baseLevel++;
                nextLevel++;
            }
        } catch (Exception exception) {
            return -1;
        }
        return baseLevel;
    }

}
