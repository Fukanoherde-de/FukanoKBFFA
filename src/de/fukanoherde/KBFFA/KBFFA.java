/*
 * Erstellt am 21.11.2020 um 21:39
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.KBFFA
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.KBFFA;

import de.fukanoherde.FileSystem.Config;
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
