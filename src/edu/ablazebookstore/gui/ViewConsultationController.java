/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;


import edu.ablazebookstore.models.Book;
import edu.ablazebookstore.services.ConsultationLivreCRUD;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS 550
 */
public class ViewConsultationController implements Initializable {

    @FXML
    private TableView<Book> allBooksTable;
    @FXML
    private TableColumn<Book, Integer> idBk;
    @FXML
    private TableColumn<Book, String> title;
    @FXML
    private TableColumn<Book, String> auth;
    @FXML
    private TableColumn<Book, Date> rlSdate;
    @FXML
    private TableColumn<Book, String> category;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        /* idBk.setCellValueFactory(new PropertyValueFactory<>("id"));
         title.setCellValueFactory(new PropertyValueFactory<>("title"));
         auth.setCellValueFactory(new PropertyValueFactory<>("author"));
         rlSdate.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
         category.setCellValueFactory(new PropertyValueFactory<>("Categorie"));*/
        
        
        
       
     /*   ConsultationLivreCRUD crd=new ConsultationLivreCRUD();
     allBooksTable.getItems().setAll(crd.listBooks());
        // allBooksTable.setItems((ObservableList<Book>) crd.listBooks());*/
    
    }    
    
 
    
    
    
   
    
    
    
    
    /*public ObservableList<String> ListNames() {
        return this.listCategories().stream()
                .map(c -> c.getName())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));*/



}
