package io.github.oboolt.plugin.utils;

import io.github.oboolt.plugin.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class MyInventory implements InventoryHolder {
    private final Inventory inventory;
    private HumanEntity owner;

    public MyInventory(@NotNull Main plugin, @NotNull HumanEntity owner, int size) {
        this.owner = owner;
        this.inventory = plugin.getServer().createInventory(this, size);
    }

    public MyInventory(@NotNull Main plugin, @NotNull HumanEntity owner, int size, @NotNull Component title) {
        this.owner = owner;
        this.inventory = plugin.getServer().createInventory(this, size, title);
    }

    public MyInventory(@NotNull Main plugin, @NotNull HumanEntity owner, @NotNull InventoryType type) {
        this.owner = owner;
        this.inventory = plugin.getServer().createInventory(this, type);
    }

    public MyInventory(@NotNull Main plugin, @NotNull HumanEntity owner, @NotNull InventoryType type, @NotNull Component title) {
        this.owner = owner;
        this.inventory = plugin.getServer().createInventory(this, type, title);
    }


    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    public @NotNull HumanEntity getOwner() {
        return this.owner;
    }
}
