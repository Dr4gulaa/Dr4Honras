package com.dr4gula.player;

import com.dr4gula.manager.HonraRank;

import java.util.UUID;

public class HonraPlayer {
    private UUID uuid;
    private int pontos;
    private HonraRank honraRank;

    public HonraPlayer(UUID uuid) {
        this.uuid = uuid;
        this.pontos = 0;
        this.honraRank = HonraRank.E;
    }

    public void adicionarPontos(int pontosAdicionados) {
        this.pontos += pontosAdicionados;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getPontos() {
        return pontos;
    }

    public void setRank(HonraRank honraRank) {
        this.honraRank = honraRank;
    }

    public HonraRank getRank() {
        return honraRank;
    }

    public UUID getUuid() {
        return uuid;
    }
}