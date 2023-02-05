package kyriakum.com.main.manager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;

public class ConfManager {



    public static YamlConfiguration getConfiguration(File f){
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(f);
        return conf;
    }
}
