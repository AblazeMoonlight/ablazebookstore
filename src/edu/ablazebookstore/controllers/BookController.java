/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.controllers;

import edu.ablazebookstore.models.Book;
import java.sql.Connection;
import edu.ablazemoonlight.test.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JARR
 */
public class BookController {
    Connection cnx;

    public BookController() {
                cnx = MyConnection.getInstance().getCnx();

    }
     public void addBook() {
        try {
            String requete = "INSERT INTO Book(title,author) VALUES"
                    + " ('the shining','stephen king')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Book added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public void addBook(Book b) {
        try {
//            String requete = "INSERT INTO personne(nom,prenom) "
//                    + "VALUES ('"+p.getNom()+"','"+p.getPrenom()+"')";

            String requete = "INSERT INTO Book(title,author) "
                    + "VALUES (?,?)";

            PreparedStatement pst = cnx
                    .prepareStatement(requete);
            pst.setString(1, b.getTitle());
            pst.setString(2, b.getAuthor());
            pst.executeUpdate();
            System.out.println("Book added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean deleteBook(int id) {
        boolean etat = false;
        try {
            String requete = "DELETE FROM Book WHERE idBook=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Book deleted");
            etat = true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            etat = false;
        }
        return etat;
    }
    
    public List<Book> listBooks() {
        List<Book> myList = new ArrayList<Book>();
        try {
            String requete = "SELECT * FROM Book";
            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Book p = new Book();
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString("title"));
                p.setAuthor(rs.getString("author"));
              
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
}
