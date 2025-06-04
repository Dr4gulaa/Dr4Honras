package com.dr4gula.commands;

import com.dr4gula.manager.HonraManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.concurrent.TimeUnit;

public class Honras2xCommand implements CommandExecutor {
    private final HonraManager honraManager;

    public Honras2xCommand(HonraManager honraManager) {
        this.honraManager = honraManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("dr4honras.manager")) {
            sender.sendMessage(ChatColor.RED + "Você não tem permissão para usar esse comando.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Uso: /honras2x <tempo>");
            return true;
        }

        try {
            String tempo = args[0];
            long duration = parseTime(tempo);
            if (duration <= 0) {
                sender.sendMessage(ChatColor.RED + "Por favor, insira um tempo válido.");
                return true;
            }

            honraManager.setMultiplier(2, duration);
            Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "Altverse" + ChatColor.GRAY + "]" +
                    ChatColor.GREEN + " Multiplicador de honras 2x ativado por " + tempo + ".");

            Bukkit.getScheduler().runTaskLater(honraManager.getPlugin(), () -> {
                honraManager.setMultiplier(1, 0);
                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "Altverse" + ChatColor.GRAY + "]" +
                        ChatColor.RED + " O evento de honras 2x terminou!");
            }, duration);

        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Erro ao processar o comando.");
            e.printStackTrace();
        }

        return true;
    }

    private long parseTime(String tempo) {
        try {
            if (tempo.endsWith("h")) {
                int hours = Integer.parseInt(tempo.replace("h", ""));
                return TimeUnit.HOURS.toSeconds(hours) * 20;
            } else if (tempo.endsWith("m")) {
                int minutes = Integer.parseInt(tempo.replace("m", ""));
                return TimeUnit.MINUTES.toSeconds(minutes) * 20;
            } else if (tempo.endsWith("s")) {
                int seconds = Integer.parseInt(tempo.replace("s", ""));
                return seconds * 20;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        return -1;
    }
}
