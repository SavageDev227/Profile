package com.savage.plugins.profile;


import com.savage.plugins.profile.commands.ProfileCommand;
import com.savage.plugins.profile.commands.SaveConfigCommand;
import com.savage.plugins.profile.guis.ProfileGUI;
import com.savage.plugins.profile.guis.listeners.Menulisteners;
import com.savage.plugins.profile.guis.menuutilities.PlayerMenuUtility;
import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.ServiceLoader;
import java.util.logging.Logger;

public final class Profile extends JavaPlugin {

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    private static Profile plugin;
    private static Economy econ = null;


    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        saveDefaultConfig();
        getCommand("profile").setExecutor(new ProfileCommand());
        getCommand("saveconfig").setExecutor(new SaveConfigCommand());
        getServer().getPluginManager().registerEvents(new Menulisteners(), this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        if(!setupEconomy()) {
            System.out.println("No economy plugin found! Disabling Vault");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        RegisteredServiceProvider<LuckPerms> rsp = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if(rsp != null) {
            LuckPerms api = rsp.getProvider();
        }

    }

    @Override
    public void onDisable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
    public static Profile getPlugin() {
        return plugin;
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player player) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(player))) {
            playerMenuUtility = new PlayerMenuUtility(player);
            playerMenuUtilityMap.put(player, playerMenuUtility);
            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(player);
        }

    }
    private boolean setupEconomy() {
        if(getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if(rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ !=null;
    }
    public static Economy getEconomy() {
        return econ;
    }



}
