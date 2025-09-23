package com.ngobatai_lab6.baitap;

public class Book extends LibraryItem{
    private String title;
    private String author;

    public Book (String title,String author,int copy){
        super(copy);
        this.title = title;
        this.author = author;
    }

    @Override
    public void display(){
        System.out.println("Book: " + title + " by" + author + ", copies: " + getCopy());
    }
}


