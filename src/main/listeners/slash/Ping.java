package main.listeners.slash;


import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Ping implements SlashCommand {
    @Override
    public SlashCommandData getSlashCommandData() {
        return Commands.slash("signum-mitto", "Send Opus Consummatorem a Signal");
    }

    @Override
    public void doSlashCommandInteraction(SlashCommandInteractionEvent event) {
        event.deferReply(true).queue();
        event.getHook().sendMessage("Respondeo").queue();
    }
}
