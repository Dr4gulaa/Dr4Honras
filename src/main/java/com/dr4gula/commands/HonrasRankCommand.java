package com.dr4gula.commands;

import com.dr4gula.manager.HonraManager;
import com.dr4gula.player.HonraPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HonrasRankCommand implements CommandExecutor {
    private final HonraManager honraManager;

    public HonrasRankCommand(HonraManager honraManager) {
        this.honraManager = honraManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Somente jogadores podem executar esse comando.");
            return true;
        }

        Player player = (Player) sender;
        HonraPlayer honraPlayer = honraManager.getPlayer(player.getUniqueId());

        if (honraPlayer != null) {
            player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "Altverse" + ChatColor.WHITE + "] " + ChatColor.GRAY + "Você está no ranking " + ChatColor.GOLD + honraPlayer.getRank().name());
        } else {
            player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "Altverse" + ChatColor.WHITE + "] " + ChatColor.RED +"Você ainda não tem um rank de honras.");
        }

        return true;
    }
}