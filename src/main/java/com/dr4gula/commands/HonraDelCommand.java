package com.dr4gula.commands;

import com.dr4gula.manager.HonraManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HonraDelCommand implements CommandExecutor {


    private final HonraManager honraManager;

    public HonraDelCommand(HonraManager honraManager) {
        this.honraManager = honraManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = Bukkit.getPlayerExact(args[0]);

        if(player.hasPermission("dr4honras.manager")) {

            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "Uso: /delhonra <jogador> <pontos>");
                return true;
            }
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "Jogador não encontrado.");
                return true;
            }
            try {
                int pontos = Integer.parseInt(args[1]);
                honraManager.delPontos(player, pontos);
                sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "Altverse" + ChatColor.WHITE + "] " + ChatColor.GRAY +"Pontos de honra"+ ChatColor.RED + "deletados.");
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Por favor, insira um número válido de pontos.");
            }

        }else{
            sender.sendMessage(ChatColor.RED + "Você não tem permissão para usar esse comando");
        }
        return true;
    }
}
