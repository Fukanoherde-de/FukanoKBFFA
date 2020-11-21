/*
 * Erstellt am 21.11.2020 um 21:39
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.KBFFA
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.KBFFA;

import de.fukanoherde.Commands.Stats_CMD;
import de.fukanoherde.FileSystem.Config;
import de.fukanoherde.Listener.AllListener;
import de.fukanoherde.Listener.DeathListener;
import de.fukanoherde.Listener.OnJoin;
import de.fukanoherde.MySQL.MySQL;
import de.fukanoherde.SpawnSystem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class KBFFA extends JavaPlugin {

    PluginManager pm = Bukkit.getPluginManager();
    private static KBFFA  instance;
    private static KBFFA  plugin;

    @Override
    public void onLoad() {
        instance= this;
        plugin = this;
    }

    @Override
    public void onEnable() {

        Config.loadFile();

        Bukkit.getConsoleSender().sendMessage("§6Fukano KBFFA wurde erfolgreich geladen!");

        //Listener
        this.pm.registerEvents(new OnJoin(), this);
        this.pm.registerEvents(new AllListener(),this);
        this.pm.registerEvents(new DeathListener(), this);


        //Commands
        getCommand("setspawn").setExecutor(new SpawnSystem());
        getCommand("stats").setExecutor(new Stats_CMD());


        // MySQL
        MySQL.connect();
        MySQL.createTable();
    }

    @Override
    public void onDisable() {

    }

    public static KBFFA  getInstance(){
        return instance;
    }
    public static KBFFA getPlugin(){
        return plugin;
    }
}
