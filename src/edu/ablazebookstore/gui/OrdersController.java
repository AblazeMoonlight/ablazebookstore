/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import edu.ablazebookstore.models.Order;
import edu.ablazebookstore.services.OrderCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author userJT
 */
public class OrdersController implements Initializable {

    @FXML
    private TableView<Order> tableOrders;
    @FXML
    private TableColumn<?, ?> tusernameorders;
    @FXML
    private TableColumn<?, ?> tbookorders;
    @FXML
    private TableColumn<?, ?> tdateorders;
    @FXML
    private TableColumn<?, ?> tpriceorders;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OrderCrud o=new OrderCrud();
        tableOrders.getItems().setAll(o.listeOrders());
    }    
    
}
