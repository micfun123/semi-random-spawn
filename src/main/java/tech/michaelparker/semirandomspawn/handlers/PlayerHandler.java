package tech.michaelparker.semirandomspawn.handlers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import tech.michaelparker.semirandomspawn.Semi_random_spawn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerHandler implements Listener {
    private final Semi_random_spawn plugin;

    public PlayerHandler(Semi_random_spawn plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // Only execute for new players
        if (player.hasPlayedBefore()) {
            return;
        } else {
            // Get config welcome message
            FileConfiguration config = plugin.getConfig();
            if (!config.contains("WelcomeMessage")) {
                return;
            }

            String welcomeMessage = config.getString("WelcomeMessage");
            // Send if not empty
            if (!welcomeMessage.isEmpty()) {
                welcomeMessage = welcomeMessage.replace("%player%", player.getName());
                player.sendMessage(welcomeMessage);
            }

            ConfigurationSection zonesSection = config.getConfigurationSection("Zones");
            if (zonesSection != null) {
                List<String> zoneKeys = new ArrayList<>(zonesSection.getKeys(false));
                if (!zoneKeys.isEmpty()) {
                    // Select a random zone
                    Random random = new Random();
                    String randomZoneKey = zoneKeys.get(random.nextInt(zoneKeys.size()));
                    ConfigurationSection randomZone = zonesSection.getConfigurationSection(randomZoneKey);

                    // Fetch zone coordinates
                    int x1 = randomZone.getInt("x1");
                    int z1 = randomZone.getInt("z1");
                    int x2 = randomZone.getInt("x2");
                    int z2 = randomZone.getInt("z2");

                    // Calculate random coordinates within the zone
                    int randomX = getRandomCoordinate(x1, x2);
                    int randomZ = getRandomCoordinate(z1, z2);
                    int y = player.getWorld().getHighestBlockYAt(randomX, randomZ);

                    // Teleport player to the random location
                    player.teleport(new org.bukkit.Location(player.getWorld(), randomX, y, randomZ));
                    player.setBedSpawnLocation(new org.bukkit.Location(player.getWorld(), randomX, y, randomZ));

                    // Send a welcome message with the zone information
                    player.sendMessage("Welcome to Zone: " + randomZoneKey);
                    player.sendMessage("Coordinates: X: " + randomX + ", Z: " + randomZ);
                }
            }
        }
    }

    private int getRandomCoordinate(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
