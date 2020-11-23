/*
 * Erstellt am 21.11.2020 um 21:48
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.FileSystem
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.FileSystem;

import de.fukanoherde.KBFFA.KBFFA;

public class Config {

    private static FileWirter fileWriter = new FileWirter(KBFFA.getPlugin().getDataFolder().getPath(), "config.yml");
    public static void  loadFile(){

        setValue("Prefix", "&8[&3FukanoKBFFA&8] §6» ");
        setValue("NoPerm", "&4Leider hast du dazu keine Rechte!");
        setValue("SB.Name","&3FukanoKBFFA &6V2.2" );
        setValue("SB.Map","Map" );
        setValue("SB.MapChange", "Mapchange");
        setValue("SB.Kit","Kit" );
        setValue("SB.KitChange", "Kitchange");
        setValue("SB.Kill","Kill" );
        setValue("SB.Death","Tode" );
        setValue("SB.KD","KD" );
        setValue("SB.Team", "Keine Teams");
        setValue("MySQL.Host", "127.0.0.1");
        setValue("MySQL.Port", "3306");
        setValue("MySQL.Database", "Fukano");
        setValue("MySQL.User", "Fukano");
        setValue("MySQL.Password", "S7100EhylVXgLjDM");

    }
    private static void setValue(String valuePath, String value){
        if (!fileWriter.valueExist(valuePath)){
            fileWriter.setValue(valuePath, value);
            fileWriter.save();
        }
    }
    public static Object getValue(String valuePath){
        return fileWriter.getObject(valuePath);
    }


}
