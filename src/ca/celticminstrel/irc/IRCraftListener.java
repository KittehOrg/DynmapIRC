package ca.celticminstrel.irc;

import java.lang.reflect.Field;

import org.bukkit.plugin.Plugin;

import me.nareshkumarrao.IRCraft.IRCraft;
import me.nareshkumarrao.IRCraft.IRCraftBot;

public class IRCraftListener extends IRCListener {
	private IRCraft irc;
	private IRCraftBot ircBot;

	public IRCraftListener(Plugin plugin) {
		irc = (IRCraft)plugin;
	}

	@Override
	public void sendMessage(String ip, String from, String message, String to) {
		ircBot.sendMessage(irc.channel, DynmapIRC.getPrefix() + " " + from + DynmapIRC.getSuffix() + ": " + message);
	}
	
	@Override
	public void setup() {
		try {
			Field bot = irc.getClass().getDeclaredField("bot");
			bot.setAccessible(true);
			ircBot = (IRCraftBot)bot.get(irc);
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
		return "IRCraft";
	}
	
}
