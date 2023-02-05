package kyriakum.com.main.commands;

import kyriakum.com.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LinkCommand implements CommandExecutor {

    private Main main;

    public LinkCommand(Main main){this.main = main;}
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){

        }
        return false;
    }
}
