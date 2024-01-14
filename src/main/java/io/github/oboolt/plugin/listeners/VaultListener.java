package io.github.oboolt.plugin.listeners;

import io.github.oboolt.plugin.Main;
import io.github.oboolt.plugin.utils.MyInventory;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class VaultListener implements Listener {

    @EventHandler
    public void onPlayerCloseVault(InventoryCloseEvent e) {
        if (!(e.getInventory().getHolder() instanceof MyInventory vaultInventory)) {
            return;
        }

        Player player = (Player) vaultInventory.getOwner();
        Inventory inventory = vaultInventory.getInventory();

        Map<Integer, byte[]> items = new HashMap<>();
        for(int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if(item != null) {
                items.put(i, item.serializeAsBytes());
            }
        }

        // Convert map to byte array
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(items);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        NamespacedKey key = new NamespacedKey(Main.getPlugin(), "vault");
        PersistentDataContainer container = player.getPersistentDataContainer();
        container.set(key, PersistentDataType.BYTE_ARRAY, byteOut.toByteArray());

    }
}
