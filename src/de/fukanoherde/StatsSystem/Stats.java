/*
 * Erstellt am 21.11.2020 um 23:33
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.StatsSystem
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.StatsSystem;

import de.fukanoherde.MySQL.MySQL;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Stats {

    public static boolean inList(Player p){
        String uuid = p.getUniqueId().toString();
        ResultSet rs = MySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void addPlayer(Player p){
        String uuid = p.getUniqueId().toString();
        MySQL.update("INSERT INTO Stats(UUID, Player, Kills, Deaths) VALUES('" + p.getUniqueId() + "', '" + p.getName() + "', '0', '0')");
    }


    public static Integer getKills(Player p){
        if (inList(p)){
            String uuid = p.getUniqueId().toString();
            ResultSet rs = MySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
                try {
                    while (rs.next()) {
                        return rs.getInt("Kills");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
            }
        }else {
            addPlayer(p);
        }
        return 0;
    }

    public static Integer getDeaths(Player p){
        if (inList(p)){
            String uuid = p.getUniqueId().toString();
            ResultSet rs = MySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
            try {
                while (rs.next()) {
                    return rs.getInt("Deaths");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            addPlayer(p);
        }
        return 0;
    }

    public static void setKills(Player p, int kills){
        String uuid = p.getUniqueId().toString();

        if (kills < 0){
            kills = 0;
        }

        if (inList(p)){
            MySQL.update("UPDATE Stats SET Kills='" + kills +"' WHERE UUID'" + uuid + "'");
        }else {
            addPlayer(p);
        }
    }

    public static void setDeaths(Player p, int deaths){
        String uuid = p.getUniqueId().toString();

        if (deaths < 0){
            deaths = 0;
        }

        if (inList(p)){
            MySQL.update("UPDATE Stats SET Deaths='" + deaths +"' WHERE UUID'" + uuid + "'");
        }else {
            addPlayer(p);
        }
    }


    public static void addKills(Player p, int kills){
        String uuid = p.getUniqueId().toString();
        if (kills < 0){
            kills = 0;
        }
        int amount = kills + getKills(p);

        if (inList(p)){
            MySQL.update("UPDATE Stats SET Kills='" + amount +"' WHERE UUID'" + uuid + "'");
        }else {
            addPlayer(p);
        }
    }

    public static void addDeaths(Player p, int deaths){
        String uuid = p.getUniqueId().toString();
        if (deaths < 0){
            deaths = 0;
        }
        int amount = deaths + getDeaths(p);
        if (inList(p)){
            MySQL.update("UPDATE Stats SET Deaths='" + amount +"' WHERE UUID'" + uuid + "'");
        }else {
            addPlayer(p);
        }
    }

    public static void removeKills(Player p, int kills){
        String uuid = p.getUniqueId().toString();
        if (kills < 0){
            kills = 0;
        }
        int amount = getKills(p) - kills;

        if (inList(p)){
            MySQL.update("UPDATE Stats SET Kills='" + amount +"' WHERE UUID'" + uuid + "'");
        }else {
            addPlayer(p);
        }
    }

    public static void removeDeaths(Player p, int deaths){
        String uuid = p.getUniqueId().toString();
        if (deaths < 0){
            deaths = 0;
        }
        int amount = getDeaths(p) - deaths;
        if (inList(p)){
            MySQL.update("UPDATE Stats SET Deaths='" + amount +"' WHERE UUID'" + uuid + "'");
        }else {
            addPlayer(p);
        }
    }
}
