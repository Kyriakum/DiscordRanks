package kyriakum.com.main.commands;

import kyriakum.com.main.Main;
import kyriakum.com.main.entities.VerificationChannel;
import kyriakum.com.main.manager.RankManager;
import kyriakum.com.main.manager.VerificationManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LinkCommand implements CommandExecutor {

    private final Main main;

    public LinkCommand(Main main){this.main = main;}
    @Override
    public boolean  onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){ return true; }
            Player p = (Player) sender;
            if(command.getName().equalsIgnoreCase("verify")){
                if(main.getLinkManager().playerExists(p)) {
                    p.sendMessage(ChatColor.RED + "You are already verified!");
                    return false;
                }

                if(args.length < 1){
                    p.sendMessage(ChatColor.RED + "You didn't specify your code! /verify (code)");
                    return false;
                    }

                VerificationChannel ver = VerificationManager.getSpecificVer(p.getUniqueId());
                if(ver == null) {
                    p.sendMessage(ChatColor.RED + "You haven't requested a verification code!");
                    return false;
                }
                String code = args[0];
                if(code.equals(ver.getCode())){
                    main.getLinkManager().setupLink(p.getUniqueId(), ver.getUser());
                     p.sendMessage(ChatColor.GREEN + "You successfully linked your minecraft account to the discord server!");
                    RankManager r = main.getRankManager();
                    String rank = r.getRank(p);
                    r.setRank(rank, ver.getUser());
                    VerificationManager.removeVerification(ver);

                } else {
                    p.sendMessage(ChatColor.RED + "The code is incorrect!");
                }
            }

        return false;
    }
}
