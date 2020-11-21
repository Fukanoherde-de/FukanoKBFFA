/*
 * Erstellt am 21.11.2020 um 22:05
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.Listener
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.*;

public class OnJoin implements Listener {


    @EventHandler
    public void OnJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        String name = p.getDisplayName();

        e.setJoinMessage("§8[§2+§8] §6" + name);
    }

    @EventHandler
    public void onChatclear(PlayerJoinEvent e){
        Player p = e.getPlayer();
        for (int i = 0; i <=1000; i++){
            p.sendMessage(" ");
        }
    }
}
