package main.listeners;


import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


public class BotReadier extends ListenerAdapter {

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event){
        // for guild-specific commands too
        System.out.println(event.getGuild().getName()+" guild ready");
    }
}
