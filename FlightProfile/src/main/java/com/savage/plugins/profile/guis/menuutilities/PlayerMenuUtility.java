package com.savage.plugins.profile.guis.menuutilities;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerMenuUtility {
    private Player owner;
    public OfflinePlayer playerToMod;

    public PlayerMenuUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }

    public OfflinePlayer getPlayerToMod() {
        return playerToMod;
    }

    public void setPlayerToMod(OfflinePlayer playerToMod) {
        this.playerToMod = playerToMod;
    }
}
