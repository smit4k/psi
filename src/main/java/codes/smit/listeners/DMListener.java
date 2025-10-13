package codes.smit.listeners;

import codes.smit.services.DMService;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import codes.smit.services.AnnouncementService;

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

            // Call the AnnouncementService
            AnnouncementService.postAnnouncement(event.getAuthor(), lines);
        }
    }
}
