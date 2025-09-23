package com.ngobatai_lab6.baitap;

public class Movie {
    private String title;
    private String director;
    private int time;

    public Movie(String title, String director, int time, int copy){
        super(copy);
        this.title = title;
        this.director = director;
        this.time = time;
    }

    @Override
    public void display(){
        System.out.println("Movie: " + title + " by" + director + ", copies: " + getCopy());
    }
}
