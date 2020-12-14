/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.services;

import edu.ablazebookstore.models.Book;

import edu.ablazebookstore.test.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ConsultationLivreCRUD {
    Connection cnx;


     public ConsultationLivreCRUD(){ 
           cnx = MyConnection.getInstance().getCnx();
         
         
     }
     
     
      public List<Book> listBooks() {
        List<Book> myList =new ArrayList();
        try {
            String requete = "SELECT idBook,title,author,releaseDate,categorie FROM Book";
            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Book p = new Book();
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString("title"));
               p.setAuthor(rs.getString("author"));
                 p.setReleasedate(rs.getDate("releaseDate"));
                 p.setCategory(rs.getString("categorie"));
                 myList.add(p);
             }
     
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          return myList;
    }
     
      public List<Book> listBooksByTitle(String title) {
        List<Book> myList = new ArrayList<Book>();
        try {
            String requete = "SELECT * FROM Book where title like '"+title+"'";
                
            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Book p = new Book();
               
                p.setTitle(rs.getString(2));
               
               
                
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
      
      public List<Book> listBooksByauthor(String author) {
        List<Book> myList = new ArrayList<Book>();
        try {
            String requete = "SELECT * FROM Book where author like '"+author+"'";
                
            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Book p = new Book();
               
                p.setAuthor(rs.getString(3));
               
               
                
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
      public List<Book> listBooksByPrice(int price) {
        List<Book> myList = new ArrayList<Book>();
        try {
            String requete = "SELECT * FROM Book where prix='"+price+"'";
                
            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Book p = new Book();
               
                p.setPrice(rs.getInt(4));
               
               
                
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
      
      
     
      
      public List<Book> listBooksByCategory(String categorie) {
        List<Book> myList = new ArrayList<Book>();
        try {
            String requete = "select * from Book where categorie like '"+categorie+"'";
                
            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Book p = new Book();
               
                p.setCategory(rs.getString(7));
               
               
                
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
      
      
      
      
      

      
      
      
      
      
      
      
      




}
