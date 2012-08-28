package ca.celticminstrel.irc;

import hef.IRCTransport.IRCTransport;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.permissions.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class IRCTransportListener extends AbstractIRCTransportListener {
	private IRCTransport irc;

	public IRCTransportListener(Plugin plugin) {
		irc = (IRCTransport)plugin;
	}
	
	@Override
	public String getName() {
		return "[DYNMAP]";
	}
	
	@Override
	public void sendMessage(String ip, String from, String message, String to) {
		PlayerChatEvent chatMessage = new PlayerChatEvent(this, DynmapIRC.getPrefix() + " " + from +
			DynmapIRC.getSuffix() + ": " + message);
		Bukkit.getPluginManager().callEvent(chatMessage);
		
	}
	
	@Override
	public void setup() {
		PlayerJoinEvent joinMessage = new PlayerJoinEvent(this, "Dynmap webchat is watching you!");
		Bukkit.getPluginManager().callEvent(joinMessage);
	}
	
	@Override
	public void shutdown() {
		PlayerQuitEvent quitMessage = new PlayerQuitEvent(this, "Dynmap webchat departs!");
		Bukkit.getPluginManager().callEvent(quitMessage);
	}
	
	@Override
	public void sendMessage(String message) {
		// TODO: Parse message to extract details
		DynmapIRC.sendMessage("[IRCTransport]", "?", message, "?");
	}
	
	@Override
	public void setDisplayName(String name) {}
}

