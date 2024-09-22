package me.grahamwilk.breweryxjobs.commands;

import com.dre.brewery.Brew;
import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.actions.ItemNameActionInfo;
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
import me.grahamwilk.breweryxjobs.BreweryxJobs;

import java.util.ArrayList;
import java.util.List;

public class BarkeeperSellMenu implements Listener, CommandExecutor {
    private String invName = "Barkeeper Sell Menu";
    Inventory inv = Bukkit.createInventory(null, 9 * 3, invName);
    private boolean alreadySold = false;
    public BarkeeperSellMenu(BreweryxJobs plugin) {
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
        for (int i = 0; i < 26; i++) {
            ItemStack item = inv.getItem(i);
            if (item == null){
                continue;
            }
            Brew brew = Brew.get(item);
            if (brew == null)
            {
                playerInv.addItem(this.inv.getItem(i));
                continue;
            }
            int brewQuality = brew.getQuality();
            String brewName = brew.getCurrentRecipe().getName(5);
            ItemNameActionInfo info = new ItemNameActionInfo(brewName,ActionType.CRAFT);
            for (int j = brewQuality; j > 0; j--){
                Jobs.action(thePlayer, info);
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
            for (int i = 0; i < 26; i++) {
                ItemStack item = inv.getItem(i);
                if (item == null){
                    continue;
                }
                Brew brew = Brew.get(item);
                if (brew == null)
                {
                    playerInv.addItem(this.inv.getItem(i));
                    continue;
                }
                int brewQuality = brew.getQuality();
                String brewName = brew.getCurrentRecipe().getName(5);
                ItemNameActionInfo info = new ItemNameActionInfo(brewName,ActionType.CRAFT);
                for (int j = brewQuality; j > 0; j--){
                    Jobs.action(thePlayer, info);
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
