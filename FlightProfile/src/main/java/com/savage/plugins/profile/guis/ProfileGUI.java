package com.savage.plugins.profile.guis;


import com.savage.plugins.profile.ColorUtils;
import com.savage.plugins.profile.Profile;
import com.savage.plugins.profile.guis.menuutilities.PlayerMenuUtility;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;


import java.util.ArrayList;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;


public class ProfileGUI extends PagedMenu {

    FileConfiguration config = Profile.getPlugin().getConfig();
    Server server = Profile.getPlugin().getServer();

    public static Integer taskID1;

    public ProfileGUI(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }



    public String getMenuName() {
        return ColorUtils.translateColorCodes(config.getString("playerlist-name"));
    }


    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Player p = Bukkit.getPlayer(event.getCurrentItem().getItemMeta().getDisplayName());
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(p.getUniqueId());
        ArrayList<Player> players = new ArrayList<Player>(server.getOnlinePlayers());
        if (event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
            PlayerMenuUtility playerMenuUtility = Profile.getPlayerMenuUtility(p);
            playerMenuUtility.setPlayerToMod(offlinePlayer);
            String playerToMod = playerMenuUtility.getPlayerToMod().getName();
            new InfoGUI(playerMenuUtility).openMenu();

        } else if (event.getCurrentItem().getType().equals(Material.BARRIER)) {
            p.closeInventory();
            Bukkit.getScheduler().cancelTask(taskID1);
        }
        else if(event.getCurrentItem().getType().equals(Material.STONE_BUTTON)) {
            if(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Previous Page")) {
                if(page == 0) {
                    p.sendMessage(ChatColor.RED + "This is the first page!");
                } else {
                    page = page -1;
                }
            } else if(event.getCurrentItem().getType().equals(Material.STONE_BUTTON)) {
                if(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Next Page")) {
                    if(!((index + 1) >= players.size())) {
                        page = page + 1;
                        super.openMenu();
                    } else {
                        p.sendMessage(ChatColor.DARK_RED + "This is the last page!");
                    }
                }
            }
        }
    }


    @Override
    public void setMenuItems() {
        taskID1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Profile.getPlugin(Profile.class), new Runnable() {
            @Override
            public void run() {
                //The thing you will be looping through to place items
                ArrayList<Player> players = new ArrayList<Player>(getServer().getOnlinePlayers());

                //Pagination loop template
                if(players != null && !players.isEmpty()) {
                    for(int i = 0; i < getMaxItemsPerPage(); i++) {
                        index = getMaxItemsPerPage() * page + i;
                        if(index >= players.size()) break;
                        if (players.get(index) != null){

                            //Create an item from our collection and place it into the inventory
                            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
                            SkullMeta skull = (SkullMeta) playerHead.getItemMeta();
                            UUID uuid = players.get(index).getUniqueId();
                            skull.setOwningPlayer(getServer().getOfflinePlayer(uuid));
                            playerHead.setItemMeta(skull);
                            ItemMeta meta = playerHead.getItemMeta();
                            meta.setDisplayName(players.get(index).getName());
                            ArrayList<String> lore = new ArrayList<>();
                            lore.add(ChatColor.WHITE + "Player Health: " + ChatColor.LIGHT_PURPLE + players.get(index).getHealth());
                            lore.add(ChatColor.WHITE + "Player Food: " + ChatColor.LIGHT_PURPLE + players.get(index).getFoodLevel());
                            lore.add(ChatColor.WHITE + "Player XP: " + ChatColor.LIGHT_PURPLE + players.get(index).getLevel());
                            lore.add(ChatColor.WHITE + "Gamemode: " + ChatColor.LIGHT_PURPLE + players.get(index).getGameMode());
                            lore.add(ChatColor.WHITE + "Has Fly: " + ChatColor.LIGHT_PURPLE + players.get(index).getAllowFlight());

                            meta.setLore(lore);
                            playerHead.setItemMeta(meta);

                            inv.setItem(index, playerHead);

                        }
                    }
                }
            }
        }, 0, 40);
    }

}
