package com.ngobatai_lab6.baitap;

public abstract class LibraryDecorator {
    protected LibraryItem libraryItem;

    public LibraryDecorator(LibraryItem libraryItem){
        super(0);
        this.libraryItem = libraryItem;
    }

    @Override
    public int getCopy(){
        return this.libraryItem.getCopy();
    }

    @Override
    public abstract void display();
}
