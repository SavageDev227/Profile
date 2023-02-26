package com.savage.plugins.profile.guis;

import com.savage.plugins.profile.guis.menuutilities.MenuSystem;
import com.savage.plugins.profile.guis.menuutilities.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;


public abstract class PagedMenu extends MenuSystem {
    protected int page = 0;
    protected int maxItemsPerPage = 54;
    protected int index = 0;

    public PagedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    public void addMenuControls(){
        inv.setItem(48, createItem(Material.STONE_BUTTON, ChatColor.GREEN + "Previous Page"));
        inv.setItem(49, createItem(Material.BARRIER, ChatColor.DARK_RED + "Close"));
        inv.setItem(50, createItem(Material.STONE_BUTTON, ChatColor.GREEN + "Next Page"));
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }

}
