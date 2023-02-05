package kyriakum.com.main.entities;


import net.dv8tion.jda.api.entities.User;

import java.util.Random;
import java.util.UUID;


public class VerificationChannel {

    private final UUID uuid;
    private final User user;
    private final String code;

    public VerificationChannel(UUID uuid, User user){
        this.uuid = uuid;
        this.user = user;
        char c = (char)(new Random().nextInt(26) + 'A');
        code = new Random().nextInt(800000) + 200000 + String.valueOf(c);
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getUser() {
        return user;
    }

    public String getCode() {
        return code;
    }
}
