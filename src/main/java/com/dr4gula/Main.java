package com.dr4gula;

import com.dr4gula.commands.*;
import com.dr4gula.events.MenuListener;
import com.dr4gula.events.PlayerDeathListener;
import com.dr4gula.manager.HonraManager;
import com.dr4gula.player.Placeholders;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private HonraManager honraManager;


    @Override
    public void onEnable() {

        honraManager = new HonraManager(this);
        honraManager.loadData();
        honraManager.iniciarTarefaRecompensa();
        honraManager.iniciarSalvamentoAutomatico();


        //Events
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(honraManager), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        //Commands
        this.getCommand("sethonra").setExecutor(new HonraSetCommand(honraManager));
        this.getCommand("addhonra").setExecutor(new HonrasAddCommand(honraManager));
        this.getCommand("delhonra").setExecutor(new HonraDelCommand(honraManager));
        this.getCommand("honrasrank").setExecutor(new HonrasRankCommand(honraManager));
        this.getCommand("honras").setExecutor(new HonrasCommand(honraManager));
        this.getCommand("honras2x").setExecutor(new Honras2xCommand(honraManager));

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new Placeholders(honraManager).register();
            getLogger().info("PlaceholderAPI detectada! Expansão de honras registrada.");
        } else {
            getLogger().warning("PlaceholderAPI não detectada! As placeholders não funcionarão.");
        }


        getLogger().info("[Dr4Honras] Plugin ativado!");
    }

    @Override
    public void onDisable() {
        honraManager.saveData();
        getLogger().info("[Dr4Honras] Plugin desativado e dados salvos!");;
    }
}
