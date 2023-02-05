package kyriakum.com.main.entities;


import net.dv8tion.jda.api.entities.User;

import java.util.Random;
import java.util.UUID;


public class VerificationChannel {

    private UUID uuid;
    private User user;
    private int code;

    public VerificationChannel(UUID uuid, User user){
        this.uuid = uuid;
        this.user = user;
        code = new Random().nextInt(800000) + 200000;
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getUser() {
        return user;
    }

    public int getCode() {
        return code;
    }
}
