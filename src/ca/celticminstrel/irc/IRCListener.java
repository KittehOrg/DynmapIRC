package ca.celticminstrel.irc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public abstract class IRCListener {
	public abstract void sendMessage(String ip, String from, String message, String to);
	public abstract void setup();
	public abstract void shutdown();
	public abstract String getName();
	
	public static IRCListener find() {
		Plugin irc;
		irc = Bukkit.getPluginManager().getPlugin("CraftIRC");
		if(irc != null) {
			int version = Integer.valueOf(irc.getDescription().getVersion().split(".")[0]);
			return version < 3 ? new CraftIRC2Listener(irc) : new CraftIRC3Listener(irc);
		}
		/* Exclude this portion when compiling against CraftIRC2 */	// ~~~~~~
		irc = Bukkit.getPluginManager().getPlugin("MonsterIRC");	// ~~~~~~
		if(irc != null) return new MonsterListener(irc);			// ~~~~~~
		irc = Bukkit.getPluginManager().getPlugin("MinecraftBot");	// ~~~~~~
		if(irc != null) return new MinebotListener(irc);			// ~~~~~~
		irc = Bukkit.getPluginManager().getPlugin("MineIRC");		// ~~~~~~
		if(irc != null) return new MineIRCListener(irc);			// ~~~~~~
		irc = Bukkit.getPluginManager().getPlugin("IRCraft");		// ~~~~~~
		if(irc != null) return new IRCraftListener(irc);			// ~~~~~~
		return null;
	}
}
