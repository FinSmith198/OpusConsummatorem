package main.listeners.slash;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public interface SlashCommand {
    /**
     * @return  Slash command data defining the slash command schema. Use Commands.slash() to use schema.
     */
    SlashCommandData getSlashCommandData();

    /**
     * Runs when the specific slash command is called by a user (or bot)
     * @param event     the event that triggered this slash command event call
     */
    void doSlashCommandInteraction(SlashCommandInteractionEvent event);
}
