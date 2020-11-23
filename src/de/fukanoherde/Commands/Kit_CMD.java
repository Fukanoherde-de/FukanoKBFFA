/*
 * Erstellt am 22.11.2020 um 15:08
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

public class Kit_CMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String arg2, String[] args) {
        Player p = (Player)s;
        if (!p.hasPermission("knockbackffa.changekit")) {
            return true;
        }
        if (KBFFA.changeKit == false) {
            if (KBFFA.KitChange < 10) {
                p.sendMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§cDas Kit wird in den nächsten 10 Sekunden geändert...");
                return true;
            }
            KBFFA.changeKit = true;
            p.sendMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§eDas Kit wird in 10 Sekunden geändert");
            KBFFA.KitChange = 10;
        } else {
            p.sendMessage(Config.getValue("Prefix").toString().replace("&", "§") + "§cDas Kit wird bereits geändert...");
        }
        return true;
    }
}


