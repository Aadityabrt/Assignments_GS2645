package org.example;

import java.sql.*;

public class OrderedProductsDAO {
    String url = "jdbc:mysql://localhost:3306/Gainsight";

    public void displayOrderedProductById(int productId){

        try(Connection con = DriverManager.getConnection(url, "root", "Mysql@98366");
            PreparedStatement ps = con.prepareStatement
                    ("select * from product inner join ordered_products on product.prod_id=ordered_products.prod_id where product.prod_id = ?")){
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println(rs.getInt(1) + " " +
                        rs.getString(2) + " " +rs.getString(3) + " " +
                        rs.getDouble(4) + " " + rs.getInt(5));

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void displayOrderedProductsByOrderDate(String orderDate){
        try(Connection con = DriverManager.getConnection(url, "root", "Mysql@98366");
            PreparedStatement ps = con.prepareStatement
                    ("select product.prod_id, prod_name,prod_desc,orders.order_date from Product product, ordered_products ordered_products, orders orders where product.prod_id = ordered_products.prod_id and ordered_products.order_id = orders.order_id and orders.order_date = ?")){
            ps.setString(1,orderDate);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("name : "+rs.getString(2)+"  product_name : " + rs.getString(2) +" date : "+ rs.getDate(4));
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
