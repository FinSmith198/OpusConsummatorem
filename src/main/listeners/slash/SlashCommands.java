package main.listeners.slash;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;
import java.util.*;

public class SlashCommands extends ListenerAdapter {

    protected final Set<SlashCommand> commands = new HashSet<>();

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        commands.forEach(command -> {
            if (!command.getSlashCommandData().getName().equals(event.getName()))
                return;
            command.doSlashCommandInteraction(event);
        });
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {

        //==================ADD NEW COMMANDS HERE...==================

        commands.add(new Ping());
        commands.add(new SetActivity());



        //============================================================

        List<CommandData> commandData = new ArrayList<>();
        commands.forEach(command -> commandData.add(command.getSlashCommandData()));

        event.getJDA().updateCommands().addCommands(commandData).queue();
    }
}
