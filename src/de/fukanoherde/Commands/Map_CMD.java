/*
 * Erstellt am 22.11.2020 um 15:16
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.Commands
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.Commands;

import de.fukanoherde.FileSystem.Config;
import de.fukanoherde.KBFFA.KBFFA;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Map_CMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String arg2, String[] args) {
        Player p = (Player) s;
        if(!p.hasPermission("knockbackffa.changemap")){
            return true;
        }
        if(KBFFA.changeMap == false){
            if(KBFFA.MapChange < 10){
                p.sendMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§cDie Map wird in den nächsten 10 Sekunden geändert...");
                return true;
            }
            KBFFA.changeMap = true;
            p.sendMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§eDie Karte wird in 10 Sekunden geändert");
            KBFFA.MapChange = 10;
        }else{
            p.sendMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§cDie Karte wird bereits geändert...");
        }
        return true;

    }
}
