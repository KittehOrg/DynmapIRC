package ca.celticminstrel.irc.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.monstercraft.irc.MonsterIRC;
import org.monstercraft.irc.plugin.event.events.IRCMessageEvent;
import org.monstercraft.irc.plugin.handles.IRCHandler;
import org.monstercraft.irc.plugin.wrappers.IRCChannel;

import ca.celticminstrel.irc.DynmapIRC;

public class MonsterListener extends IRCListener implements Listener {
    private IRCHandler ircBot;

    public MonsterListener(Plugin ircPlugin, Plugin dynmapirc) {
        ircBot = MonsterIRC.getHandleManager().getIRCHandler();
        dynmapirc.getServer().getPluginManager().registerEvents(this, dynmapirc);
    }

    @Override
    public void sendMessage(String ip, String from, String message, String to) {
        for (IRCChannel chan : MonsterIRC.getChannels())
            ircBot.sendMessage(chan.getChannel(), DynmapIRC.getPrefix() + " " + from + DynmapIRC.getSuffix() + ": " + message);
    }

    @Override
    public void shutdown() {
    }

    @EventHandler(priority = EventPriority.MONITOR)
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
