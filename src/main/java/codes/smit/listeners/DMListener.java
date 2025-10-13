package codes.smit.listeners;

import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DMListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Ignore messages from bots
        if (event.getAuthor().isBot()) {
            return;
        }

        // Check if the message is from a private channel (DM)
        if (event.getChannel().getType() == ChannelType.PRIVATE) {
            String message = event.getMessage().getContentRaw();
            String authorName = event.getAuthor().getName();

            System.out.println("DM received from " + authorName + ": " + message);

            // Respond to the DM
            event.getChannel().sendMessage("Message received: " + message).queue();

        }
    }
}