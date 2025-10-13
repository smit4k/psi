package codes.smit.services;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import codes.smit.services.DMService;

public class AnnouncementService {
    public static boolean postAnnouncement(User user, String[] messageContent) {
        String channelId = messageContent[0];
        String title = messageContent[1];
        String description = messageContent[2];

        TextChannel channel = user.getJDA().getTextChannelById(channelId);
        if (channel == null) {
            DMService.sendDM(user, "❌ Channel not found: " + channelId);
            return false;
        }

        channel.sendMessage(title + "\n" + description).queue();
        DMService.sendDM(user, "✅ Announcement sent!");
        return true;
    }

}
