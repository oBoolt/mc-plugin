package io.github.oboolt.plugin.commands;

import io.github.oboolt.plugin.Main;
import io.github.oboolt.plugin.utils.MyInventory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;


public class VaultCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)){
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>Only allowed for players"));
            return true;
        }

        Inventory vaultInventory = new MyInventory(Main.getPlugin(), player, 9*3, Component.text("Vault")).getInventory();

        NamespacedKey key = new NamespacedKey(Main.getPlugin(), "vault");
        PersistentDataContainer container = player.getPersistentDataContainer();

        if (container.has(key, PersistentDataType.BYTE_ARRAY)) {
            byte[] itemsByteArray = container.get(key, PersistentDataType.BYTE_ARRAY);
            Map<Integer,ItemStack> items = new HashMap<>();
            // Parse byte array to map
            ByteArrayInputStream byteIn = new ByteArrayInputStream(itemsByteArray); // the variable 'itemsByteArray' cannot be 'null' because it is already checked in the 'if statement' - line 39
            try {
                ObjectInputStream in = new ObjectInputStream(byteIn);
                Map<Integer, byte[]> mapSerializedItems = (Map<Integer,byte[]>) in.readObject();
                for (Integer i : mapSerializedItems.keySet()) {
                    items.put(i, ItemStack.deserializeBytes(mapSerializedItems.get(i)));
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            for(Integer i : items.keySet()) {
                vaultInventory.setItem(i, items.get(i));
            }
        }

        player.openInventory(vaultInventory);

        return true;
    }
}
