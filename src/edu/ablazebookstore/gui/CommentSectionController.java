/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import edu.ablazebookstore.models.Commentaire;
import edu.ablazebookstore.services.CommentaireCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS 550
 */
public class CommentSectionController implements Initializable {

    @FXML
    private TableView<Commentaire> viewComments;
    @FXML
    private MenuItem modif;
    @FXML
    private MenuItem supp;
    @FXML
    private TableColumn<Commentaire, String> user_id;
    @FXML
    private TableColumn<Commentaire, String> commentaire;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
         loadtable();
         
    }    

    
    
    
    public void loadtable() { 
        user_id.setCellValueFactory(new PropertyValueFactory<>("iduser"));
         commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
         CommentaireCrud crd=new CommentaireCrud();
         viewComments.getItems().setAll(crd.listComments());
        
        
    }
    
    
    
    
    @FXML
    private void mettreJour(ActionEvent event) {
    }

    @FXML
    private void supprimerComm(ActionEvent event) {
    }
    
}
