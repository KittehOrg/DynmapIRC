package ca.celticminstrel.irc;

import static org.bukkit.event.EventPriority.MONITOR;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.monstercraft.irc.IRC;
import org.monstercraft.irc.plugin.event.events.IRCMessageEvent;
import org.monstercraft.irc.plugin.managers.handlers.IRCHandler;
import org.monstercraft.irc.plugin.wrappers.IRCChannel;

public class MonsterListener extends IRCListener implements Listener {
	@SuppressWarnings("unused")
	private IRC irc;
	private IRCHandler ircBot;

	public MonsterListener(Plugin plugin) {
		irc = (IRC)plugin;
	}

	@Override
	public void sendMessage(String ip, String from, String message, String to) {
		for(IRCChannel chan : IRC.getChannels())
			ircBot.sendMessage(chan.getChannel(), DynmapIRC.getPrefix() + " " + from + DynmapIRC.getSuffix() + ": " +
				message);
	}
	
	@Override
	public void setup() {
		ircBot = IRC.getHandleManager().getIRCHandler();
		Bukkit.getPluginManager().registerEvents(this, DynmapIRC.plugin);
	}
	
	@Override public void shutdown() {}
	
	@EventHandler(priority=MONITOR)
	public void onMessage(IRCMessageEvent evt) {
		String channel = evt.getIRCChannel().getChannel();
		String sender = evt.getName();
		String text = evt.getMessage();
		DynmapIRC.sendMessage(channel, sender, text, sender);
	}

	@Override
	public String getName() {
		return "MonsterIRC";
	}
}
