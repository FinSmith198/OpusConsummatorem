package main.listeners.slash;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.Objects;

public class SetActivity implements SlashCommand {
    @Override
    public SlashCommandData getSlashCommandData() {
        return Commands.slash("set-activity", "Sets Opus Consummatorem's current discord activity")
                .addOptions(
                        new OptionData(OptionType.STRING, "type", "The Type of activity to be shown", true)
                                .addChoice("Playing", "playing")
                                .addChoice("Watching", "watching")
                                .addChoice("Listening", "listening to")
                                .addChoice("Custom", "custom"),
                        new OptionData(OptionType.STRING, "text", "The text that the activity shows, e.g. Listening to {text}", true)
                )
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MESSAGE_MANAGE, Permission.MANAGE_ROLES));
    }

    @Override
    public void doSlashCommandInteraction(SlashCommandInteractionEvent event) {
        JDA jda = event.getJDA();
        event.deferReply(true).queue();

        try {
            String choice = Objects.requireNonNull(event.getOption("type")).getAsString();
            String text = Objects.requireNonNull(event.getOption("text")).getAsString();
            switch (choice){
                case "listening to":
                    jda.getPresence().setActivity(Activity.listening(text));
                    event.getHook().sendMessage("Set activity to: Listening to "+text).queue();
                    break;
                case "playing":
                    jda.getPresence().setActivity(Activity.playing(text));
                    event.getHook().sendMessage("Set activity to: Playing "+text).queue();
                    break;
                case "watching":
                    jda.getPresence().setActivity(Activity.watching(text));
                    event.getHook().sendMessage("Set activity to: Watching "+text).queue();
                    break;
                case "custom":
                    jda.getPresence().setActivity(Activity.customStatus(text));
                    event.getHook().sendMessage("Set activity to: "+text).queue();
                    break;
                default:
                    event.getHook().sendMessage("Error Occurred, Activity was not changed.").queue();
            }
        } catch (NullPointerException e){
            event.getHook().sendMessage("Command Not Processed. Did you format it correctly? (/set-activity {type} {text})").queue();
        }
    }
}
