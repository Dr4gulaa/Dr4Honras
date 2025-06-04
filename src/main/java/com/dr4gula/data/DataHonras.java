package com.dr4gula.data;

import com.dr4gula.manager.HonraManager;
import com.dr4gula.manager.HonraRank;
import com.dr4gula.player.HonraPlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Map;
import java.util.UUID;

public class DataHonras {

    private final JavaPlugin plugin;
    private final HonraManager honraManager;

    public DataHonras(JavaPlugin plugin, HonraManager honraManager) {
        this.plugin = plugin;
        this.honraManager = honraManager;
    }

    public void saveHonras() {
        for (Map.Entry<UUID, HonraPlayer> entry : honraManager.getPlayers().entrySet()) {
            plugin.getConfig().set("honras." + entry.getKey() + ".pontos", entry.getValue().getPontos());
            plugin.getConfig().set("honras." + entry.getKey() + ".rank", entry.getValue().getRank().name());
        }
        plugin.saveConfig();
    }

    public void loadHonras() {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("honras");
        if (section != null) {
            for (String key : section.getKeys(false)) {
                UUID playerId = UUID.fromString(key);
                ConfigurationSection honraSection = section.getConfigurationSection(key);
                if (honraSection != null) {
                    int pontos = honraSection.getInt("pontos");
                    String rankName = honraSection.getString("rank");
                    HonraPlayer honraPlayer = new HonraPlayer(playerId);
                    honraPlayer.setPontos(pontos);
                    if (rankName != null) {
                        try {
                            honraPlayer.setRank(HonraRank.valueOf(rankName));
                        } catch (IllegalArgumentException e) {
                            honraPlayer.setRank(HonraRank.E);
                        }
                    }
                    honraManager.addPlayer(playerId, honraPlayer);
                }
            }
        }
    }
}
