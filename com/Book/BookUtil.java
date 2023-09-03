package com.Book;

import java.sql.SQLException;
import java.util.Scanner;

public class BookUtil {

    public static void main(String[] args) {

            BookStore store = new BookStore();
            Scanner sc = new Scanner(System.in);

            for(int i=0; i<1; i++){
                try {
                System.out.print("Enter Book ID : ");
                String bookID = sc.nextLine();

                System.out.print("Enter Book Title : ");
                String title = sc.nextLine();

                System.out.print("Enter Author name : ");
                String author = sc.nextLine();

                System.out.print("Enter category: \"Science\", \"Fiction\", \"Technology\" or \"Others\" : ");
                String category = sc.nextLine();

                System.out.print("Enter Price : ");
                Double price = sc.nextDouble();

                sc.nextLine();

                Book book = new Book(bookID, title, author, category, price);
                store.addBook(book);
                }
                catch (Exception ex){
                    System.out.println(ex);
                }
            }

        System.out.println();
            store.searchByAuthor("Verma");
        System.out.println();
            store.searchByTitle("Java AvaDe");
        System.out.println();

        System.out.println("Books Available are : ");
        store.displayAll();
    }

}
