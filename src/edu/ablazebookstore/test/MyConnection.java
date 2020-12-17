package edu.ablazebookstore.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class MyConnection {
    
    public String url="jdbc:mysql://sql7.freesqldatabase.com:3306/sql7381684";
    public String login="sql7381684";
    public String pwd="TUvsrQBiUT";
    Connection cnx;
    public static MyConnection instance;

    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    public Connection getCnx(){
        return cnx;
    }
    public static MyConnection getInstance(){
        
            if(instance== null){
                instance = new MyConnection();
            }
        return instance ;
    }
    
    
}