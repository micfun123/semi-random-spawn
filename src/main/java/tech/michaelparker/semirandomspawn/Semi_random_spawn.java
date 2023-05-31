package tech.michaelparker.semirandomspawn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Semi_random_spawn extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("\n" +
                "   _____                _                                _                                                  \n" +
                "  / ____|              (_)                              | |                                                 \n" +
                " | (___   ___ _ __ ___  _   ______   _ __ __ _ _ __   __| | ___  _ __ ___    ___ _ __   __ ___      ___ __  \n" +
                "  \\___ \\ / _ \\ '_ ` _ \\| | |______| | '__/ _` | '_ \\ / _` |/ _ \\| '_ ` _ \\  / __| '_ \\ / _` \\ \\ /\\ / / '_ \\ \n" +
                "  ____) |  __/ | | | | | |          | | | (_| | | | | (_| | (_) | | | | | | \\__ \\ |_) | (_| |\\ V  V /| | | |\n" +
                " |_____/ \\___|_| |_| |_|_|          |_|  \\__,_|_| |_|\\__,_|\\___/|_| |_| |_| |___/ .__/ \\__,_| \\_/\\_/ |_| |_|\n" +
                "                                                                                | |                         \n" +
                "                                                                                |_|                         \n" +
                "\n");
        Bukkit.getLogger().info("Thank you for using the free version SemiRandomSpawn! Feel free to donate here https://www.buymeacoffee.com/Michaelrbparker");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("SHUTDOWN");
    }
}
