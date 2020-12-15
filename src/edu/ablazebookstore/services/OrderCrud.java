package edu.ablazebookstore.services;


import edu.ablazebookstore.models.Order;
import edu.ablazebookstore.test.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author userJT
 */
public class OrderCrud {
    
    Connection cnx;
    PreparedStatement pr;
    
    public OrderCrud(){
        cnx= MyConnection.getInstance().getCnx();
    }
    
    public void addOrder(){
        try{
            String requet="INSERT INTO Orders VALUES (1,'chaine',CONVERT(\"2017-08-29\", DATE),7,8)";
            //pr=cnx.prepareStatement(requet);
            Statement st = cnx.createStatement();
            st.executeUpdate(requet);
            //pr.executeUpdate();
            System.out.println("Order added");
        }
        catch(Exception e){
            System.out.println(e.getMessage());   
        }
    }
    
    public void addOrder(Order o){
        try{
            String requet="INSERT INTO Orders(bookid,quantitie,dateAchat)"+" VALUES (?,?,NOW())";
            pr=cnx.prepareStatement(requet);
            
            pr.setInt(1,o.getIdBook());
            pr.setInt(2,o.getQuantitie());
            
            pr.executeUpdate();
            System.out.println("Order added");
        }
        catch(Exception e){
            System.out.println(e.getMessage());   
        }
    }
    
    public void modifyOrder(Order o){
        try{
            String requet="UPDATE `Orders` SET `dateAchat`=NOW(),`quantitie`=?,idClient=?,idBook=? where idOrder=3";
            pr=cnx.prepareStatement(requet);
            
            //pr.setInt(1,o.getIdOrder());
            pr.setInt(1,o.getQuantitie());
            //pr.setString(1, "CONVERT(\"2017-08-30\",DATE)");
            pr.setInt(2,o.getIdClient());
            pr.setInt(3,o.getIdBook());
            
            pr.executeUpdate();
            System.out.println("Order edited");
        }
        catch(Exception e){
            System.out.println("problem editing order");
        }
    }
    public List<Order> listeOrder(int userID){
        List<Order> myOList= new ArrayList<Order>();
        try{
            String requet="SELECT * FROM `Orders` WHERE userID="+userID;
            Statement st = cnx.createStatement();
            Statement st2=cnx.createStatement();
            
            ResultSet rs = st.executeQuery(requet);
            ResultSet rs2;
            while(rs.next()){
                Order o=new Order();
                o.setIdBook(rs.getInt(3));
                o.setIdClient(rs.getInt(2));
                o.setIdOrder(rs.getInt(1));
                o.setDateAchat(rs.getDate("dateAchat"));
                o.setQuantitie(rs.getInt("quantitie"));
                
                String requet2="SELECT * FROM `Book` WHERE bookid="+rs.getInt("bookid");
                rs2= st2.executeQuery(requet2);
                while(rs2.next()){
                  o.setTitle(rs2.getString("title"));
                  o.setPrice(rs2.getInt("price"));
                }
                

                
                myOList.add(o);
            }
            System.out.println("Showing Orders...");
        }
        catch(Exception e){
            System.out.println("Showing error");
            e.printStackTrace();
        }
        return myOList;
    }
    
    public List<Order> listeOrders(){
        List<Order> myOList= new ArrayList<Order>();
        try{
            String requet="SELECT * FROM `Orders`";
            Statement st = cnx.createStatement();
            Statement st2=cnx.createStatement();
            
            ResultSet rs = st.executeQuery(requet);
            ResultSet rs2;
            while(rs.next()){
                Order o=new Order();
                o.setIdBook(rs.getInt(3));
                o.setIdClient(rs.getInt(2));
                o.setIdOrder(rs.getInt(1));
                o.setDateAchat(rs.getDate("dateAchat"));
                o.setQuantitie(rs.getInt("quantitie"));
                
                String requet2="SELECT * FROM `Book` WHERE bookid="+rs.getInt("bookid");
                rs2= st2.executeQuery(requet2);
                while(rs2.next()){
                  o.setTitle(rs2.getString("title"));
                  o.setPrice(rs2.getInt("price"));
                }
                

                
                myOList.add(o);
            }
            System.out.println("Showing Orders...");
        }
        catch(Exception e){
            System.out.println("Showing error");
            e.printStackTrace();
        }
        return myOList;
    }
    
    public Boolean deleteOrder(int idOrder){
        try{
            String requet="delete from Orders where idOrder=?";
            pr=cnx.prepareStatement(requet);
            
            pr.setInt(1, idOrder);
            
            pr.executeUpdate();
            System.out.println("Order deleted :) ");
            return true;
        }
        catch(Exception e){
            System.out.println("problem deleting order");
            return false;
        }
    }
    
}
