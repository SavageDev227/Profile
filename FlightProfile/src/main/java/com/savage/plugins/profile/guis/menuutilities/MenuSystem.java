package com.savage.plugins.profile.guis.menuutilities;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;


public abstract class MenuSystem implements InventoryHolder {
    protected PlayerMenuUtility menuUtility;
    protected Inventory inv;

    public MenuSystem(PlayerMenuUtility menuUtility) {
        this.menuUtility = menuUtility;
    }

    public abstract String getMenuName();
    public abstract int getSlots();
    public abstract void handleMenu(InventoryClickEvent e);
    public abstract void setMenuItems();

    public void openMenu() {
        inv = Bukkit.createInventory(this,getSlots(),getMenuName());
        this.setMenuItems();
        menuUtility.getOwner().openInventory(inv);
    }
    @Override
    public Inventory getInventory() {
        return inv;
    }

    public ItemStack createItem(Material mat, String itemName, String... itemLore) {
        ItemStack item = new ItemStack(mat);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(itemName);
        itemMeta.setLore(Arrays.asList(itemLore));
        item.setItemMeta(itemMeta);
        return item;
    }
}
