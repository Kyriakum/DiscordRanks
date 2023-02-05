package kyriakum.com.main.manager;

import kyriakum.com.main.Main;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.entity.Player;

public class RankManager {
    private Main main;
    public RankManager(Main main){
        this.main = main;
    }

    public void setRank(String rank, User user){

        Role role = main.getGuild().getRolesByName(rank,true).get(0);
        if(role == null) {
            System.out.println("Error occured! Role doesn't exist!"); return;
        }
        main.getGuild().addRoleToMember(user, role).queue();
    }

    public void removeRank(String rank, User user){

        Role role = main.getGuild().getRolesByName(rank,true).get(0);
        if(role == null) {
            System.out.println("Error occured! Role doesn't exist!"); return;
        }
        main.getGuild().removeRoleFromMember(user, role).queue();
    }



    public String getRank(Player player){
    return main.getLuckPerms().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup();
    }
}
