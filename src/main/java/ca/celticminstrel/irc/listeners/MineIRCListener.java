package ca.celticminstrel.irc.listeners;

import java.lang.reflect.Field;

import ca.celticminstrel.irc.DynmapIRC;

import com.jca2323.MineIRC.MineBot;
import com.jca2323.MineIRC.MineIRCCore;

import org.bukkit.plugin.Plugin;

public class MineIRCListener extends IRCListener {
    private MineIRCCore irc;
    private MineBot ircBot;

    public MineIRCListener(Plugin plugin) {
        irc = (MineIRCCore) plugin;
        try {
            Field bot = irc.getClass().getDeclaredField("bot");
            bot.setAccessible(true);
            ircBot = (MineBot) bot.get(irc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String ip, String from, String message, String to) {
        ircBot.sendMessage(ircBot.getChannel(), DynmapIRC.getPrefix() + " " + from + DynmapIRC.getSuffix() + ": " + message);
    }

    @Override
    public void shutdown() {
    }

    @Override
    public String getName() {
        return "MineIRC";
    }
}
