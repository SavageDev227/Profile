package com.savage.plugins.profile.guis;

import com.savage.plugins.profile.ColorUtils;
import com.savage.plugins.profile.Profile;
import com.savage.plugins.profile.guis.menuutilities.MenuSystem;
import com.savage.plugins.profile.guis.menuutilities.PlayerMenuUtility;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;


import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import static com.savage.plugins.profile.guis.ProfileGUI.taskID1;

public class InfoGUI extends MenuSystem {

    public InfoGUI(PlayerMenuUtility menuUtility) {
        super(menuUtility);
    }

    FileConfiguration config = Profile.getPlugin().getConfig();

    @Override
    public String getMenuName() {
        return config.getString("profile-name");
    }

    @Override
    public int getSlots() {
        return config.getInt("profile-size");
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
    }


    @Override
    public void setMenuItems() {
        UUID uuid = menuUtility.getPlayerToMod().getUniqueId();
        String targetname = menuUtility.getPlayerToMod().getName();
        Server server = Bukkit.getServer();
        Player onlineTarget = Bukkit.getPlayer(uuid);


        //PlayerHead
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta) playerHead.getItemMeta();
        skull.setOwningPlayer(server.getOfflinePlayer(uuid));
        playerHead.setItemMeta(skull);
        ItemMeta PMeta = playerHead.getItemMeta();
        PMeta.setDisplayName(ChatColor.WHITE + targetname);
        playerHead.setItemMeta(PMeta);
        inv.setItem(4, playerHead);

        //Fly
        ItemStack Elytra = new ItemStack(Material.ELYTRA, 1);
        ItemMeta flymeta = Elytra.getItemMeta();
        flymeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Can Fly: " + ChatColor.GREEN + onlineTarget.getAllowFlight());
        Elytra.setItemMeta(flymeta);
        inv.setItem(22,Elytra);

        //Balance
        Economy eco = Profile.getEconomy();
        ItemStack money = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta moneymeta = money.getItemMeta();
        moneymeta.setDisplayName(ChatColor.GOLD + "Balance: " + eco.format(eco.getBalance(targetname)));
        money.setItemMeta(moneymeta);
        inv.setItem(20,money);

        //Rank
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(onlineTarget);
        String userrank = user.getPrimaryGroup();
        ItemStack rank = new ItemStack(Material.PAPER,1);
        ItemMeta rankmeta = rank.getItemMeta();
        rankmeta.setDisplayName(ChatColor.AQUA + "Rank: " + ChatColor.LIGHT_PURPLE + userrank);
        rank.setItemMeta(rankmeta);
        inv.setItem(24, rank);

        //Fillers
        //ItemStack fill = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1);
        //ItemMeta fillmeta = fill.getItemMeta();
        //fillmeta.setDisplayName(" ");
        //fill.setItemMeta(fillmeta);


    }


}
