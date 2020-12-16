/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.ablazebookstore.models.Admin;
import edu.ablazebookstore.models.Client;
import edu.ablazebookstore.models.User;
import edu.ablazebookstore.services.UserService;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class LoginUController implements Initializable {

    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField loginpassword;
    @FXML
    private Label ErrorLogin;
    @FXML
    private Label ErrorLoginPassword;
    @FXML
    private Label errorEmail;
    @FXML
    private Button login;

    @FXML
    private Button sign;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void loginUser() throws IOException, SQLException {
        boolean login = true;

        if (this.email.getText() == null || this.email.getText().equals("")) {
            this.errorEmail.setText("Please enter email");
            login = false;
        }

        if (this.loginpassword.getText() == null || this.loginpassword.getText().equals("")) {
            this.ErrorLoginPassword.setText("Please enter Password");
            login = false;
        }
        if (this.loginpassword.getText().length() > 50) {
            this.loginpassword.setText("The Password you have entered is too long");
            login = false;
        }
        if (login) {
            this.ErrorLogin.setText("");
            this.ErrorLoginPassword.setText("");
            this.errorEmail.setText("");

            String email = this.email.getText();
            String password = this.loginpassword.getText();
            UserService userService = new UserService();
            User user = userService.authenticateUser(email, password);
            if (user != null) {
                UserService.connectedUser = user;
                this.redirectToHome();
            } else {
                this.ErrorLogin.setText("Please enter Valid credentials");
            }
        }
    }

    private void redirectToHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("\\edu\\ablazebookstore\\gui\\Home.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Home");
        Parent root2 = loader.load();
        HomeController controller = (HomeController) loader.getController();
        stage.setScene(new Scene(root2));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("\\edu\\ablazebookstore\\gui\\Register.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("RegisterPage");
            Parent root2 = loader.load();
            RegisterController controller = (RegisterController) loader.getController();
            stage.setScene(new Scene(root2));
            stage.show();

        } catch (IOException ex) {
            System.out.println("Error occured while inflating view: " + ex);
        }
    }
        public void ResetPage(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("\\edu\\ablazebookstore\\gui\\ResetPassword.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Reset Page");
            Parent root2 = loader.load();
            ResetPasswordController controller = (ResetPasswordController) loader.getController();
            stage.setScene(new Scene(root2));
            stage.show();

        } catch (IOException ex) {
            System.out.println("Error occured while inflating view: " + ex);
        }

    }
}
