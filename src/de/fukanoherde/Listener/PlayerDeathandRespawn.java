/*
 * Erstellt am 22.11.2020 um 17:28
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.Listener
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.Listener;

import de.fukanoherde.FileSystem.Config;
import de.fukanoherde.KBFFA.KBFFA;
import de.fukanoherde.SpawnSystem;
import de.statsapi.mysql.StatsAPI;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;



public class PlayerDeathandRespawn implements Listener {


    @SuppressWarnings("deprecation")
    @EventHandler
    public void onTod(PlayerDeathEvent e){
        Player p = e.getEntity();
        if (p == p.getKiller()) {
            e.setDeathMessage(null);
            e.setDeathMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§6" + p.getName() + "§e ist gestorben...");
            StatsAPI.addDeaths(p.getUniqueId().toString(), +1);
            p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 10);
            p.setHealthScale(6);
            e.getDrops().clear();
            onRespawn(p, 2);
            p.teleport(SpawnSystem.location.get(KBFFA.MapName));
            KBFFA.setRandomKit(p);
            KBFFA.setRandomKit(p);
            KBFFA.setRandomKit(p);
            p.updateInventory();
            Bukkit.getScheduler().scheduleAsyncDelayedTask(KBFFA.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    KBFFA.setRandomKit(p);
                    onRespawn(p, 1);
                }
            }, 2L);
            return;
        }
        p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 10);
        e.setDeathMessage(null);
        StatsAPI.addDeaths(p.getUniqueId().toString(), 1);
        if (p.getKiller() == null){
            p.sendTitle("§4§l✖", "§cGESTORBEN");
            e.setDeathMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§6" + p.getName() + "§e ist gestorben...");
            e.getDrops().clear();
            KBFFA.setRandomKit(p);
            onRespawn(p, 2);
            KBFFA.setRandomKit(p);
            KBFFA.setRandomKit(p);
            p.updateInventory();
        }else {
            StatsAPI.addKills(p.getKiller().getUniqueId().toString(),+ 1);
            p.sendTitle("§4§l✖", "§9" + p.getKiller().getName());
            p.getKiller().sendTitle("§a§l✔", "§7" + p.getName());
            onRespawn(p, 2);
            p.getKiller().playSound(p.getKiller().getLocation(), Sound.LEVEL_UP, 10, 10);
            KBFFA.setRandomKit(p);
            KBFFA.setRandomKit(p);
            KBFFA.setRandomKit(p);
            KBFFA.setRandomKit(p);
            p.updateInventory();
            Bukkit.getScheduler().scheduleAsyncDelayedTask(KBFFA.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    KBFFA.setRandomKit(p);
                    onRespawn(p, 1);
                }
            }, 5L);

        }
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onRespawn(Player player,int Time) {
        Bukkit.getScheduler().runTaskLater(KBFFA.getPlugin(), new Runnable() {
            @Override
            public void run() {
                ((CraftPlayer)player).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                player.teleport(SpawnSystem.location.get(KBFFA.MapName));
                KBFFA.setRandomKit(player);
            }
        },Time);
    }

}
