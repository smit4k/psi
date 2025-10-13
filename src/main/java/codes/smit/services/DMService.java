package codes.smit.services;

import net.dv8tion.jda.api.entities.User;

public class DMService {

    public static boolean sendDM(User user, String messageContent) {
        if (user == null || messageContent == null || messageContent.isEmpty()) {
            System.out.println("Invalid user or message content.");
            return false;
        }

        user.openPrivateChannel()
                .flatMap(channel -> channel.sendMessage(messageContent))
                .queue(
                        success -> System.out.println("✅ Sent DM to " + user.getName()),
                        error -> System.out.println("❌ Failed to send DM to " + user.getName())
                );

        return true;
    }
}
