/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.models;

import edu.ablazebookstore.models.User;

/**
 *
 * @author Nour
 */
public class Client extends User {

    public Client() {
    }

    public Client(int userID, String uFirstName, String uLastName, String email, String uAddress, String uPassword, Character uRole, int hPoints, String TokenResetPassword) {
        super(userID, uFirstName, uLastName, email, uAddress, uPassword, uRole, hPoints, TokenResetPassword);
    }

  

    @Override
    public String toString() {
        return "Client{"+ super.toString() + '}';
    }

 
    
 
	
}