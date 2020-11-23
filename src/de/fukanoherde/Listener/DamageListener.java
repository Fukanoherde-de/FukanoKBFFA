/*
 * Erstellt am 22.11.2020 um 14:14
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.Listener
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.Listener;

import de.fukanoherde.FileSystem.Config;
import de.fukanoherde.KBFFA.KBFFA;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class DamageListener implements Listener {


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        e.setDamage(0);
        if (e.getEntity().getLocation().getY() > KBFFA.SpawnY - 2) {
            e.setCancelled(true);
            e.getDamager().sendMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§cDu darfst dich hier nicht schlagen...");
        } else {
            e.setCancelled(false);
        }
        if (e.getDamager().getLocation().getY() < KBFFA.SpawnY && e.getEntity().getLocation().getY() >= KBFFA.SpawnY) {
            e.setCancelled(true);
            e.getDamager().sendMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§cDu darfst dich hier nicht schlagen...");
        }
        if (e.getEntity().getLocation().getY() < KBFFA.SpawnY && e.getDamager().getLocation().getY() >= KBFFA.SpawnY) {
            e.setCancelled(true);
            e.getDamager().sendMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§cDu darfst dich hier nicht schlagen...");
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        try{
            if(e.getPlayer().getLocation().getY() > KBFFA.SpawnY){
                if(e.getItem().getType() == Material.SNOW_BALL){
                    e.getPlayer().sendMessage(Config.getValue("Prefix").toString().replace("&", "§")  + "§cDu darfst keine Schneebälle werfen!");
                    e.setCancelled(true);
                    return;
                }
            }
        }catch(Exception e1){
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamageVoid(EntityDamageEvent event) {
        if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
            event.setDamage(20);
        }
    }

}
