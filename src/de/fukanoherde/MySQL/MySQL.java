/*
 * Erstellt am 21.11.2020 um 21:51
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.MySQL
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.MySQL;


import de.fukanoherde.FileSystem.Config;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    public static String host = Config.getValue("MySQL.Host").toString();
    public static String port = Config.getValue("MySQL.Port").toString();
    public static String database = Config.getValue("MySQL.Database").toString();
    public static String user = Config.getValue("MySQL.User").toString();
    public static String passwd = Config.getValue("MySQL.Password").toString();

    public static Connection con;

    public static void connect(){
        if (!isConnect()){
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database , user, passwd);
                Bukkit.getConsoleSender().sendMessage("§2MySQL erfolgreich verbunden!");
            }catch (SQLException e){
                Bukkit.getConsoleSender().sendMessage("§cLeider ist ein fehler beim herstellen der MySQL Verbindung! Grund: " + e);
            }
        }

    }

    public static void disconnect(){
        if (isConnect()){
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage("§2MySQL verbindung geschlossen!");
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("§cLeider konnte die MySQL verbindung nicht richtig geschlossen werden! Grund: " + e);
            }

        }

    }

    public static boolean isConnect(){
        return con != null;

    }

    public static void createTable(){
    if (isConnect()){
        try {
            con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Stats(UUID VARCHAR(10), Player VARCHAR(10), Kills INT(255) , Deaths INT(255))");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§cLeider konnte keine Table erstellt werden! Grund: " + e);

        }
    }

    }

    public static void update(String qry){
    if (isConnect()){
        try {
            con.createStatement().executeUpdate(qry);
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§cUpdate konnte nicht durchgeführt werden! Grund: " + e);
        }
    }

    }

    public static ResultSet getResult(String qry){
    if (isConnect()){
        try {
            return con.createStatement().executeQuery(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return null;
    }

}
