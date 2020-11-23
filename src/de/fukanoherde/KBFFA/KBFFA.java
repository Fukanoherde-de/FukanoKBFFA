/*
 * Erstellt am 21.11.2020 um 21:39
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.KBFFA
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.KBFFA;

import de.fukanoherde.Commands.Kit_CMD;
import de.fukanoherde.Commands.Map_CMD;
import de.fukanoherde.Commands.Stats_CMD;
import de.fukanoherde.FileSystem.Config;
import de.fukanoherde.Listener.*;
import de.fukanoherde.SpawnSystem;
import de.statsapi.mysql.MySQL;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class KBFFA extends JavaPlugin {

    PluginManager pm = Bukkit.getPluginManager();
    private static KBFFA instance;
    private static KBFFA plugin;

    public static String MapName;
    public static int SpawnY;
    public static int KillY;
    public static String KitName;
    public static int KitChange;
    public static int MapChange;
    public static boolean enterHaken;
    public static boolean changeKit;
    public static boolean changeMap;
    public static int i;

    @Override
    public void onLoad() {
        instance = this;
        plugin = this;
    }
    @SuppressWarnings("deprecation")
    @Override
    public void onEnable() {


        enterHaken = false;
        changeKit = false;
        changeMap = false;
        KitChange = 160;
        MapChange = 400;
        i = -1;

        Config.loadFile();

        //Runnable
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {
                KitChange--;
                MapChange--;
                if (KitChange == 0) {
                    KitChange = 200;
                    enterHaken = false;
                    changeKit = false;
                    pickRandomKit();
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        setRandomKit(all);
                        all.sendTitle("§eKitChange", "§6" + KitName);
                        all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
                    }
                }
                if (MapChange == 0) {
                    MapChange = 400;
                    pickRandomMap();
                    changeMap = false;

                }

                for (Player all : Bukkit.getOnlinePlayers()) {
                    Board.setBoard(all);
                }
            }
        }, 20, 20);
        SpawnSystem.Maps = (ArrayList<String>) SpawnSystem.cfg.getStringList("Maps");
        for (int i = 0; i < SpawnSystem.cfg.getStringList("Maps").size(); i++) {
            System.out.println(SpawnSystem.Maps.get(i));
            SpawnSystem.location.put(SpawnSystem.Maps.get(i), SpawnSystem.loadSpawn(SpawnSystem.Maps.get(i)));
        }
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < SpawnSystem.cfg.getStringList("Maps").size(); i++) {
                    System.out.println(SpawnSystem.Maps.get(i));
                    SpawnSystem.location.put(SpawnSystem.Maps.get(i), SpawnSystem.loadSpawn(SpawnSystem.Maps.get(i)));
                }
            }
        }, 3L);

        Bukkit.getConsoleSender().sendMessage("§6Fukano KBFFA wurde erfolgreich geladen!");

        //Listener
        this.pm.registerEvents(new OnJoin(), this);
        this.pm.registerEvents(new AllListener(), this);
        this.pm.registerEvents(new DamageListener(), this);
        this.pm.registerEvents(new PlayerDeathandRespawn(), this);


        //Commands
        getCommand("setspawn").setExecutor(new SpawnSystem());
        getCommand("stats").setExecutor(new Stats_CMD());
        getCommand("changekit").setExecutor(new Kit_CMD());
        getCommand("changemap").setExecutor(new Map_CMD());

        // MySQL
        MySQL.setStandardMySQL();
        MySQL.readMySQL();
        MySQL.connect();
        MySQL.createTable();

        try{
            pickRandomMap();
            pickRandomKit();
        }catch(Exception e1){
            Bukkit.getConsoleSender().sendMessage("§4§lDu musst mindestens eine Map eingerichtet haben, damit das Plugin vernünftig starten kann...");
        }
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.getLocation().getY() < KillY) {
                        all.getPlayer().damage(20);
                        onRespawn(all, 2);
                    }
                }

            }
        }, 5, 5);
    }
    @EventHandler
    public void onRespawn(Player player,int Time) {
        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                ((CraftPlayer)player).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                KBFFA.setRandomKit(player);
            }
        },Time);
    }

    @Override
    public void onDisable() {

    }

    public static KBFFA getInstance() {
        return instance;
    }

    public static KBFFA getPlugin() {
        return plugin;
    }

    @SuppressWarnings("deprecation")
    public void pickRandomMap() {

        Bukkit.getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {

            @Override
            public void run() {
                ArrayList<String> internList = new ArrayList<>();
                internList = (ArrayList<String>) SpawnSystem.cfg.getStringList("Maps");
                int a = (int) ((Math.random()) * internList.size());
                if (internList.isEmpty()) {
                    Bukkit.getConsoleSender().sendMessage("§4§lDu musst mindestens eine Map eingerichtet haben, damit das Plugin vernünftig starten kann...");
                    return;
                }
                MapName = internList.get(a);
                SpawnY = SpawnSystem.cfg.getInt(internList.get(a) + ".SafeZone");
                KillY = SpawnSystem.cfg.getInt(internList.get(a) + ".KillZone");
            }
        }, 1L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

            @Override
            public void run() {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
                    all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
                    all.teleport(SpawnSystem.loadSpawn(MapName));
                    all.sendTitle("§6Mapwechsel", "§e" + MapName);
                }
            }
        }, 2L);
    }

    public void pickRandomKit() {
        int a = (int) ((Math.random()) * 4 + 1);

        if (a == 1) {
            KitName = "Standart";

        }
        if (a == 2) {
            KitName = "Schneemann";

        }
        if (a == 3) {
            KitName = "Knockback-3";

        }
        if (a == 4) {
            KitName = "Ultimate";

        }
    }

    public static void setRandomKit(Player p) {
        p.getInventory().clear();
        if (KitName.equals("Standart")) {
            p.getInventory().setItem(0, buildItem(Material.STICK, 1, 0, "§eKnockback-Stick", Enchantment.KNOCKBACK, 1));
        }
        if (KitName.equals("Schneemann")) {
            p.getInventory().setItem(0, buildItem(Material.STICK, 1, 0, "§eKnockback-Stick", Enchantment.KNOCKBACK, 1));
            p.getInventory().setItem(1, buildItemWAE(Material.SNOW_BALL, 16, 0, "§eSchneeball"));
        }
        if (KitName.equals("Knockback-3")) {
            p.getInventory().setItem(0, buildItem(Material.STICK, 1, 0, "§eKnockback-Stick", Enchantment.KNOCKBACK, 3));
        }
        if (KitName.equals("Ultimate")) {
            p.getInventory().setItem(0, buildItem(Material.STICK, 1, 0, "§eKnockback-Stick", Enchantment.KNOCKBACK, 5));
        }
    }

    public static ItemStack buildItem(Material mat, int anzahl, int shortid, String Displayname, Enchantment enchant, int staerke) {

        ItemStack i = new ItemStack(mat, anzahl, (short) shortid);
        ItemMeta im = i.getItemMeta();
        im.spigot().setUnbreakable(true);
        im.setDisplayName(Displayname);
        im.addEnchant(enchant, staerke, true);
        i.setItemMeta(im);

        return i;
    }

    public static ItemStack buildItemWAE(Material mat, int anzahl, int shortid, String Displayname) {
        ItemStack i = new ItemStack(mat, anzahl, (short) shortid);
        ItemMeta im = i.getItemMeta();
        im.spigot().setUnbreakable(true);
        im.setDisplayName(Displayname);
        i.setItemMeta(im);

        return i;
    }
}


