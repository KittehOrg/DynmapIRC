package ca.celticminstrel.irc;

import java.lang.reflect.Field;

import com.jca2323.MineIRC.MineBot;
import com.jca2323.MineIRC.MineIRCCore;

import org.bukkit.plugin.Plugin;

public class MineIRCListener extends IRCListener {
	private MineIRCCore irc;
	private MineBot ircBot;

	public MineIRCListener(Plugin plugin) {
		irc = (MineIRCCore)plugin;
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
			ircBot = (MineBot)bot.get(irc);
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
		return "MineIRC";
	}
}
