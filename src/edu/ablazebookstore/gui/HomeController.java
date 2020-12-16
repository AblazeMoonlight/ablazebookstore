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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @FXML
    private Pane panepaymen;

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
    hide(panecart,panesupport,paneconsult,paneclient,panepaymen,pangemb);
    }

    @FXML
    private void viewCart(ActionEvent event) throws IOException {
        Pane newLoadedPane =FXMLLoader.load(getClass().getResource("Cart.fxml"));
    panecart.getChildren().add(newLoadedPane); 
    
    hide(pangemb,panesupport,paneconsult,paneclient,panepaymen,panecart);
    }

    private void paymentInfo(ActionEvent event) throws IOException {
        
    
    }
    
    public void hide(Pane p1,Pane p2,Pane p3,Pane p4,Pane p5,Pane p6){
        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);
        p4.setVisible(false);
        p5.setVisible(false);
        p6.setVisible(true);
    }

  

    @FXML
    private void viewBook(ActionEvent event) throws IOException {
         Pane newLoadedPane =FXMLLoader.load(getClass().getResource("viewconsultation.fxml"));
    paneconsult.getChildren().add(newLoadedPane); 
    hide(pangemb,panesupport,panepaymen,paneclient,panecart,paneconsult);
    }

    @FXML
    private void paymentInfos(ActionEvent event) {
        try {
            Pane newLoadedPane =FXMLLoader.load(getClass().getResource("payment.fxml"));
            panepaymen.getChildren().add(newLoadedPane);
            hide(pangemb,panesupport,paneconsult,paneclient,panecart,panepaymen);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
