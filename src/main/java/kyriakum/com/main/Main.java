package kyriakum.com.main;

import kyriakum.com.main.manager.LinkManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private JDA jda;
    private LinkManager linkManager;
    private Guild guild;
    @Override
    public void onEnable() {

            saveDefaultConfig();
                linkManager = new LinkManager(this);
            if(getConfig().getString("token") == null){
                System.out.println("No token specified!");
                setEnabled(false);
                return;
            }
        JDABuilder builder = JDABuilder.createDefault(getConfig().getString("token"));
        builder.setActivity(Activity.watching("your ranks"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.addEventListeners(new EventListener(this));
        jda = builder.build();
      //  guild = jda.getGuildById(getConfig().getLong("guild"));
    }

    @Override
    public void onDisable() {}
    public LinkManager getLinkManager() {return linkManager; }
    public JDA getJda(){ return jda; }
    public Guild getGuild(){ return guild; }
}
