package me.grahamwilk.breweryxjobs;

import org.bukkit.plugin.java.JavaPlugin;
import me.grahamwilk.breweryxjobs.commands.BarkeeperSellMenu;

public final class BreweryxJobs extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("barkeepersellmenu").setExecutor(new BarkeeperSellMenu(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
