/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import edu.ablazebookstore.models.User;
import edu.ablazebookstore.services.UserService;
import edu.ablazebookstore.services.javaMail;
import java.io.IOException;
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
public class ResetPasswordController implements Initializable {

    @FXML
    private TextField tokenn;
    @FXML
    private PasswordField pwd;
    @FXML
    private Button reset;
    @FXML
    private TextField mail;
    @FXML
    private Button tokensend;
    @FXML
    private Label msg;
    @FXML
    private Label errorUserPassword;
    @FXML
    private Label resets;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
 @FXML
    public void ResetPassword() throws IOException,SQLException {
        String email= mail.getText();
        String token=tokenn.getText();
        String pwd= this.pwd.getText();
        javaMail service = new javaMail();
            UserService u=new UserService();
        if(!u.checkMail(email))
        {
            service.mail(email);
             this.msg.setText("token sended");
        }else{
            this.msg.setText("email not existing");
        }
        
    } 
     @FXML
    public void ResetPass() throws IOException,SQLException {
        
        boolean registre =true;
        String email= mail.getText();
        String token=tokenn.getText();
        String pwd= this.pwd.getText();
          if (pwd.length() > 50 || pwd.length() < 8) {
            this.errorUserPassword.setText("Password invalid");
            boolean register = false;
        }
          if(registre){
        javaMail service = new javaMail();
            service.ResetPass(token,pwd,email); 
             this.resets.setText("Password updated");
        
    }    
    
}}
