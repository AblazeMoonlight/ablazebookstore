/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import edu.ablazebookstore.models.Client;
import edu.ablazebookstore.models.User;
import edu.ablazebookstore.services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class UpdateUserController implements Initializable {

    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField address;
    @FXML
    private PasswordField password;
    @FXML
    private Button valid;
    @FXML
    private Label mdifier;
    @FXML
    private Label ErrorUserPassword;
    @FXML
    private Label errorFirstName;
    @FXML
    private Label errorLastName;
    @FXML
    private Label ErrorEmail;
    @FXML
    private Label ErrorAddress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prenom.setText(UserService.connectedUser.getuFirstName());
        nom.setText(UserService.connectedUser.getuLastName());
        email.setText(UserService.connectedUser.getEmail());
        address.setText(UserService.connectedUser.getuAddress());
        password.setText(UserService.connectedUser.getuPassword());
        // TODO
    }

    @FXML
    public void UpdateUser(javafx.event.ActionEvent actionEvent) throws SQLException {
        Boolean register = true;
        String firstName = prenom.getText();
        String lastName = nom.getText();
        String mail = email.getText();
        String adrs = address.getText();
        String pwd = password.getText();
        if (firstName.equals("") || firstName == null) {
            this.errorFirstName.setText("Please enter First Name");
            register = false;
        }
        if (lastName.equals("") || lastName == null) {
            this.errorLastName.setText("Please enter Last Name");
            register = false;
        }
        if (pwd.equals("") || pwd == null) {
            this.ErrorUserPassword.setText("Please enter Password");
            register = false;
        }
        if (mail.equals("") || mail == null) {
            this.ErrorEmail.setText("Please enter Email");
            register = false;
        }
        if (adrs.equals("") || adrs == null) {
            this.ErrorAddress.setText("Please enter Adress");
            register = false;
        }
        if (!isValidEmailAddress(mail)) {
            this.ErrorEmail.setText("Email not valid");
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
        if (pwd.length() > 50 || pwd.length() < 8) {
            this.ErrorUserPassword.setText("Password invalid");
            register = false;
        }
        if (register) {
             UserService b = new UserService();
            b.updateUser(firstName, lastName, mail, adrs, pwd);
            this.mdifier.setText("user updated");
        } else {
            this.mdifier.setText("user not updated");
        }
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}
