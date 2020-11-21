/*
 * Erstellt am 21.11.2020 um 22:24
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.Listener
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.Listener;

import de.fukanoherde.FileSystem.Config;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

public class AllListener implements Listener {


    @EventHandler
    public void onGM(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.setGameMode(GameMode.SURVIVAL);
    }

    @EventHandler
    public void onAchievment(PlayerAchievementAwardedEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onBreake(BlockBreakEvent e) {
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if (e.getPlayer().getGameMode() !=GameMode.CREATIVE){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHuger(PlayerItemConsumeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE ){
         e.setCancelled(false);
        }else {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§cDu darfst keine Items weg werfen");
            e.getPlayer().sendMessage(Config.getValue("Prefix") + "§2Sonst wäre es unfair");
        }
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        e.setCancelled(true);
        e.setFoodLevel(20);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e){
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPush(PlayerMoveEvent event) {

        if (event.getPlayer().getLocation().getBlock().getType() == Material.GOLD_PLATE)
        {
            Player p = event.getPlayer();
            org.bukkit.util.Vector v = p.getLocation().getDirection().multiply(2.0D).setY(1.3D);
            p.setVelocity(v);
            p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 3);
            p.playSound(p.getLocation(), Sound.FIZZ, 3.0F, 2.0F);

        }
    }

}
