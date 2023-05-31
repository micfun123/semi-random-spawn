package tech.michaelparker.semirandomspawn.handlers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tech.michaelparker.semirandomspawn.Semi_random_spawn;

public class PlayerHandler implements org.bukkit.event.Listener{
    public PlayerHandler(Semi_random_spawn plugin) {
        Bukkit.getPluginManager().registerEvents((Listener)this, (org.bukkit.plugin.Plugin)plugin);
    }

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        // new players only
        if (player.hasPlayedBefore()) {
            return;
        }
        else {
            //get config welcome message
            FileConfiguration config = Semi_random_spawn.getPlugin(Semi_random_spawn.class).getConfig();
            String welcomeMessage = config.getString("WelcomeMessage");
            // send if not empty
            if (welcomeMessage.isEmpty()) {
                return;
            }
            else {
                welcomeMessage = welcomeMessage.replace("%player%", player.getName());
                player.sendMessage(welcomeMessage);
            }


            //get random location
            double x = Math.random() * 1000;
            double z = Math.random() * 1000;
            double y = player.getWorld().getHighestBlockYAt((int) x, (int) z);
            //teleport player
            player.teleport(new org.bukkit.Location(player.getWorld(), x, y, z));
            player.setBedSpawnLocation(new org.bukkit.Location(player.getWorld(), x, y, z));
        }

    }

}
