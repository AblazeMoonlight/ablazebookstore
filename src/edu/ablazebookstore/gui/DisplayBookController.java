/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import edu.ablazebookstore.models.Book;
import edu.ablazebookstore.services.BookCrud;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author JARR
 */
public class DisplayBookController implements Initializable {

    @FXML
    private TableColumn<Book, String> txid;
    @FXML
    private TableColumn<Book, String> txtitle;
    @FXML
    private TableColumn<Book, String> txauthor;
    @FXML
    private TableColumn<Book, String> txisbn;
    @FXML
    private TableColumn<Book, String> txprice;
    @FXML
    private TableColumn<Book, String> txpublisher;
    @FXML
    private TableColumn<Book, String> txreleasedate;
    @FXML
    private TableView<Book> tableview;
    @FXML
    private TableColumn<Book, String> txcover;
    @FXML
    private ImageView aze;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();  
        BookCrud bc = new BookCrud();
      
        tableview.getItems().setAll(bc.listBooks());
    

    }    
 List<Book> myList = new ArrayList<Book>();
     
    private void initCol() {
     
        txid.setCellValueFactory(new PropertyValueFactory<>("id"));
        txtitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        txauthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        txpublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        txisbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        txprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        txreleasedate.setCellValueFactory(new PropertyValueFactory<>("releasedate"));
        txcover.setPrefWidth(80);
        txcover.setCellValueFactory(new PropertyValueFactory<>("cover"));

    }
    
}
