package com.dr4gula.manager;

public enum HonraRank {
    E(0, "rankE"),
    D(500, "rankD"),
    C(1000, "rankC"),
    B(1500, "rankB"),
    A(2000, "rankA"),
    S(3000, "rankS"),
    SS(5000, "rankSS");

    private final int pontosMinimos;
    private final String permissao;

    HonraRank(int pontosMinimos, String permissao) {
        this.pontosMinimos = pontosMinimos;
        this.permissao = permissao;
    }

    public int getPontosMinimos() {
        return pontosMinimos;
    }

    public String getPermissao() {
        return permissao;
    }

    public static HonraRank getRankPorPontos(int pontos) {
        HonraRank honraRankAtual = HonraRank.E;
        for (HonraRank honraRank : HonraRank.values()) {
            if (pontos >= honraRank.getPontosMinimos()) {
                honraRankAtual = honraRank;
            } else {
                break;
            }
        }
        return honraRankAtual;
    }
}