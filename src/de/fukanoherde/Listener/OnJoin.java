/*
 * Erstellt am 21.11.2020 um 22:05
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.Listener
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.Listener;

import de.fukanoherde.KBFFA.KBFFA;
import de.fukanoherde.SpawnSystem;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.player.*;

public class OnJoin implements Listener {


    public void JoinListerner(KBFFA KBFFA){
        this.pl = KBFFA;
    }
    private de.fukanoherde.KBFFA.KBFFA pl;
    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnJoin(PlayerJoinEvent e) {
        e.getPlayer().setFoodLevel(20);
        e.getPlayer().setHealthScale(6);
        e.getPlayer().getInventory().clear();
        e.getPlayer().getInventory().setArmorContents(null);
        Player p = e.getPlayer();
        KBFFA.getInstance().board.setScoreboard(p);
        KBFFA.setRandomKit(e.getPlayer());
        p.teleport(SpawnSystem.location.get(KBFFA.MapName));
        String name = p.getDisplayName();
        e.setJoinMessage("§8[§2+§8] §6" + name);
        p.setGameMode(GameMode.ADVENTURE);

        Bukkit.getScheduler().runTaskTimer(KBFFA.getInstance(), () -> {
            KBFFA.getInstance().board.updateSB(p);
        }, 0, 20);

    }
    @EventHandler
    public void onChatclear(PlayerJoinEvent e){
        Player p = e.getPlayer();
        for (int i = 0; i <=1000; i++){
            p.sendMessage(" ");
        }
    }
}
