/*
 * Erstellt am 21.11.2020 um 22:24
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.Listener
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.Listener;

import de.fukanoherde.FileSystem.Config;
import de.fukanoherde.KBFFA.KBFFA;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.util.Vector;

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
    public void onBNavi(PlayerInteractEvent e){
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                Block b = e.getClickedBlock();
                if (b.getType() == Material.CHEST || b.getType() == Material.BREWING_STAND || b.getType() == Material.ENCHANTMENT_TABLE
                        || b.getType() == Material.ENDER_CHEST || b.getType() == Material.ANVIL || b.getType() == Material.DARK_OAK_DOOR || b.getType() == Material.WOODEN_DOOR
                        || b.getType() == Material.BIRCH_DOOR || b.getType() == Material.ACACIA_DOOR || b.getType() == Material.IRON_DOOR || b.getType() == Material.JUNGLE_DOOR
                        || b.getType() == Material.SPRUCE_DOOR || b.getType() == Material.TRAP_DOOR || b.getType() == Material.WOOD_DOOR || b.getType() == Material.FENCE_GATE
                        || b.getType() == Material.WORKBENCH || b.getType() == Material.DROPPER || b.getType() == Material.DISPENSER || b.getType() == Material.HOPPER
                        || b.getType() == Material.WOOD_BUTTON || b.getType() == Material.STONE_BUTTON || b.getType() == Material.ITEM_FRAME || b.getType() == Material.DAYLIGHT_DETECTOR
                        || b.getType() == Material.BED_BLOCK || b.getType() == Material.FURNACE || b.getType() == Material.LEVER || b.getType() == Material.WOOD_PLATE){
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        try{
            if(e.getPlayer().getLocation().getY() > KBFFA.SpawnY){
                if(e.getItem().getType() == Material.SNOW_BALL){
                    e.getPlayer().sendMessage(Config.getValue("Prefix").toString().replace("&", "§")  + "§cDu darfst keine Schneebälle werfen!");
                    e.setCancelled(true);
                }
            }
        }catch(Exception e1){
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
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerFish(PlayerFishEvent e)
    {
        if(!KBFFA.enterHaken){
            return;
        }
        e.getPlayer().getItemInHand().setDurability((short)0);
        Player p = e.getPlayer();
        Fish h = e.getHook();
        if (((e.getState().equals(PlayerFishEvent.State.IN_GROUND)) || (e.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY)) || (e.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT))) && (Bukkit.getWorld(e.getPlayer().getWorld().getName()).getBlockAt(h.getLocation().getBlockX(), h.getLocation().getBlockY() - 1, h.getLocation().getBlockZ()).getType() != Material.AIR) && (Bukkit.getWorld(e.getPlayer().getWorld().getName()).getBlockAt(h.getLocation().getBlockX(), h.getLocation().getBlockY() - 1, h.getLocation().getBlockZ()).getType() != Material.STATIONARY_WATER))
        {
            Location lc = p.getLocation();
            Location to = e.getHook().getLocation();
            lc.setY(lc.getY() + 0.8D);
            p.teleport(lc);
            double g = -0.08D;
            double t;
            double d = t = to.distance(lc);
            double v_x = (1.0D + 0.07D * t) * (to.getX() - lc.getX()) / t;
            double v_y = (1.0D + 0.08D * t) * (to.getY() - lc.getY()) / t - -0.1D * t;
            double v_z = (1.0D + 0.07D * t) * (to.getZ() - lc.getZ()) / t;
            Vector v = p.getVelocity();
            v.setX(v_x);
            v.setY(v_y);
            v.setZ(v_z);
            p.setVelocity(v);
            p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
        }
    }
    @SuppressWarnings("deprecation")
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
