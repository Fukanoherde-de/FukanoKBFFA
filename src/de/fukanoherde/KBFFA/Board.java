/*
 * Erstellt am 22.11.2020 um 15:02
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.KBFFA
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.KBFFA;


import de.fukanoherde.FileSystem.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Board {


    public void setScoreboard(Player p){

        Scoreboard bord = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = bord.registerNewObjective("stats", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(Config.getValue("SB.Name").toString().replace("&", "§"));


        Team map = bord.registerNewTeam("maps");
        Team mapchange = bord.registerNewTeam("mapchange");
        Team kit = bord.registerNewTeam("kit");
        Team kitchange = bord.registerNewTeam("kitchange");


        obj.getScore(Config.getValue("SB.Map").toString().replace("&", "§")).setScore(12);
        obj.getScore("§1").setScore(11);
        obj.getScore("§f").setScore(10);
        obj.getScore(Config.getValue("SB.MapChange").toString().replace("&", "§")).setScore(9);
        obj.getScore("§2").setScore(8);
        obj.getScore("§e").setScore(7);
        obj.getScore(Config.getValue("SB.Kit").toString().replace("&", "§")).setScore(6);
        obj.getScore("§3").setScore(5);
        obj.getScore("§d").setScore(4);
        obj.getScore(Config.getValue("SB.KitChange").toString().replace("&", "§")).setScore(3);
        obj.getScore("§4").setScore(2);
        obj.getScore("§c").setScore(1);
        obj.getScore(Config.getValue("SB.Team").toString().replace("&", "§")).setScore(0);


        map.addEntry("§1");
        map.setPrefix("§6" + KBFFA.MapName);

        mapchange.addEntry("§2");
        mapchange.setPrefix("§6" + KBFFA.MapChange);

        kit.addEntry("§3");
        kit.setPrefix("§6" + KBFFA.KitName);

        kitchange.addEntry("§4");
        kitchange.setPrefix("§6" + KBFFA.KitChange);






        p.setScoreboard(bord);
    }

    public void updateSB(Player p){
        Scoreboard bord = p.getScoreboard();
        Team map = bord.getTeam("maps");
        Team mapchange = bord.getTeam("mapchange");
        Team kit = bord.getTeam("kit");
        Team kitchange = bord.getTeam("kitchange");


        map.setPrefix("§6" + KBFFA.MapName);
        mapchange.setPrefix("§6" + KBFFA.MapChange);
        kit.setPrefix("§6" + KBFFA.KitName);
        kitchange.setPrefix("§6" + KBFFA.KitChange);

    }
}
