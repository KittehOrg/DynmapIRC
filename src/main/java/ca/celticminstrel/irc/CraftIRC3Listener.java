package ca.celticminstrel.irc;

import java.util.List;

import org.bukkit.plugin.Plugin;

import com.ensifera.animosity.craftirc.CraftIRC;
import com.ensifera.animosity.craftirc.EndPoint;
import com.ensifera.animosity.craftirc.RelayedMessage;

public class CraftIRC3Listener extends IRCListener implements EndPoint {
    private CraftIRC irc;

    public CraftIRC3Listener(Plugin irc2) {
        irc = (CraftIRC) irc2;
        irc.registerEndPoint("dynmap", this);
    }

    @Override
    public Type getType() {
        return Type.MINECRAFT;
    }

    @Override
    public List<String> listDisplayUsers() {
        return listUsers();
    }

    @Override
    public void shutdown() {
    }

    @Override
    // TODO: A way to get a list of viewing IPs?
    public List<String> listUsers() {
        return null;
    }

    @Override
    public void messageIn(RelayedMessage msg) {
        String text = msg.getField("message");
        String sender = msg.getField("sender");
        String name = msg.getField("realSender");
        if (name == null)
            name = msg.getField("username");
        String channel = msg.getField("srcChannel");
        DynmapIRC.sendMessage(channel, sender, text, name);
    }

    // We can't handle incoming private or admin messages
    @Override
    public boolean adminMessageIn(RelayedMessage msg) {
        return false;
    }

    @Override
    public boolean userMessageIn(String user, RelayedMessage msg) {
        return false;
    }

    @Override
    public void sendMessage(String ip, String from, String message, String to) {
        RelayedMessage msg = irc.newMsg(this, null, "dynmap");
        msg.setField("message", message);
        msg.setField("ip", ip);
        msg.setField("source", from);
        msg.setField("prefix", DynmapIRC.getPrefix());
        msg.setField("suffix", DynmapIRC.getSuffix());
        msg.post();
    }

    @Override
    public String getName() {
        return "CraftIRC 3";
    }
}
