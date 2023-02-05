package kyriakum.com.main;

import kyriakum.com.main.entities.VerificationChannel;
import kyriakum.com.main.manager.VerificationManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EventListener extends ListenerAdapter {

    private Main main;

    public EventListener(Main main){
        this.main = main;
    }



    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        String[] args = e.getMessage().getContentRaw().split(" ");


        if(args[0].equalsIgnoreCase("!link")){


            if(main.getLinkManager().userExists(e.getAuthor())) {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + " you have already linked your minecraft account!").queue();
                return;
            }

            if(VerificationManager.verAlreadyGenerated(e.getAuthor())){
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + " you already requested a verification code!").queue();
                return;
            }

            if(args.length < 2) {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + " you didn't specify a minecraft username!").queue();
                return;
            }
            Player target = Bukkit.getPlayer(args[1]);
            if(target==null){
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + " this player is not online!").queue();
                return;
            }

            if(main.getLinkManager().playerExists(target)){
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + " this player is already linked!").queue();
                return;
            }


            if(VerificationManager.verAlreadyGenerated(target.getUniqueId())){
            VerificationManager.removeVerification(VerificationManager.getSpecificVer(target.getUniqueId()));
            }


                e.getChannel().sendMessage(e.getAuthor().getAsMention() + " your code has been sent to your DMs!").queue();
                VerificationChannel channel = new VerificationChannel(target.getUniqueId(), e.getAuthor());
                VerificationManager.addVerification(channel);
                e.getAuthor().openPrivateChannel().complete().sendMessage("Your verification code: " + channel.getCode() + "\ntype /verify (code) in minecraft to verify your account!").queue();

        }
    }


}
