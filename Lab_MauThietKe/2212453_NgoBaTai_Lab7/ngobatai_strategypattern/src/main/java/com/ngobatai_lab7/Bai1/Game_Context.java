package com.ngobatai_lab7.Bai1;

public class Game_Context {
    public Game_Interface game;

    public void setDifficult(Game_Interface game) {
        this.game = game;
    }

    public void Difficult() {
        game.ChonDoKho();
    }
}
