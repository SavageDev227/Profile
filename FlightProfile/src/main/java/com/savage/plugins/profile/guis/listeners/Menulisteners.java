package com.savage.plugins.profile.guis.listeners;

import com.savage.plugins.profile.guis.menuutilities.MenuSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;



public class Menulisteners implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {

        InventoryHolder holder = event.getInventory().getHolder();
        //If the inventoryholder of the inventory clicked on
        // is an instance of Menu, then gg. The reason that
        // an InventoryHolder can be a Menu is because our Menu
        // class implements InventoryHolder!!
        if (holder instanceof MenuSystem) {
            event.setCancelled(true); //prevent them from altering with the inventory
            if (event.getCurrentItem() == null) { //deal with null exceptions
                return;
            }
            //Since we know our inventoryholder is a menu, get the Menu Object representing
            // the menu we clicked on
            MenuSystem menu = (MenuSystem) holder;
            //Call the handleMenu object which takes the event and processes it
            menu.handleMenu(event);
        }
    }


}
