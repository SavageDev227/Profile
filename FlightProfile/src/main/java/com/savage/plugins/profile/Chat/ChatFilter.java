package com.savage.plugins.profile.Chat;

import com.savage.plugins.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;



public class ChatFilter implements Listener {

    FileConfiguration config = Profile.getPlugin().getConfig();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) throws InterruptedException {
        Player p = event.getPlayer();
        String message = event.getMessage();

        if (p.hasPermission("profile.bypassfilter")) {
            return;
        }

        // Check for excessive use of capital letters
        int uppercaseCount = 0;
        for (int i = 0; i < message.length(); i++) {
            if (Character.isUpperCase(message.charAt(i))) {
                uppercaseCount++;
            }
        }


        if (uppercaseCount >= config.getInt("max-caps-amount")) {
            event.getPlayer().sendMessage(ChatColor.RED + "You used too many capitals!");
            event.setMessage(message.toLowerCase());
        }


    }
}
