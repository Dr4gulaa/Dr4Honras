package com.dr4gula.commands;

import com.dr4gula.manager.HonraManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HonrasAddCommand implements CommandExecutor {

    private final HonraManager honraManager;

    public HonrasAddCommand(HonraManager honraManager) {
        this.honraManager = honraManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("dr4honras.manager")) {
            sender.sendMessage(ChatColor.RED + "Você não tem permissão para usar esse comando.");
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Uso correto: /addhonra <jogador> <pontos>");
            return true;
        }

        Player player = Bukkit.getPlayerExact(args[0]);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Jogador não encontrado.");
            return true;
        }

        try {
            int pontos = Integer.parseInt(args[1]);
            honraManager.adicionarPontos(player, pontos);
            sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "Altverse" + ChatColor.WHITE + "] " + ChatColor.AQUA + pontos + " pontos de honra foram adicionados a " + player.getName() + ".");
            if (!sender.equals(player)) {
                player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "Altverse" + ChatColor.WHITE + "] " + ChatColor.AQUA + "Você recebeu " + (pontos * honraManager.getMultiplier()) + " pontos de honra.");
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Por favor, insira um número válido de pontos.");
            return true;
        }

        return true;
    }
}
