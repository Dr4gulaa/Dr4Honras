package com.dr4gula.player;

import com.dr4gula.manager.HonraManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class Placeholders extends PlaceholderExpansion {

    private final HonraManager honraManager;

    public Placeholders(HonraManager honraManager) {
        this.honraManager = honraManager;
    }

    @Override
    public String getIdentifier() {
        return "honras";
    }

    @Override
    public String getAuthor() {
        return "Dr4gula";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getPlugin() {
        return "Dr4Honras";
    }

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        if (player == null) {
            return "";
        }

        if (params.equalsIgnoreCase("pontos")) {
            return String.valueOf(honraManager.getPlayer(player.getUniqueId()).getPontos());
        }

        if (params.equalsIgnoreCase("rank")) {
            return honraManager.getPlayer(player.getUniqueId()).getRank().name();
        }

        return null;
    }
}
