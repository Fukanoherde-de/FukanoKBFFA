/*
 * Erstellt am 22.11.2020 um 00:03
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.Listener
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.Listener;

import de.fukanoherde.StatsSystem.Stats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        Player k = p.getKiller();

        if (k != null && k != p){
            Stats.addKills(p , 1);
        }else {
            Stats.addDeaths(p, 1);
        }
    }


}
