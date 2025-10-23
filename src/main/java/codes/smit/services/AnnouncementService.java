package codes.smit.services;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class AnnouncementService {
    public static void postAnnouncement(User user, String[] messageContent) {
        String channelId = messageContent[0];
        String title = messageContent[1];
        String description = messageContent[2];

        TextChannel channel = user.getJDA().getTextChannelById(channelId);
        if (channel == null) {
            DMService.sendDM(user, "❌ Channel not found: " + channelId);
            return;
        }

        channel.sendMessage(title + "\n" + description).queue();
        DMService.sendDM(user, "✅ Announcement sent!");
        return;
    }

    public static void postEmbedAnnouncement(User user, String channelId, EmbedBuilder embed) {
        TextChannel channel = user.getJDA().getTextChannelById(channelId);
        if (channel == null) {
            DMService.sendDM(user, "❌ Channel not found: " + channelId);
            return;
        }

        channel.sendMessageEmbeds(embed.build()).queue();
        DMService.sendDM(user, "✅ Announcement sent!");
    }

}
