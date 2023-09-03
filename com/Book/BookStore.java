package com.Book;

import java.sql.*;

public class BookStore {
    String url = "jdbc:mysql://localhost:3306/Gainsight?useSSL=false";

    //To add a new Book object into the book table
    public void addBook(Book b) throws Exception{
        int count = 0;
        try(Connection con = DriverManager.getConnection(url,"root", "Mysql@98366");
            PreparedStatement ps = con.prepareStatement("insert into Book values(?,?,?,?,?)")
        ){
            ps.setString(1,b.getBookID());
            ps.setString(2,b.getTitle());
            ps.setString(3,b.getAuthor());
            ps.setString(4,b.getCategory());
            ps.setDouble(5,b.getPrice());

            count = ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Invalid Book Data Entered");
            throw new Exception("🔴💥 InvalidBookException 💥🔴");
        }
        if(count == 1) System.out.println("Book with ID:" + b.getBookID()+ " Added Successfully 👍");
        else System.out.println("Failed to add this Book 👎");
    }

    //Search a book from DB based on title and if found, display the details
    public void searchByTitle(String title){
        try(Connection con = DriverManager.getConnection(url,"root","Mysql@98366");
        PreparedStatement ps = con.prepareStatement("select * from Book where title=?")){
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("Book exists with the requested Title 👍 and its details are 👇");
                System.out.println("BookID : " + rs.getString(1) +
                        "\n" +"Title : " +rs.getString(2) +
                        "\n" +"Author : " + rs.getString(3) +
                        "\n" +"Category : " + rs.getString(4) +
                        "\n" +"Price : " +rs.getDouble(5));
            }
            else System.out.println("Book, " + title + ", doesn't exists 😕");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //Search a book from DB based on author and if found, display the details
    public void searchByAuthor(String author){
        try(Connection con = DriverManager.getConnection(url,"root","Mysql@98366");
            PreparedStatement ps = con.prepareStatement("select * from Book where author=?")){
            ps.setString(1, author);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("Book exists with the requested Author 👍 and its details are 👇");
                System.out.println("BookID : " + rs.getString(1) +
                        "\n" +"Title : " +rs.getString(2) +
                        "\n" +"Author : " + rs.getString(3) +
                        "\n" +"Category : " + rs.getString(4) +
                        "\n" +"Price : " +rs.getDouble(5));
            }
            else System.out.println(author + "'s books doesn't exists in here 😕");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //Print the details of all the books
    public void displayAll(){
        try(Connection con = DriverManager.getConnection(url,"root","Mysql@98366");
            PreparedStatement ps = con.prepareStatement("select * from Book")){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                System.out.println("BookID : " + rs.getString(1) +
                        "\n" +"Title : " +rs.getString(2) +
                        "\n" +"Author : " + rs.getString(3) +
                        "\n" +"Category : " + rs.getString(4) +
                        "\n" +"Price : " +rs.getDouble(5));
                System.out.println();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
