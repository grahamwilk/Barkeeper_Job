package testplugin.testplugin.commands;

import com.dre.brewery.Brew;
import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.actions.ItemActionInfo;
import com.gamingmesh.jobs.container.ActionType;
import com.gamingmesh.jobs.container.JobProgression;
import com.gamingmesh.jobs.container.JobsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import testplugin.testplugin.TestPlugin;

import java.util.*;

public class BarkeeperSellMenu  implements Listener, CommandExecutor {
    private String invName = "Barkeeper Sell Menu";
    Inventory inv = Bukkit.createInventory(null, 9 * 3, invName);
    private boolean alreadySold = false;
    public BarkeeperSellMenu(TestPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryClose(InventoryCloseEvent event){
        if (!event.getView().getTitle().equals(invName)){
            return;
        }
        if (this.alreadySold == true){
            return;
        }
        this.alreadySold = true;
        Player player = (Player) event.getPlayer();
        Inventory playerInv = player.getInventory();
        JobsPlayer thePlayer = Jobs.getPlayerManager().getJobsPlayer(player);
        ItemStack anItemStack = new ItemStack(Material.BEDROCK,1);
        for (int i = 0; i < 26; i++) {
            if (this.inv.getItem(i) == null){
                Bukkit.getLogger().info("There is no item in slot " + i);
                continue;
            }
            if (Brew.get(this.inv.getItem(i)) == null)
            {
                playerInv.addItem(this.inv.getItem(i));
                Bukkit.getLogger().info("There is no brew in slot " + i + "but there is an item");
                continue;
            }
            Bukkit.getLogger().info("There is a brew in slot " + i);
            Brew brew = Brew.get(inv.getItem(i));
            int brewQuality = (brew.getQuality());
            String brewName = brew.getCurrentRecipe().getName(5);
            Bukkit.getLogger().info("The name of wheatbeer is: " + brewName);
            if (brewName.equals("Wheatbeer")){anItemStack = new ItemStack(Material.WHEAT,1);}
            else if (brewName.equals("Beer")){anItemStack = new ItemStack(Material.HAY_BLOCK,1);}
            else if (brewName.equals("Darkbeer")){anItemStack = new ItemStack(Material.DARK_OAK_LOG,1);}
            else if (brewName.equals("Red Wine")){anItemStack = new ItemStack(Material.SWEET_BERRIES,1);}
            else if (brewName.equals("Mead")){anItemStack = new ItemStack(Material.SUGAR_CANE,1);}
            else if (brewName.equals("Sweet Apple Mead")){anItemStack = new ItemStack(Material.GOLDEN_APPLE,1);}
            else if (brewName.equals("Apple Cider")){anItemStack = new ItemStack(Material.APPLE,1);}
            else if (brewName.equals("Apple Liquor")){anItemStack = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE,1);}
            else if (brewName.equals("Whiskey")){anItemStack = new ItemStack(Material.WHEAT_SEEDS,1);}
            else if (brewName.equals("Rum")){anItemStack = new ItemStack(Material.PAPER,1);}
            else if (brewName.equals("Vodka")){anItemStack = new ItemStack(Material.POTATO,1);}
            else if (brewName.equals("Mushroom Vodka")){anItemStack = new ItemStack(Material.RED_MUSHROOM,1);}
            else if (brewName.equals("Gin")){anItemStack = new ItemStack(Material.BLUE_ORCHID,1);}
            else if (brewName.equals("Tequila")){anItemStack = new ItemStack(Material.CACTUS,1);}
            else if (brewName.equals("Absinthe")){anItemStack = new ItemStack(Material.GRASS,1);}
            else if (brewName.equals("Green Absinthe")){anItemStack = new ItemStack(Material.POISONOUS_POTATO,1);}
            else if (brewName.equals("Potato Soup")){anItemStack = new ItemStack(Material.BAKED_POTATO,1);}
            else if (brewName.equals("Coffee")){anItemStack = new ItemStack(Material.COCOA_BEANS,1);}
            else if (brewName.equals("Eggnog")){anItemStack = new ItemStack(Material.EGG,1);}
            else{anItemStack = new ItemStack(Material.BEETROOT_SOUP,1);}
            for (int j = brewQuality; j > 0; j--){
                Jobs.action(thePlayer, new ItemActionInfo(anItemStack, ActionType.BREW));
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (!event.getView().getTitle().equals(invName)){
            return;
        }
        // checks if the player clicked confirm
        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();
        if (slot == 26) {
            if (this.alreadySold == true){
                return;
            }
            this.alreadySold = true;
            Inventory playerInv = player.getInventory();
            JobsPlayer thePlayer = Jobs.getPlayerManager().getJobsPlayer(player);
            ItemStack anItemStack = new ItemStack(Material.BEDROCK,1);
            for (int i = 0; i < 26; i++) {
                if (this.inv.getItem(i) == null){
                    Bukkit.getLogger().info("There is no item in slot " + i);
                    continue;
                }
                if (Brew.get(this.inv.getItem(i)) == null)
                {
                    playerInv.addItem(this.inv.getItem(i));
                    Bukkit.getLogger().info("There is no brew in slot " + i + "but there is an item");
                    continue;
                }
                Bukkit.getLogger().info("There is a brew in slot " + i);
                Brew brew = Brew.get(inv.getItem(i));
                int brewQuality = (brew.getQuality());
                String brewName = brew.getCurrentRecipe().getName(5);
                Bukkit.getLogger().info("The name of wheatbeer is: " + brewName);
                if (brewName.equals("Wheatbeer")){anItemStack = new ItemStack(Material.WHEAT,1);}
                else if (brewName.equals("Beer")){anItemStack = new ItemStack(Material.HAY_BLOCK,1);}
                else if (brewName.equals("Darkbeer")){anItemStack = new ItemStack(Material.DARK_OAK_LOG,1);}
                else if (brewName.equals("Red Wine")){anItemStack = new ItemStack(Material.SWEET_BERRIES,1);}
                else if (brewName.equals("Mead")){anItemStack = new ItemStack(Material.SUGAR_CANE,1);}
                else if (brewName.equals("Sweet Apple Mead")){anItemStack = new ItemStack(Material.GOLDEN_APPLE,1);}
                else if (brewName.equals("Apple Cider")){anItemStack = new ItemStack(Material.APPLE,1);}
                else if (brewName.equals("Apple Liquor")){anItemStack = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE,1);}
                else if (brewName.equals("Whiskey")){anItemStack = new ItemStack(Material.WHEAT_SEEDS,1);}
                else if (brewName.equals("Rum")){anItemStack = new ItemStack(Material.PAPER,1);}
                else if (brewName.equals("Vodka")){anItemStack = new ItemStack(Material.POTATO,1);}
                else if (brewName.equals("Mushroom Vodka")){anItemStack = new ItemStack(Material.RED_MUSHROOM,1);}
                else if (brewName.equals("Gin")){anItemStack = new ItemStack(Material.BLUE_ORCHID,1);}
                else if (brewName.equals("Tequila")){anItemStack = new ItemStack(Material.CACTUS,1);}
                else if (brewName.equals("Absinthe")){anItemStack = new ItemStack(Material.GRASS,1);}
                else if (brewName.equals("Green Absinthe")){anItemStack = new ItemStack(Material.POISONOUS_POTATO,1);}
                else if (brewName.equals("Potato Soup")){anItemStack = new ItemStack(Material.BAKED_POTATO,1);}
                else if (brewName.equals("Coffee")){anItemStack = new ItemStack(Material.COCOA_BEANS,1);}
                else if (brewName.equals("Eggnog")){anItemStack = new ItemStack(Material.EGG,1);}
                else{anItemStack = new ItemStack(Material.BEETROOT_SOUP,1);}
                for (int j = brewQuality; j > 0; j--){
                    Jobs.action(thePlayer, new ItemActionInfo(anItemStack, ActionType.BREW));
                }
            }
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            player.closeInventory();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command.");
            return true;
        }
        Player player = (Player) sender;
        List<JobProgression> jobs = Jobs.getPlayerManager().getJobsPlayer(player).getJobProgression();
        boolean hasJob = false;

        for (JobProgression OneJob : jobs) {
            if (OneJob.getJob().getName().equals("Barkeeper")){
                hasJob = true;
            }
        }
        if (!hasJob)
        {
            sender.sendMessage("Only players in the barkeeper job can run this command.");
            return true;
        }

        this.alreadySold = false;

        this.inv = Bukkit.createInventory(player, 9 * 3, invName);

        this.inv.setItem(26, getItem(new ItemStack(Material.GREEN_STAINED_GLASS_PANE), "Confirm","Click here to confirm your sell"));

        player.openInventory(this.inv);
        
        return true;
    }
    private ItemStack getItem(ItemStack item, String name, String ... lore){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        List<String> lores = new ArrayList<>();
        for (String s : lore) {
            lores.add(s);
        }
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }
}
