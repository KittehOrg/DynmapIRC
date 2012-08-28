package ca.celticminstrel.irc.listeners;

public abstract class IRCListener {
    public abstract void sendMessage(String ip, String from, String message, String to);

    public abstract void shutdown();

    public abstract String getName();
}
