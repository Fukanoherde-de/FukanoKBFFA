/*
 * Erstellt am 21.11.2020 um 21:44
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.FileSystem
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.FileSystem;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileWirter {


    private File f;
    private YamlConfiguration c;

    public FileWirter(String FilePath, String FileName) {
        this.f = new File(FilePath, FileName);
        this.c = YamlConfiguration.loadConfiguration(this.f);
    }

    public boolean exist() {
        return this.f.exists();
    }

    public FileWirter setValue(String ValuePath, Object Value) {
        this.c.set(ValuePath, Value);
        return this;
    }

    public FileWirter setDefaultValue(String ValuePath, Object Value) {
        if(!valueExist(ValuePath)){
            this.c.set(ValuePath, Value);
            save();
        }
        return this;
    }

    public Object getObject(String ValuePath) {
        return this.c.get(ValuePath);
    }

    public boolean valueExist(String value) {
        return this.getObject(value) != null;
    }

    public FileWirter save() {
        try {
            this.c.save(this.f);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return this;
    }

    public boolean getBoolean(String ValuePath) {
        return this.c.getBoolean(ValuePath);
    }

    public String getString(String ValuePath) {
        return this.c.getString(ValuePath);
    }

    public Integer getInt(String ValuePath) {
        return this.c.getInt(ValuePath);
    }

    public List<String> getStringList(String ValuePath) {
        return this.c.getStringList(ValuePath);
    }

    public List<Integer> getIntList(String ValuePath) {
        return this.c.getIntegerList(ValuePath);
    }

    public Long getLong(String ValuePath) {
        return this.c.getLong(ValuePath);
    }

    public Float getFloat(String ValuePath) {
        return (float)this.c.getLong(ValuePath);
    }

    public Double getDouble(String ValuePath) {
        return this.c.getDouble(ValuePath);
    }


}
