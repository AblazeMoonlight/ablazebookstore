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
public class User {

    private int userID;
    private String uFirstName;
    private String uLastName;
    private String email;
    private String uAddress;
    private String uPassword;
    private Character uRole;
    private int hPoints;
    private String TokenResetPassword;

    public User(int userID, String uFirstName, String uLastName, String email, String uAddress, String uPassword, Character uRole, int hPoints, String TokenResetPassword) {
        this.userID = userID;
        this.uFirstName = uFirstName;
        this.uLastName = uLastName;
        this.email = email;
        this.uAddress = uAddress;
        this.uPassword = uPassword;
        this.uRole = uRole;
        this.hPoints = hPoints;
        this.TokenResetPassword = TokenResetPassword;
    }

    public User() {
    }

    public String getTokenResetPassword() {
        return TokenResetPassword;
    }

    public void setTokenResetPassword(String TokenResetPassword) {
        this.TokenResetPassword = TokenResetPassword;
    }

 
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getuFirstName() {
        return uFirstName;
    }

    public void setuFirstName(String uFirstName) {
        this.uFirstName = uFirstName;
    }

    public String getuLastName() {
        return uLastName;
    }

    public void setuLastName(String uLastName) {
        this.uLastName = uLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public Character getuRole() {
        return uRole;
    }

    public void setuRole(Character uRole) {
        this.uRole = uRole;
    }

    public int gethPoints() {
        return hPoints;
    }

    public void sethPoints(int hPoints) {
        this.hPoints = hPoints;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", uFirstName=" + uFirstName + ", uLastName=" + uLastName + ", email=" + email + ", uAddress=" + uAddress + ", uPassword=" + uPassword + ", uRole=" + uRole + ", hPoints=" + hPoints + '}';
    }
    

}