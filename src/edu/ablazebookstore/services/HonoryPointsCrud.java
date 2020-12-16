/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.services;

import edu.ablazebookstore.models.Book;
import edu.ablazebookstore.models.User;
import edu.ablazebookstore.test.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS 550
 */
public class HonoryPointsCrud {
     Connection cnx; 
    
     
     
     public HonoryPointsCrud() {
          cnx = MyConnection.getInstance().getCnx();
     }
    
    
    
    
    
    
    public  List<Integer>  getUserHonoryPoints(int userid) {
        
     List<Integer> honor = new ArrayList<Integer>();
      
        try {
            String requete = "SELECT honoryPoints from `Users` where userID='"+userid+"'";
            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                User c = new User();
              c.sethPoints(rs.getInt("honoryPoints"));
//              
              honor.add(c.gethPoints());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return honor;
      }

 public boolean updateHonory(User p) {
        try {
            String requete = "Update    Users  SET honorypoints=5-honorypoints where usedID=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
           pst.setInt(1,p.getUserID());
            pst.setInt(2, p.gethPoints());
          
           
            
          

            pst.executeUpdate();
            System.out.println("Honory points updated");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }catch(NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
        return true;

    }










}
