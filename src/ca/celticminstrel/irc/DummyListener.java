package ca.celticminstrel.irc;

import org.bukkit.plugin.Plugin;

public class DummyListener extends IRCListener {
	public DummyListener(@SuppressWarnings("unused") Plugin irc) {}
	@Override public void sendMessage(String ip, String from, String message, String to) {}
	@Override public void setup() {}
	@Override public void shutdown() {}
	@Override public String getName() {return "None!";}
}
