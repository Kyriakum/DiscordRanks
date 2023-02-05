package kyriakum.com.main;

import kyriakum.com.main.commands.LinkCommand;
import kyriakum.com.main.manager.LinkManager;
import kyriakum.com.main.manager.RankManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private JDA jda;
    private LinkManager linkManager;
    private Guild guild;
    private LuckPerms luckPerms;
    private RankManager rankManager;
    private final Logger log = Logger.getLogger("Minecraft");
    @Override
    public void onEnable() {

        saveDefaultConfig();
        if(getConfig().getString("token") == null){
           log.info("No token specified!");
            setEnabled(false);
            return;
        }
        if(getConfig().get("guildID") == null){
            log.info("No guild ID specified!");
            setEnabled(false);
            return;
        }

        luckPerms = LuckPermsProvider.get();
        linkManager = new LinkManager(this);
        rankManager = new RankManager(this);
        new LuckPermsListeners(this);
        setupDiscord();
        }

    @Override
    public void onDisable() {//Empty
         }
    public LinkManager getLinkManager() {return linkManager; }
    public JDA getJda(){ return jda; }
    public Guild getGuild(){ return guild; }
    public LuckPerms getLuckPerms() { return luckPerms; }
    public RankManager getRankManager() { return rankManager;}
    private void setupDiscord(){
        JDABuilder builder = JDABuilder.createDefault(getConfig().getString("token")).setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS).setChunkingFilter(ChunkingFilter.ALL);
        builder.setActivity(Activity.watching("your ranks"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.addEventListeners(new EventListener(this));
        getCommand("verify").setExecutor(new LinkCommand(this));
        jda = builder.build();
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String s = getConfig().getString("guildID");
        guild = jda.getGuildById(s);
        if(guild == null) {
            System.out.println("The guild ID is incorrect!");
            setEnabled(false);
            return;
        }
    }
}
