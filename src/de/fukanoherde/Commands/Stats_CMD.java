/*
 * Erstellt am 22.11.2020 um 00:09
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.Commands
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.Commands;

import de.fukanoherde.StatsSystem.Stats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Stats_CMD implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender cms, Command cmd, String label, String[] args) {
        if (cms instanceof ConsoleCommandSender){
            cms.sendMessage("§4Leider nicht per Console möglich");
            return true;
        }

        Player p = (Player) cms;
        cms.sendMessage("§2====================§8[§3Stats§8]§2====================");
        cms.sendMessage(" ");
        cms.sendMessage("§6Kills: §2" + Stats.getKills(p));
        cms.sendMessage(" ");
        cms.sendMessage("§6Deaths: §2" + Stats.getDeaths(p));
        cms.sendMessage(" ");
        return false;
    }
}
