package kyriakum.com.main.manager;

import kyriakum.com.main.entities.VerificationChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VerificationManager {

    private static final ArrayList<VerificationChannel> verifications = new ArrayList<>();

    public static List<VerificationChannel> getVerifications() { return verifications; }
    public static void addVerification(VerificationChannel chan) { verifications.add(chan);}
    public static boolean removeVerification(VerificationChannel chan) { return verifications.remove(chan);}

    private VerificationManager() { throw new IllegalStateException(""); }

    public static VerificationChannel getSpecificVer(UUID uuid){
        for(VerificationChannel chan : verifications){
            if(chan.getUuid().equals(uuid))
                return chan;

        }
        return null;
    }

    public static boolean verAlreadyGenerated(UUID uuid){
        for(VerificationChannel chan : verifications){
            if(chan.getUuid().equals(uuid)) return true;
        }
        return false;
    }

    public static boolean verAlreadyGenerated(User user){
        for(VerificationChannel chan : verifications){
            if(chan.getUser().equals(user)) return true;
        }
        return false;

    }
}
