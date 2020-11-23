/*
 * Erstellt am 22.11.2020 um 15:56
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.Listener
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.Listener;

import de.fukanoherde.FileSystem.Config;
import de.fukanoherde.KBFFA.KBFFA;
import de.fukanoherde.SpawnSystem;
import de.statsapi.mysql.StatsAPI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death  implements Listener {


    @SuppressWarnings("deprecation")
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (p == p.getKiller()) {
            e.setDeathMessage(null);
            e.setDeathMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§6" + p.getName() + "§e ist gestorben...0");
            StatsAPI.addDeaths(p.getUniqueId().toString(), + 1);
            p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 10);
            p.setHealthScale(6);
            e.getDrops().clear();
            p.teleport(SpawnSystem.location.get(KBFFA.MapName));
            KBFFA.setRandomKit(p);
            return;
        }
        p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 10);
        e.setDeathMessage(null);
        StatsAPI.addDeaths(p.getUniqueId().toString(), 1);
        if (p.getKiller() == null){
            p.sendTitle("§4§l✖", "§cGESTORBEN");
            e.setDeathMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§6" + p.getName() + "§e ist gestorben...1");
            p.teleport(SpawnSystem.location.get(KBFFA.MapName));
            KBFFA.setRandomKit(p);
        }else {
            StatsAPI.addKills(p.getKiller().getUniqueId().toString(),+ 1);
            p.sendTitle("§4§l✖", "§9" + p.getKiller().getName());
            p.getKiller().sendTitle("§a§l✔", "§7" + p.getName());
            p.getKiller().playSound(p.getKiller().getLocation(), Sound.LEVEL_UP, 10, 10);
        }
        p.setHealthScale(6);
        e.getDrops().clear();
        KBFFA.setRandomKit(p);
        p.teleport(SpawnSystem.location.get(KBFFA.MapName));
        e.getDrops().clear();
    }

}
