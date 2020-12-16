/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.services;

import edu.ablazebookstore.models.Book;
import edu.ablazebookstore.models.Commentaire;
import edu.ablazebookstore.test.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS 550
 */
public class CommentaireCrud {
    Connection cnx;
    
    
    public CommentaireCrud()  {
        cnx = MyConnection.getInstance().getCnx();
    }
     
    
     public void addCommentaire(Commentaire b) {
        try {

            String requete = "INSERT INTO Comments (iduser,commentaire) VALUES (?,?) "  ;
                  
                   

            PreparedStatement pst = cnx
                    .prepareStatement(requete);
            pst.setInt(1, b.getUserid());
            pst.setString(2, b.getCommentaire());
            

            pst.executeUpdate();
            System.out.println("Commentaire Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }
     public ObservableList<Commentaire> listComments() {
        ObservableList<Commentaire> myList = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM Comments";
            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Commentaire p = new Commentaire();
               p.setUserid(rs.getInt("iduser"));
               p.setCommentaire(rs.getString("commentaire"));
               myList.add(p);
             }
     
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          return myList;
            
         }
     
     
     
     
     
     
     
     
     
     
     
}