package ca.celticminstrel.irc;

import java.lang.reflect.Field;

import org.bukkit.plugin.Plugin;

import me.rafa652.minecraftbot.IRCHandler;
import me.rafa652.minecraftbot.MinecraftBot;

public class MinebotListener extends IRCListener {
	private MinecraftBot irc;
	private IRCHandler ircBot;

	public MinebotListener(Plugin plugin) {
		irc = (MinecraftBot)plugin;
	}

	@Override
	public void sendMessage(String ip, String from, String message, String to) {
		ircBot.sendMessage(ircBot.getChannel(), DynmapIRC.getPrefix() + " " + from + DynmapIRC.getSuffix() + ": " +
			message);
	}
	
	@Override
	public void setup() {
		try {
			Field bot = irc.getClass().getDeclaredField("bot");
			bot.setAccessible(true);
			ircBot = (IRCHandler)bot.get(irc);
		} catch(SecurityException e) {
			e.printStackTrace();
		} catch(NoSuchFieldException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Override public void shutdown() {}

	@Override
	public String getName() {
		return "MinecraftBot";
	}
}
