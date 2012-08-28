package ca.celticminstrel.irc.listeners;

import java.lang.reflect.Field;

import org.bukkit.plugin.Plugin;

import ca.celticminstrel.irc.DynmapIRC;

import com.avisenera.minecraftbot.listeners.IRCManager;
import com.avisenera.minecraftbot.MinecraftBot;

public class MinebotListener extends IRCListener {
    private IRCManager ircManager;

    public MinebotListener(Plugin plugin) {
        MinecraftBot irc = (MinecraftBot) plugin;
        try {
            Field bot = irc.getClass().getDeclaredField("irc");
            bot.setAccessible(true);
            ircManager = (IRCManager) bot.get(irc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String ip, String from, String message, String to) {
        ircManager.sendMessage(DynmapIRC.getPrefix() + " " + from + DynmapIRC.getSuffix() + ": " + message);
    }

    @Override
    public void shutdown() {
    }

    @Override
    public String getName() {
        return "MinecraftBot";
    }
}
