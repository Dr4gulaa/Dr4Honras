package com.dr4gula.events;

import com.dr4gula.manager.HonraManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private final HonraManager honraManager;

    public PlayerDeathListener(HonraManager honraManager) {
        this.honraManager = honraManager;
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            Player killer = event.getEntity().getKiller();
            honraManager.adicionarPontos(killer, 2);
        killer.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "Altverse" + ChatColor.WHITE + "] " + ChatColor.GRAY + "Você recebeu " + ChatColor.LIGHT_PURPLE + "2 " + ChatColor.GRAY + "de honra, por matar outro ninja.");
        }
    }
}
