/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.models;


/**
 *
 * @author Nour
 */
public class Admin extends User {

    public Admin() {
    }

    public Admin(int userID, String uFirstName, String uLastName, String email, String uAddress, String uPassword, Character uRole, int hPoints, String TokenResetPassword) {
        super(userID, uFirstName, uLastName, email, uAddress, uPassword, uRole, hPoints, TokenResetPassword);
    }



    @Override
    public String toString() {
        return "Admin{" +super.toString()+ '}';
    }

}
