package ca.celticminstrel.irc;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;
import org.dynmap.DynmapWebChatEvent;

import ca.celticminstrel.irc.listeners.*;

public class DynmapIRC extends JavaPlugin implements Listener {
    private IRCListener irc;
    private static DynmapAPI web;

    @Override
    public void onDisable() {
        irc.shutdown();
    }

    @Override
    public void onEnable() {
        web = (DynmapAPI) getServer().getPluginManager().getPlugin("dynmap");
        irc = this.find();
        if (irc != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
            getLogger().info("Hooked into " + irc.getName() + "!");
        } else {
            Bukkit.getPluginManager().disablePlugin(this);
            getLogger().info("No IRC plugin found, so I can't run!");
        }
    }

    private IRCListener find() {
        Plugin irc;
        PluginManager pluginManager = this.getServer().getPluginManager();
        irc = pluginManager.getPlugin("CraftIRC");
        if (irc != null)
            return new CraftIRC3Listener(irc);
        irc = pluginManager.getPlugin("MonsterIRC");
        if (irc != null)
            return new MonsterListener(irc, this);
        irc = pluginManager.getPlugin("MinecraftBot");
        if (irc != null)
            return new MinebotListener(irc);
        irc = pluginManager.getPlugin("MineIRC");
        if (irc != null)
            return new MineIRCListener(irc);
        return null;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDynmapChat(DynmapWebChatEvent evt) {
        irc.sendMessage(evt.getName(), evt.getSource(), evt.getMessage(), "to");
    }

    public static String getPrefix() {
        return "\u00A72[WEB] ";
        //return web.getConfig().getString("webprefix", "\u00A72[WEB] ");
    }

    public static String getSuffix() {
        return "\u00A7f";
        //return web.getConfig().getString("websuffix", "\u00A7f");
    }

    public static void sendMessage(String channel, String sender, String message, String account) {
        web.postPlayerMessageToWeb(account, sender, '[' + channel + ']' + message);
        //web.mapManager.pushUpdate(msg);
    }
}
