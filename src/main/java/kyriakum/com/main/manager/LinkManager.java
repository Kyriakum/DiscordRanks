package kyriakum.com.main.manager;

import kyriakum.com.main.Main;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class LinkManager {


    private Main main;
    private File f;

    public LinkManager(Main main){
        setup(main);
    }


    private void setup(Main main){
        this.main = main;
        File f = new File(main.getDataFolder() + "/data.yml");
        this.f = f;
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean playerExists(Player player){
        YamlConfiguration conf = ConfManager.getConfiguration(f);
        return (conf.getString(player.getUniqueId().toString()) != null);
    }

    public boolean userExists(User user){
        YamlConfiguration conf = ConfManager.getConfiguration(f);
        for(String s : conf.getConfigurationSection("").getKeys(false)) {
            if(conf.get(s).equals(user.getId())){
                return true;
            }
        }
        return false;
    }

    public void setupLink(UUID uuid, User user){
        YamlConfiguration conf = ConfManager.getConfiguration(f);
        try {
            conf.set(uuid.toString(),user.getId());
            conf.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
