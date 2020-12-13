/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import static edu.ablazebookstore.gui.BookController.editmode;
import edu.ablazebookstore.models.Book;
import edu.ablazebookstore.services.BookCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private MenuItem bookModifyOption;
    @FXML
    private TableColumn<Book, String> txcatg;
    @FXML
    private TableColumn<Book, String> txdesc;

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
        txcatg.setCellValueFactory(new PropertyValueFactory<>("category"));
        txdesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        txreleasedate.setCellValueFactory(new PropertyValueFactory<>("releasedate"));
        System.out.println("****");
        txcover.setCellValueFactory(new PropertyValueFactory<>("photo"));
        System.out.println("//////");

    }
    public static void alertWrng(String title,String content)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
            return;
    }
    
    
    @FXML
    private void bookDeleteOption(ActionEvent event) {
        Book selectedForDeletion = tableview.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            alertWrng("Book Deletion error","No book is selected");
          
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Book");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the book " + selectedForDeletion + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get().equals(ButtonType.OK)) {
            BookCrud bc = new BookCrud();
            bc.deleteBook(selectedForDeletion);

        } else {
                        alertWrng("Notice","Book deletion cancelled!");

        
        }
    }

    @FXML
    private void bookModifyOption(ActionEvent event) {
        Book selectedForModification = tableview.getSelectionModel().getSelectedItem();
        if (selectedForModification == null) {
                      alertWrng("Book update error","No book is selected");

        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modifying Book");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to Modify the book " + selectedForModification + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get().equals(ButtonType.OK)) {
            editmode = true;
            
            try {
                BookCrud bc = new BookCrud();
//            bc.updateBook(selectedForModification,selectedForModification.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addbook.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Edit Book");
                Parent root2 = loader.load();
                BookController controller = (BookController) loader.getController();
                controller.inflateUi(selectedForModification);
                stage.setScene(new Scene(root2));

                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(DisplayBookController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
                        alertWrng("Notice","Book update cancelled");

           
        }
    }

}
