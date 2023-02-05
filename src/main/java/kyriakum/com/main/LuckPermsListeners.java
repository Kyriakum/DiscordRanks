package kyriakum.com.main;

import kyriakum.com.main.manager.LinkManager;
import kyriakum.com.main.manager.VerificationManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.log.LogPublishEvent;
import net.luckperms.api.event.node.NodeAddEvent;
import net.luckperms.api.event.node.NodeRemoveEvent;
import net.luckperms.api.event.user.UserDataRecalculateEvent;
import net.luckperms.api.event.user.track.UserPromoteEvent;
import net.luckperms.api.event.user.track.UserTrackEvent;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Optional;

public class LuckPermsListeners {

    private Main main;

    public LuckPermsListeners(Main main){
        this.main = main;
        EventBus eventBus = main.getLuckPerms().getEventBus();
        eventBus.subscribe(this.main, NodeAddEvent.class, this::groupUserChange);;
        eventBus.subscribe(this.main, NodeRemoveEvent.class, this::groupUserChange2);
    }

    public void groupUserChange(NodeAddEvent e){
        System.out.println("Called called Add");
        if(!e.isUser()) { return; }
        User user = (User) e.getTarget();
        Node node = e.getNode();

        main.getServer().getScheduler().runTask(main, () -> {
            Player player = main.getServer().getPlayer(user.getUniqueId());
            if(player == null) return;

            if(node instanceof InheritanceNode){
                String groupName = ((InheritanceNode) node).getGroupName();


               main.getLuckPerms().getGroupManager().getLoadedGroups().forEach(g -> {
                   String a = g.getName();
                   main.getRankManager().removeRank(a, main.getLinkManager().getUser(player));
               });
                main.getRankManager().setRank(groupName, main.getLinkManager().getUser(player));

            }
        });
    }

    public void groupUserChange2(NodeRemoveEvent e){
        System.out.println("Called called");
        if(!e.isUser()) { return; }
        User user = (User) e.getTarget();
        Node node = e.getNode();

        main.getServer().getScheduler().runTask(main, () -> {
            Player player = main.getServer().getPlayer(user.getUniqueId());
            if(player == null) return;

            if(node instanceof InheritanceNode){
                String groupName = ((InheritanceNode) node).getGroupName();
                main.getRankManager().removeRank(groupName, main.getLinkManager().getUser(player));
            }
        });
    }
}
