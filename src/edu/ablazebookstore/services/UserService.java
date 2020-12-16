/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.services;

import edu.ablazebookstore.models.Admin;
import edu.ablazebookstore.models.Client;
import edu.ablazebookstore.models.User;
import edu.ablazebookstore.test.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Nour
 */
public class UserService {
    public static User connectedUser;

    public static boolean isClient() {
        if (connectedUser != null) {
            return connectedUser.getuRole() == 'c';
        }

        return false;
    }

    private Connection cnx;

    public UserService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void addUser() {
        try {
            String requete = "INSERT INTO Users(fName,lName,email,uAddress,password,uRole,honoryPoints,TokenResetPassword) VALUES"
                    + " ('mark','stephen,'hj@gmail.com','london','azert','c',0,'')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("user added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int AddUser(User customer) {

        String query = "INSERT INTO Users (fName, lName, email, uAddress, password, uRole, honoryPoints,TokenResetPassword ) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ;";

        try (PreparedStatement statement = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, customer.getuFirstName());
            statement.setString(2, customer.getuLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getuAddress());
            statement.setString(5, customer.getuPassword());
            statement.setString(6, String.valueOf(customer.getuRole()));
            statement.setString(7, String.valueOf(customer.gethPoints()));
            statement.setString(8, customer.getTokenResetPassword());

            statement.executeUpdate();
            System.out.println("user added");

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error Creating: " + e.getMessage());
        }

        return -1;

    }

    public List<User> listerUsers() {
        List<User> myList = new ArrayList<User>();
        try {
            String requete = "SELECT * FROM  Users";

            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                User u1 = new User();
                u1.setUserID(rs.getInt(1));
                u1.setuFirstName(rs.getString("fName"));
                u1.setuLastName(rs.getString("lName"));
                u1.setEmail(rs.getString("email"));
                u1.setuAddress(rs.getString("uAddress"));
                u1.setuPassword(rs.getString("password"));
                u1.setuRole(rs.getString("uRole").charAt(0));
                u1.sethPoints(rs.getInt(8));
                u1.setTokenResetPassword(rs.getString("TokenResetPassword"));

                myList.add(u1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;

    }

    public User authenticateUser(String email, String password) {
        try {

            // TODO Auto-generated method stub
            if (email != null && !email.isEmpty()) {

                // Query to insert a record to the bank table
                String query = "SELECT * from Users where email = ? and password = ?;";
                // Use prepared statements to avoid SQL injection attacks
                PreparedStatement prepStmt = cnx.prepareStatement(query);
                prepStmt.setString(1, email);
                prepStmt.setString(2, password);

                // To get the primary key (id) of the newly inserted record
                ResultSet resultSet = prepStmt.executeQuery();

                if (resultSet.next()) {
                    if (resultSet.getString("uRole").equalsIgnoreCase("c")) {
                        User customer = new Client();
                        customer.setuFirstName(resultSet.getString("fName"));
                        customer.setuLastName(resultSet.getString("lName"));
                        customer.setuPassword(resultSet.getString("password"));
                        customer.setuRole(resultSet.getString("uRole").charAt(0));
                        customer.setUserID(resultSet.getInt("userID"));
                        customer.setuAddress(resultSet.getString("uAddress"));
                        customer.setEmail(resultSet.getString("email"));
                        customer.sethPoints(resultSet.getInt("honoryPoints"));
                        customer.setTokenResetPassword(resultSet.getString("TokenResetPassword"));
                        return customer;
                    } else {
                        User admin = new Admin();
                        admin.setuFirstName(resultSet.getString("fName"));
                        admin.setuLastName(resultSet.getString("lName"));
                        admin.setuPassword(resultSet.getString("password"));
                        admin.setuRole(resultSet.getString("uRole").charAt(0));
                        admin.setUserID(resultSet.getInt("userID"));
                        admin.setuAddress(resultSet.getString("uAddress"));
                        admin.setEmail(resultSet.getString("email"));
                        admin.sethPoints(resultSet.getInt("honoryPoints"));
                        admin.setTokenResetPassword(resultSet.getString("TokenResetPassword"));
                        return admin;
                    }

                }

            }

        } catch (Exception e) {

        }
        return null;
    }

    public boolean deleteUser(User u) {
        boolean etat = false;
        try {

            String requete = "DELETE FROM users WHERE userID=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, u.getUserID());
            pst.executeUpdate();
            System.out.println("User deleted");
            etat = true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            etat = false;
        }
        return etat;
    }

    public void updateUser(String fname, String lname, String email, String adrs, String pwd) throws SQLException {

        String sql = "UPDATE Users SET fName='" + fname + "', lName='" + lname + "', email='" + email + "', uAddress='" + adrs + "', password='" + pwd + "' WHERE userID=" + connectedUser.getUserID() + "";

        PreparedStatement statement = this.cnx.prepareStatement(sql);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println(" user was updated successfully!");
        }

    }
    public boolean checkMail(String email) throws SQLException{
             String requete = "SELECT * FROM  Users where email='" + email + "'";
        PreparedStatement prepStmt = cnx.prepareStatement(requete);
        ResultSet resultSet = prepStmt.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString("email").equalsIgnoreCase(email)) {
                return false;
            }
        return true;
    }
        return true;
}}
