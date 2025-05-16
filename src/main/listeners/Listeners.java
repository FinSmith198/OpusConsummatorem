package main.listeners;

import main.listeners.slash.SlashCommands;

public abstract class Listeners {
    public static final Object[] listeners = {new BotReadier(), new SlashCommands()};
}