abstract class AbstractIRCTransportListener extends IRCListener implements Player {
	@Override public PlayerInventory getInventory() {return null;}
	@Override public ItemStack getItemInHand() {return null;}
	@Override public void setItemInHand(ItemStack item) {}
	@Override public boolean isSleeping() {return false;}
	@Override public int getSleepTicks() {return 0;}
	@Override public GameMode getGameMode() {return null;}
	@Override public void setGameMode(GameMode mode) {}
	@Override public int getHealth() {return 0;}
	@Override public void setHealth(int health) {}
	@Override public int getMaxHealth() {return 0;}
	@Override public double getEyeHeight() {return 0;}
	@Override public double getEyeHeight(boolean ignoreSneaking) {return 0;}
	@Override public Location getEyeLocation() {return null;}
	@Override public List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance) {return null;}
	@Override public Block getTargetBlock(HashSet<Byte> transparent, int maxDistance) {return null;}
	@Override public List<Block> getLastTwoTargetBlocks(HashSet<Byte> transparent, int maxDistance) {return null;}
	@Override public Egg throwEgg() {return null;}
	@Override public Snowball throwSnowball() {return null;}
	@Override public Arrow shootArrow() {return null;}
	@Override public boolean isInsideVehicle() {return false;}
	@Override public boolean leaveVehicle() {return false;}
	@Override public Vehicle getVehicle() {return null;}
	@Override public int getRemainingAir() {return 0;}
	@Override public void setRemainingAir(int ticks) {}
	@Override public int getMaximumAir() {return 0;}
	@Override public void setMaximumAir(int ticks) {}
	@Override public void damage(int amount) {}
	@Override public void damage(int amount, Entity source) {}
	@Override public int getMaximumNoDamageTicks() {return 0;}
	@Override public void setMaximumNoDamageTicks(int ticks) {}
	@Override public int getLastDamage() {return 0;}
	@Override public void setLastDamage(int damage) {}
	@Override public int getNoDamageTicks() {return 0;}
	@Override public void setNoDamageTicks(int ticks) {}
	@Override public Player getKiller() {return null;}
	@Override public boolean addPotionEffect(PotionEffect effect) {return false;}
	@Override public boolean addPotionEffect(PotionEffect effect, boolean force) {return false;}
	@Override public boolean addPotionEffects(Collection<PotionEffect> effects) {return false;}
	@Override public boolean hasPotionEffect(PotionEffectType type) {return false;}
	@Override public void removePotionEffect(PotionEffectType type) {}
	@Override public Collection<PotionEffect> getActivePotionEffects() {return null;}
	@Override public Location getLocation() {return null;}
	@Override public void setVelocity(Vector velocity) {}
	@Override public Vector getVelocity() {return null;}
	@Override public World getWorld() {return null;}
	@Override public boolean teleport(Location location) {return false;}
	@Override public boolean teleport(Location location, TeleportCause cause) {return false;}
	@Override public boolean teleport(Entity destination) {return false;}
	@Override public boolean teleport(Entity destination, TeleportCause cause) {return false;}
	@Override public List<Entity> getNearbyEntities(double x, double y, double z) {return null;}
	@Override public int getEntityId() {return 0;}
	@Override public int getFireTicks() {return 0;}
	@Override public int getMaxFireTicks() {return 0;}
	@Override public void setFireTicks(int ticks) {}
	@Override public void remove() {}
	@Override public boolean isDead() {return false;}
	@Override public Server getServer() {return null;}
	@Override public Entity getPassenger() {return null;}
	@Override public boolean setPassenger(Entity passenger) {return false;}
	@Override public boolean isEmpty() {return false;}
	@Override public boolean eject() {return false;}
	@Override public float getFallDistance() {return 0;}
	@Override public void setFallDistance(float distance) {}
	@Override public void setLastDamageCause(EntityDamageEvent event) {}
	@Override public EntityDamageEvent getLastDamageCause() {return null;}
	@Override public UUID getUniqueId() {return null;}
	@Override public int getTicksLived() {return 0;}
	@Override public void setTicksLived(int value) {}
	@Override public void playEffect(EntityEffect type) {}
	@Override public boolean isPermissionSet(String name) {return false;}
	@Override public boolean isPermissionSet(Permission perm) {return false;}
	@Override public boolean hasPermission(String name) {return false;}
	@Override public boolean hasPermission(Permission perm) {return false;}
	@Override public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {return null;}
	@Override public PermissionAttachment addAttachment(Plugin plugin) {return null;}
	@Override public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {return null;}
	@Override public PermissionAttachment addAttachment(Plugin plugin, int ticks) {return null;}
	@Override public void removeAttachment(PermissionAttachment attachment) {}
	@Override public void recalculatePermissions() {}
	@Override public Set<PermissionAttachmentInfo> getEffectivePermissions() {return null;}
	@Override public boolean isOp() {return false;}
	@Override public void setOp(boolean value) {}
	@Override public boolean isOnline() {return false;}
	@Override public boolean isBanned() {return false;}
	@Override public void setBanned(boolean banned) {}
	@Override public boolean isWhitelisted() {return false;}
	@Override public void setWhitelisted(boolean value) {}
	@Override public Player getPlayer() {return this;}
	@Override public long getFirstPlayed() {return 0;}
	@Override public long getLastPlayed() {return 0;}
	@Override public boolean hasPlayedBefore() {return false;}
	@Override public Map<String,Object> serialize() {return null;}
	@Override public void sendPluginMessage(Plugin source, String channel, byte[] message) {}
	@Override public Set<String> getListeningPluginChannels() {return null;}
	@Override public String getDisplayName() {return null;}
	@Override public void setPlayerListName(String name) {}
	@Override public void setCompassTarget(Location loc) {}
	@Override public Location getCompassTarget() {return null;}
	@Override public InetSocketAddress getAddress() {return null;}
	@Override public void sendRawMessage(String message) {}
	@Override public void kickPlayer(String message) {}
	@Override public void chat(String msg) {}
	@Override public boolean performCommand(String command) {return false;}
	@Override public boolean isSneaking() {return false;}
	@Override public void setSneaking(boolean sneak) {}
	@Override public boolean isSprinting() {return false;}
	@Override public void setSprinting(boolean sprinting) {}
	@Override public void saveData() {}
	@Override public void loadData() {}
	@Override public void setSleepingIgnored(boolean isSleeping) {}
	@Override public boolean isSleepingIgnored() {return false;}
	@Override public void playNote(Location loc, byte instrument, byte note) {}
	@Override public void playNote(Location loc, Instrument instrument, Note note) {}
	@Override public void playEffect(Location loc, Effect effect, int data) {}
	@Override public void sendBlockChange(Location loc, Material material, byte data) {}
	@Override public boolean sendChunkChange(Location loc, int sx, int sy, int sz, byte[] data) {return false;}
	@Override public void sendBlockChange(Location loc, int material, byte data) {}
	@Override public void sendMap(MapView map) {}
	@Override public void updateInventory() {}
	@Override public void awardAchievement(Achievement achievement) {}
	@Override public void incrementStatistic(Statistic statistic) {}
	@Override public void incrementStatistic(Statistic statistic, int amount) {}
	@Override public void incrementStatistic(Statistic statistic, Material material) {}
	@Override public void incrementStatistic(Statistic statistic, Material material, int amount) {}
	@Override public void setPlayerTime(long time, boolean relative) {}
	@Override public boolean isPlayerTimeRelative() {return false;}
	@Override public void resetPlayerTime() {}
	@Override public void giveExp(int amount) {}
	@Override public float getExp() {return 0;}
	@Override public void setExp(float exp) {}
	@Override public int getTotalExperience() {return 0;}
	@Override public void setTotalExperience(int exp) {}
	@Override public float getExhaustion() {return 0;}
	@Override public void setExhaustion(float value) {}
	@Override public float getSaturation() {return 0;}
	@Override public Location getBedSpawnLocation() {return null;}
	@Override public void setBedSpawnLocation(Location location) {}
	@Override public boolean getAllowFlight() {return false;}
	@Override public void setAllowFlight(boolean flight) {}
	@Override public void hidePlayer(Player player) {}
	@Override public boolean canSee(Player player) {return false;}
	@Override public int getExperience() {return 0;}
	@Override public String getPlayerListName() {return null;}
	@Override public long getPlayerTime() {return 0;}
	@Override public long getPlayerTimeOffset() {return 0;}
	@Override public int getLevel() {return 0;}
	@Override public int getFoodLevel() {return 0;}
	@Override public void setExperience(int arg0) {}
	@Override public void setLevel(int level) {}
	@Override public void setSaturation(float value) {}
	@Override public void setFoodLevel(int value) {}
	@Override public void showPlayer(Player player) {}
}