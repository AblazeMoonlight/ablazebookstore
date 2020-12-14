/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import static edu.ablazebookstore.gui.DisplayBookController.alertWrng;
import edu.ablazebookstore.services.BookCrud;
import edu.ablazebookstore.models.Book;
import edu.ablazebookstore.services.BookApiCall;
import java.io.IOException;
import java.net.URL;
import java.sql.JDBCType;
import java.time.LocalDate;
import java.sql.Date;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    public static boolean editmode = false;
    private int id;
    @FXML
    private JFXTextArea txdescription;
    @FXML
    private JFXTextField txcategory;
    @FXML
    private JFXButton btnload;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean validatefields() {

        if (tftitle.getText().isEmpty()
                || txauthor.getText().isEmpty()
                || txisbn.getText().isEmpty()
                || txdescription.getText().isEmpty()
                || txcategory.getText().isEmpty()
                || txpublisher.getText().isEmpty()
                || txcover.getText().isEmpty()
                || txprice.getText().isEmpty()
                || txreleasedate.getEditor().getText().isEmpty()) {
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
    private void addBook(ActionEvent event) {
        String title, author, publisher, isbn, cover, description, category;
        float price;

        Date releasedate;
        if (validatefields()) {
            title = tftitle.getText();
            author = txauthor.getText();
            isbn = txisbn.getText();
            publisher = txpublisher.getText();
            cover = txcover.getText();

            description = txdescription.getText();
            category = txcategory.getText();
            price = Float.parseFloat(txprice.getText());
            releasedate = Date.valueOf(txreleasedate.getValue());
            BookCrud pc = new BookCrud();
            Book p1 = new Book(price, title, author, isbn, publisher, cover, description, category, releasedate);

            if (editmode) {
                editBook();
            } else {
                pc.addBook(p1);

            }

        }

    }

    public void inflateUi(Book book) {
        id = book.getId();
        tftitle.setText(book.getTitle());
        txauthor.setText(book.getAuthor());
        if (editmode) {
            txisbn.setText(book.getIsbn());

        } else {
            txisbn.setText(BookApiCall.isbn);

        }
        txpublisher.setText(book.getPublisher());
        txcover.setText(book.getCover());
        txdescription.setText(book.getDescription());
        txcategory.setText(book.getCategory());
        txprice.setText(String.valueOf(book.getPrice()));
        txreleasedate.setValue(book.getReleasedate().toLocalDate());
    }

    private void editBook() {
        btnadd.setText("Edit Book");
        Book book = new Book(Float.parseFloat(txprice.getText()), tftitle.getText(),
                txauthor.getText(), txisbn.getText(), txpublisher.getText(),
                Date.valueOf(txreleasedate.getValue()), txcover.getText());
        book.setId(id);
        BookCrud pc = new BookCrud();
        if (pc.updateBook(book)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Updated");
            alert.setHeaderText(null);
            alert.setContentText("The selected book has been updated");
            alert.showAndWait();
        }
    }

    @FXML
    private void loadBook(ActionEvent event) {

        if (txisbn.getText().isEmpty()) {
            alertWrng("Empty Isbn Field", "Please Insert Isbn then try again");
               
        }

        this.inflateUi(BookApiCall.gbconnect(txisbn.getText()));

    }

}
