/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import edu.ablazebookstore.services.BookCrud;
import edu.ablazebookstore.models.Book;
import java.io.IOException;
import java.net.URL;
import java.sql.JDBCType;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author JARR
 */
public class BookController implements Initializable {

    @FXML
    private JFXTextField tftitle;
    @FXML
    private JFXTextField txauthor;
    @FXML
    private JFXTextField txisbn;
    @FXML
    private JFXTextField txpublisher;
    @FXML
    private JFXTextField txprice;

    @FXML
    private JFXButton btnadd;
    @FXML
    private JFXDatePicker txreleasedate;
    @FXML
    private JFXTextField txcover;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      
    private boolean validatefields() {

        if( tftitle.getText().isEmpty()||
            txauthor.getText().isEmpty()||
            txisbn.getText().isEmpty()||
            txpublisher.getText().isEmpty()||
            txcover.getText().isEmpty()||
            txprice.getText().isEmpty() || 
                txreleasedate.getEditor().getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("make sure no fields are empty");
            alert.showAndWait();
            return false;
            
        }
           return true;     
    }
    @FXML
    private void addbook(ActionEvent event) {
//        try {
            String title,author,publisher,isbn,cover;
            float price ;
            
            Date  releasedate;
            if(validatefields())
            {
            title=tftitle.getText();
            author=txauthor.getText();
            isbn=txisbn.getText();
            publisher=txpublisher.getText();
            cover=txcover.getText();

            price=Float.parseFloat(txprice.getText());
            releasedate =Date.valueOf(txreleasedate.getValue());
            BookCrud pc = new BookCrud();
            Book p1 = new Book(price, title, author, isbn,publisher,  releasedate,cover);
            pc.addBook(p1);
            }
//            System.out.println("******redirection******");
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("PesonDetails.fxml"));
//            Parent root2 = loader.load();
//            PesonDetailsController pc2 = loader.getController();
//            pc2.setTxnom(nom);
//            pc2.setTxtprenom(prenom);
//            tfnom.getScene().setRoot(root2);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());        }
//                
    }
  

}
