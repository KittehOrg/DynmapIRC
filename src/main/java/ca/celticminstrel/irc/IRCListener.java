package ca.celticminstrel.irc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public abstract class IRCListener {
    public abstract void sendMessage(String ip, String from, String message, String to);

    public abstract void shutdown();

    public abstract String getName();

    public static IRCListener find() {
        Plugin irc;
        irc = Bukkit.getPluginManager().getPlugin("CraftIRC"); // ~~~~~~
        if (irc != null)
            return new CraftIRC3Listener(irc); // ~~~~~~
        irc = Bukkit.getPluginManager().getPlugin("MonsterIRC"); // ~~~~~~
        if (irc != null)
            return new MonsterListener(irc); // ~~~~~~
        irc = Bukkit.getPluginManager().getPlugin("MinecraftBot"); // ~~~~~~
        if (irc != null)
            return new MinebotListener(irc); // ~~~~~~
        irc = Bukkit.getPluginManager().getPlugin("MineIRC"); // ~~~~~~
        if (irc != null)
            return new MineIRCListener(irc); // ~~~~~~
        return null;
    }
}
