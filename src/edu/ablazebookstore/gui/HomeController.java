/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author JARR
 */
public class HomeController implements Initializable {

    @FXML
    private JFXButton manageBooks;
    @FXML
    private Pane pangemb;
    @FXML
    private Pane panesupport;
    @FXML
    private Pane paneconsult;
    @FXML
    private Pane paneclient;
    @FXML
    private Pane panecart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void manageBooks(ActionEvent event) throws IOException {
        Pane newLoadedPane =FXMLLoader.load(getClass().getResource("DisplayBook.fxml"));
    pangemb.getChildren().add(newLoadedPane); 
    }
    
}
