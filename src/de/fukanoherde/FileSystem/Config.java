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
        setValue("MySQL.Host", "127.0.0.1");
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
