package codes.smit.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import codes.smit.services.AnnouncementService;
import java.awt.Color;

public class DMListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Ignore messages from bots
        if (event.getAuthor().isBot()) return;

        // Only handle DMs
        if (event.getChannel().getType() == ChannelType.PRIVATE) {
            String message = event.getMessage().getContentRaw();

            // Split into lines (expects at least 3 lines: channelId, title, description)
            String[] lines = message.split("\n");

            if (lines.length < 3) {
                event.getChannel()
                        .sendMessage("⚠️ Please provide 3 lines:\n1) Channel ID\n2) Title\n3) Description")
                        .queue();
                return;
            }

            // Create preview embed
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle(lines[1]);
            embed.setDescription(lines[2]);
            embed.setColor(Color.MAGENTA);
            embed.setFooter("Sent by " + event.getAuthor().getName());

            // Send confirmation to user with preview
            event.getChannel()
                    .sendMessage("Preview announcement embed for channel:")
                    .setEmbeds(embed.build())
                    .queue();

            // Call the AnnouncementService
            AnnouncementService.postEmbedAnnouncement(event.getAuthor(), lines[0], embed);
        }
    }
}