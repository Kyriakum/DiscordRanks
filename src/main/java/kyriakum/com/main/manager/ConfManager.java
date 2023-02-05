package kyriakum.com.main.manager;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfManager {


    private ConfManager(){ throw new IllegalStateException("Utility class");}

    public static YamlConfiguration getConfiguration(File f){
        return YamlConfiguration.loadConfiguration(f);
    }
}
