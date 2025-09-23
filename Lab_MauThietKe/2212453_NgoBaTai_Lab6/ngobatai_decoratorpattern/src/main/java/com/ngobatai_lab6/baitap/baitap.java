package com.ngobatai_lab6.baitap;

public class baitap {
    public static void main(String[] args){
        System.out.println("--- Library Management (Decorator Pattern)");

        LibraryItem DAA = new Book("Data structures and algorithms", "Nguyen Van A", 3);
        BorrowLibraryItem borrowBook = new BorrowLibraryItem(DAA);

        borrowBook.display();

        //Muon sach
        System.out.println("\n borrowing ");
        borrowBook.borrow("A");
        borrowBook.borrow("B");
        borrowBook.borrow("C");
        borrowBook.borrow("D");
        borrowBook.borrow("E");

        System.out.println("\n After borrowing");
        borrowBook.display();

        System.out.println("\n Recall action");
        borrowBook.recall("B");
        borrowBook.recall("C");

        System.out.println("\n After recall");
        borrowBook.display();
    }
}
