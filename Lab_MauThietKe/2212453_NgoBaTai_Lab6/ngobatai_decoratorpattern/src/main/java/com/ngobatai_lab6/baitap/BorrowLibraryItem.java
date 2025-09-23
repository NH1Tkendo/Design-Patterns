package com.ngobatai_lab6.baitap;

public class BorrowLibraryItem extends LibraryDecorator{
    private List<String> borrowers = new ArrayList<>();

    public BorrowLibraryItem(LibraryItem libraryItem){
        super(libraryItem);
    }

    public void borrow(String name){
        if(borrowers.size() < libraryItem.getCopy()){
            if(!borrowers.contains(name)){
                borrowers.add(name);
                System.out.println(name + " has borrowed the item");
            }
            else{
                System.out.println(name + "has alredy borrowed this item");
            }
        } else {
            System.out.println("No more copies avaliable for borrowing");
        }
    }

    public void recall(string name){
        if(borrower.remove(name)){
            System.out.println(name + " has returned the item");
        } else {
            System.out.println(name + " does not have this item borrowed");
        }
    }

    @Override
    public void display(){
        libraryItem.display();
        System.out.println("Current borrowers" + borrowers);
        System.out.println("Available copies" + (libraryItem.getCopy() - borrowers.size()));
    }
}
