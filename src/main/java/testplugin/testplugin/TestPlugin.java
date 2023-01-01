package testplugin.testplugin;

import org.bukkit.plugin.java.JavaPlugin;
import testplugin.testplugin.commands.BarkeeperSellMenu;

public final class TestPlugin extends JavaPlugin {

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
