/*
 * Erstellt am 22.11.2020 um 00:09
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.Commands
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.Commands;

import de.fukanoherde.Spigot.CoinAPI.CoinAPI;
import de.statsapi.mysql.StatsAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stats_CMD implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender cms, Command cmd, String label, String[] args) {
        Player p = (Player) cms;
        if (cms instanceof Player){
            double kd = ((double)StatsAPI.getKills(p.getUniqueId().toString()) / ((double)StatsAPI.getDeaths(p.getUniqueId().toString())));
            kd = ((double)((int)(kd * 100))) / 100;

            p.sendMessage("§2§m-----------------§8[§3Stats§8]§2§m-----------------");
            p.sendMessage("");
            p.sendMessage("§2Deine Kills§8: §6" + StatsAPI.getKills(p.getUniqueId().toString()));
            p.sendMessage("§2Deine Tode§8: §6" + StatsAPI.getDeaths(p.getUniqueId().toString()));
            p.sendMessage("§2Deine KD§8: §6" + kd);
            p.sendMessage("");

        }else {
            cms.sendMessage("§cNur ein Spieler kann das ausführen!");
        }

        return true;
    }
}
