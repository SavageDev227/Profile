package com.savage.plugins.profile.commands;


import com.savage.plugins.profile.Profile;
import com.savage.plugins.profile.guis.ProfileGUI;
import com.savage.plugins.profile.guis.SinglePlayerGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import java.util.UUID;

public class ProfileCommand implements CommandExecutor {

    FileConfiguration config = Profile.getPlugin().getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                Player p = Bukkit.getPlayerExact(args[0]);
                UUID uuid = p.getUniqueId();
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
                Profile.getPlayerMenuUtility(p).setPlayerToMod(offlinePlayer);
                new SinglePlayerGUI(Profile.getPlayerMenuUtility(player)).openMenu();
            } else if (args.length < 1){
                new ProfileGUI(Profile.getPlayerMenuUtility(player)).openMenu();
            }

        }
        return true;
    }
}
