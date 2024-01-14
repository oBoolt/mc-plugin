package io.github.oboolt.plugin.listeners;

import io.github.oboolt.plugin.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String joinMessage = Main.getPlugin().getConfig().getString("messages.join-message");
        if (joinMessage == null){
            return;
        }

        e.joinMessage(MiniMessage.miniMessage().deserialize(joinMessage.replace("%player%", e.getPlayer().getName())));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        String quitMessage = Main.getPlugin().getConfig().getString("messages.leave-message");
        if (quitMessage == null){
            return;
        }

        e.quitMessage(MiniMessage.miniMessage().deserialize(quitMessage.replace("%player%", e.getPlayer().getName())));
    }


}
