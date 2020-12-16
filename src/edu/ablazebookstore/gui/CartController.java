/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import com.sun.prism.impl.Disposer.Record;
import edu.ablazebookstore.models.Order;
import edu.ablazebookstore.services.OrderCrud;
import edu.ablazebookstore.services.PaymentCrud;
import edu.ablazebookstore.services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author userJT
 */
public class CartController implements Initializable {

    @FXML
    private TableColumn<Order, String> txtitle;

    @FXML
    private TableColumn<Order, String> txuniteprice;

    @FXML
    private TableColumn<Order, String> txdisount;

    @FXML
    private TableColumn<Order, String> txprice;

    @FXML
    TableView<Order> tableOrder;

//   
    OrderCrud o = new OrderCrud();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        txuniteprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        //txquantity.setCellValueFactory(new PropertyValueFactory<>("txquantity"));
        txprice.setCellValueFactory(new PropertyValueFactory<>("price"));

        Callback<TableColumn<Order, String>, TableCell<Order, String>> cellFactory
                = (TableColumn<Order, String> param) -> new EditingCell();
//        txquantity.setCellFactory(cellFactory);

        tableOrder.getItems().setAll(o.listeOrder(UserService.connectedUser.getUserID()));

        //delete button
        TableColumn col_action = new TableColumn<>("Action");
        tableOrder.getColumns().add(col_action);

        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        col_action.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }

        });

    }

    @FXML
    private void buybutton(ActionEvent event) {
        PaymentCrud py = new PaymentCrud();
        py.chargeStripCard();
//        OrderCrud or=new OrderCrud();
//        or.modifyOrder(o);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("INFORMATION");
        alert.setHeaderText(null);
        alert.setContentText("TRANSACTION WITH SUCCESS");
        alert.showAndWait();
    }

    //Define the button cell
    private class ButtonCell extends TableCell<Record, Boolean> {

        final Button cellButton = new Button("Remove");

        ButtonCell() {

            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                    Order currentOrder = (Order) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    //remove selected item from the table list
                    o.deleteOrder(currentOrder.getIdOrder());
                    o.listeOrder(2).remove(currentOrder);
                }
            });
        }

        //Display button if the row is not empty
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            } else {
                setGraphic(null);
            }
        }

    }

    class EditingCell extends TableCell<Order, String> {

        private TextField textField;

        private EditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(item);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
//                        setGraphic(null);
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.setOnAction((e) -> commitEdit(textField.getText()));
            textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (!newValue) {
                    System.out.println("Commiting " + textField.getText());
                    commitEdit(textField.getText());
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem();
        }
    }

}
