package com.savage.plugins.profile.commands;

import com.savage.plugins.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;


public class SaveConfigCommand implements CommandExecutor {

    FileConfiguration config = Profile.getPlugin().getConfig();
    Server server = Profile.getPlugin().getServer();
    Profile plugin = Profile.getPlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("saveconfig")) {
            plugin.getConfig().options().copyDefaults();
            plugin.saveDefaultConfig();
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Config saved and reloaded successfully!");
        }
        return true;
    }
}
