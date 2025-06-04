package com.dr4gula.manager;

import com.dr4gula.data.DataHonras;
import com.dr4gula.player.HonraPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class HonraManager {

    private final JavaPlugin plugin;
    private DataHonras dataHonras;
    private final ConfigManager configManager;
    private final HashMap<UUID, HonraPlayer> jogadores;
    private int multiplier = 1;
    private long multiplierEndTime = 0;
    private long multiplierDuration = 0;


    public HonraManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.jogadores = new HashMap<>();
        this.configManager = new ConfigManager(plugin, "honra_multiplier.yml");
        this.dataHonras = new DataHonras(plugin, this);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }


    public void addPlayer(UUID playerId, HonraPlayer honraPlayer) {
        jogadores.put(playerId, honraPlayer);
    }

    public void adicionarPontos(Player player, int pontos) {
        UUID playerId = player.getUniqueId();
        HonraPlayer honraPlayer = jogadores.getOrDefault(playerId, new HonraPlayer(playerId));
        honraPlayer.adicionarPontos(pontos * multiplier);
        jogadores.put(playerId, honraPlayer);
        updateRank(player);
    }

    public void setarPontos(Player player, int pontos) {
        UUID playerId = player.getUniqueId();
        HonraPlayer honraPlayer = jogadores.getOrDefault(playerId, new HonraPlayer(playerId));
        honraPlayer.setPontos(pontos);
        jogadores.put(playerId, honraPlayer);
        updateRank(player);
    }

    public void delPontos(Player player, int pontos) {
        UUID playerId = player.getUniqueId();
        jogadores.remove(playerId, pontos);
    }

    public void updateRank(Player player) {
        UUID playerId = player.getUniqueId();
        HonraPlayer honraPlayer = jogadores.get(playerId);
        if (honraPlayer == null) {
            return;
        }
        HonraRank novoHonraRank = HonraRank.getRankPorPontos(honraPlayer.getPontos());
        if (novoHonraRank != honraPlayer.getRank()) {
            honraPlayer.setRank(novoHonraRank);
            String commandSetGroup = String.format("manuaddsub %s %s", player.getName(), novoHonraRank.getPermissao());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandSetGroup);
            player.sendMessage(String.format(ChatColor.WHITE + "[" + ChatColor.GOLD + "Altverse" + ChatColor.WHITE + "] " + ChatColor.GRAY + "Você foi promovido para o rank " + ChatColor.GOLD + "%s", novoHonraRank.name()));
        }
    }

    public void iniciarTarefaRecompensa() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            @Override
            public void run() {

                for (Player player : getServer().getOnlinePlayers()) {
                    adicionarPontos(player, 3);
                    player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "Altverse" + ChatColor.WHITE + "] " +
                            ChatColor.GRAY + "Você ganhou " + ChatColor.AQUA + (3 * getMultiplier()) + ChatColor.GRAY +
                            " pontos de honra, por ficar 10 minutos online!");

                    player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "Altverse" + ChatColor.WHITE + "] " +
                            ChatColor.GRAY + "Você ganhou " + ChatColor.YELLOW + "1" + ChatColor.GRAY +
                            " ponto da loja de Pontos Shinobi por ficar 10 minutos online!" + ChatColor.RED + " /lojinha" +
                            ChatColor.GRAY + " para resgatar.");

                    String comando = String.format("darpontosseason %s 1", player.getName());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comando);
                }
            }
        }, 0L, 12000L); //(12000 ticks = 10 minutos)
    }

    public void setMultiplier(int multiplier, long duration) {
        this.multiplier = multiplier;
        if (multiplier == 1) {
            multiplierEndTime = 0;
        } else {
            multiplierEndTime = System.currentTimeMillis() + duration;
        }
        saveState();
    }

    public HonraPlayer getPlayer(UUID playerId) {
        return jogadores.get(playerId);
    }

    public HashMap<UUID, HonraPlayer> getPlayers() {
        return jogadores;
    }


    public int getMultiplier() {
        return multiplier;
    }

    public void saveState() {
        configManager.getConfig().set("multiplier", multiplier);
        configManager.getConfig().set("multiplierEndTime", multiplierEndTime);
        configManager.saveConfig();
    }

    public void loadState() {
        configManager.reloadConfig();
        multiplier = configManager.getConfig().getInt("multiplier", 1);
        multiplierEndTime = configManager.getConfig().getLong("multiplierEndTime", 0);

        if (multiplierEndTime > System.currentTimeMillis()) {
            long remainingTime = multiplierEndTime - System.currentTimeMillis();
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                setMultiplier(1, 0);
                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "Altverse" + ChatColor.GRAY + "]" +
                        ChatColor.RED + " O evento de honras 2x terminou!");
            }, remainingTime / 50);
            multiplier = 2;
        } else {
            setMultiplier(1, 0);
        }
    }

    public void iniciarSalvamentoAutomatico() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            saveData();
            Bukkit.getLogger().info("[Dr4Honras] Dados de honras salvos automaticamente!");
        }, 6000L, 6000L);
    }

    public void saveData() {
        dataHonras.saveHonras();
    }

    public void loadData() {
        dataHonras.loadHonras();
    }
}
