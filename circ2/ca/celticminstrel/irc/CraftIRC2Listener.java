package ca.celticminstrel.irc;

import org.bukkit.plugin.Plugin;

import com.ensifera.animosity.craftirc.CraftIRC;

public class CraftIRC2Listener extends IRCListener {
	private CraftIRC irc;
	
	public CraftIRC2Listener(Plugin plugin) {
		irc = (CraftIRC)plugin;
	}

	@Override
	public void sendMessage(String ip, String from, String message, String to) {
		irc.sendMessageToTag(DynmapIRC.getPrefix() + " " + from + DynmapIRC.getSuffix() + ": " +
			message, "all");
	}

	@Override
	public void setup() {}
	
	@Override
	public String getName() {
		return "CraftIRC 2";
	}
}
