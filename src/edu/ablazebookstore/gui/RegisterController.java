/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.ablazebookstore.models.Client;
import edu.ablazebookstore.services.UserService;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class RegisterController implements Initializable {

    @FXML
    private JFXPasswordField confirmPass;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField firstName;
    @FXML
    private Button register;
    @FXML
    private Label errorEmail;
    @FXML
    private Label errorUserPassword;
    @FXML
    private Label errorFirstName;
    @FXML
    private Label errorLastName;
    @FXML
    private Label errorPass;
    @FXML
    private Label ErrorAddress;
    @FXML
    private Label auth;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

     @FXML
    public void registerUser() throws SQLException {

        boolean register = true;
        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String email = this.email.getText();
        String address = this.address.getText();
        String password = this.password.getText();
        String password2 = this.confirmPass.getText();

        if (firstName.equals("") || firstName == null) {
            this.errorFirstName.setText("Please enter First Name");
            register = false;
        }
        if (lastName.equals("") || lastName == null) {
            this.errorLastName.setText("Please enter Last Name");
            register = false;
        }
        if (password.equals("") || password == null) {
            this.errorUserPassword.setText("Please enter Password");
            register = false;
        }
        if (email.equals("") || email == null) {
            this.errorEmail.setText("Please enter Email");
            register = false;
        }
          if (address.equals("") ||address == null) {
            this.ErrorAddress.setText("Please enter Adress");
            register = false;
        }
          if (!isValidEmailAddress( email)) {
            this.errorEmail.setText("Email not valid");
            register = false;
        }

        if (firstName.length() > 50) {
            this.errorFirstName.setText("First Name cannot comprise more then 50 letters");
            register = false;
        }
        if (lastName.length() > 50) {
            this.errorLastName.setText("Last Name cannot comprise more then 50 letters");
            register = false;
        }
        if (password.length() > 50 || password.length() < 8) {
            this.errorUserPassword.setText("Password invalid");
            register = false;
        }
         if (!password.equals(password2)) {
            this.errorPass.setText("Password different");
            register = false;
        }
        
        if (email.length() > 30 ) {
            this.errorEmail.setText("Address cannot comprise more then 30 letters");
            register = false;
        }

        if (register) {
            this.errorFirstName.setText("");
            this.errorLastName.setText("");
            this.errorUserPassword.setText("");
            this.errorEmail.setText("");
            this.errorPass.setText("");

            Client customer = new Client();
            customer.setUserID(10);
            customer.setuFirstName(firstName);
            customer.setuLastName(lastName);
            customer.setEmail(email);
            customer.setuPassword(password);
            customer.setuAddress(address);
            customer.setuRole('c');
            customer.sethPoints(0);
            customer.setTokenResetPassword("");

            UserService b = new UserService();
           if( b.checkMail(email))
           { b.AddUser(customer);
            this.auth.setText("account created");}
           else{this.auth.setText("account exist");
          
        }}}

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}


