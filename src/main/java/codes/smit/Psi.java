package codes.smit;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import codes.smit.listeners.DMListener;

import io.github.cdimascio.dotenv.Dotenv;

public class Psi extends ListenerAdapter {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("DISCORD_TOKEN");

        if (token == null) {
            System.err.println("DISCORD_TOKEN not found in .env!");
            return;
        }

        JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new DMListener())
                .build();
    }
}