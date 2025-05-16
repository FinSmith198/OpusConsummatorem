package main;


import main.listeners.Listeners;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import static main.Config.readConfigFile;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        readConfigFile("default.conf");
        init(Config.DISCORD_BOT_TOKEN);
    }

    public static void init(String TOKEN) throws InterruptedException {

        JDABuilder builder = JDABuilder.createDefault(TOKEN)
                .setMemberCachePolicy(MemberCachePolicy.DEFAULT)
                .enableIntents(
                        GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_WEBHOOKS,
                        GatewayIntent.GUILD_MODERATION, GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.GUILD_MESSAGE_TYPING
                );

        builder.addEventListeners(Listeners.listeners).build().awaitReady();
    }
}
