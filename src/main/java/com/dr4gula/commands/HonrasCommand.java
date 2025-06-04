package com.dr4gula.commands;

import com.dr4gula.gui.HonrasMenu;
import com.dr4gula.manager.HonraManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HonrasCommand implements CommandExecutor {
    private HonraManager honraManager;
    private HonrasMenu honrasMenu;

    public HonrasCommand(HonraManager honraManager) {
        this.honraManager = honraManager;
        this.honrasMenu = new HonrasMenu(honraManager);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando só pode ser usado por jogadores.");
            return true;
        }

        Player player = (Player) sender;
        honrasMenu.open(player);
        return true;
    }
}