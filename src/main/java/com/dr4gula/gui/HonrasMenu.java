package com.dr4gula.gui;

import com.dr4gula.manager.HonraManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.dr4gula.player.HonraPlayer;

import java.util.ArrayList;
import java.util.Arrays;

public class HonrasMenu implements InventoryHolder {

    private Inventory inv;
    private HonraManager honraManager;

    public HonrasMenu(HonraManager honraManager) {
        this.honraManager = honraManager;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    public void open(Player player) {
        inv = Bukkit.createInventory(this, 27, ChatColor.BLUE + "Menu de Honras");

        HonraPlayer honraPlayer = honraManager.getPlayer(player.getUniqueId());

        ItemStack infoItem = new ItemStack(Material.PAPER);
        ItemMeta infoMeta = infoItem.getItemMeta();
        infoMeta.setDisplayName(ChatColor.BOLD.GOLD + "Suas Informações");
        infoMeta.setLore(Arrays.asList(
                " ",
                ChatColor.BOLD.GOLD + "Pontos de Honra: " + ChatColor.AQUA + honraPlayer.getPontos(),
                ChatColor.BOLD.GOLD + "Rank: " + ChatColor.AQUA + honraPlayer.getRank().name())
        );
        infoItem.setItemMeta(infoMeta);
        inv.setItem(11, infoItem);


        ItemStack explainItem = new ItemStack(Material.BOOK);
        ItemMeta explainMeta = explainItem.getItemMeta();
        explainMeta.setDisplayName(ChatColor.BOLD.GOLD + "Como Funcionam as Honras?");
        explainMeta.setLore(Arrays.asList(
                " " ,
                ChatColor.WHITE + "Ganhe honras ao permanecer online, terminando Quests",
                ChatColor.WHITE + "e matando players. Com as honras você consegue upar",
                ChatColor.WHITE + "o seu rank de honra, para fazer quests especificas,",
                ChatColor.WHITE + "comprar espadas mais fortes, com isso ganhando mais",
                ChatColor.WHITE + "recompensas."
        ));
        explainItem.setItemMeta(explainMeta);
        inv.setItem(15, explainItem);


        ItemStack pointsItem = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta pointsMeta = pointsItem.getItemMeta();
        pointsMeta.setDisplayName(ChatColor.BOLD.GOLD + "Ranks");
        pointsMeta.setLore(Arrays.asList(

                " " ,
                ChatColor.GOLD + "Rank E " + ChatColor.AQUA + "0 pontos" ,
                ChatColor.GOLD + "Rank D   " + ChatColor.AQUA + "500 pontos" ,
                ChatColor.GOLD + "Rank C   " + ChatColor.AQUA + "1000 pontos" ,
                ChatColor.GOLD + "Rank B   " + ChatColor.AQUA + "1500 pontos" ,
                ChatColor.GOLD + "Rank A   " + ChatColor.AQUA + "2000 pontos" ,
                ChatColor.GOLD + "Rank S   " + ChatColor.AQUA + "3000 pontos" ,
                ChatColor.GOLD + "Rank SS  " + ChatColor.AQUA + "5000 pontos"
        ));
        pointsItem.setItemMeta(pointsMeta);
        inv.setItem(13, pointsItem);




        player.openInventory(inv);
    }
}
