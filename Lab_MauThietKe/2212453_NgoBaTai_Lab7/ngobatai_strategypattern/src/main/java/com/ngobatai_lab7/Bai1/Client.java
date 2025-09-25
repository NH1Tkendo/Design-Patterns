package com.ngobatai_lab7.Bai1;

public class Client {
    public static void main(String[] args) {
        System.out.println("Hay chon do kho");

        Game_Context game = new Game_Context();
        game.setDifficult(new DoKhoDe());
        game.Difficult();
        game.setDifficult(new DoKhoTrungBinh());
        game.Difficult();
        game.setDifficult(new DoKhoKho());
        game.Difficult();
    }
}
