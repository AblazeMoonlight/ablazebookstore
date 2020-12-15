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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author userJT
 */
public class OrderController implements Initializable {

    @FXML
    private TableView<Order> tableOrder;
    @FXML
    private TableColumn<?, ?> tbookorder;
    @FXML
    private TableColumn<?, ?> tdateorder;
    @FXML
    private TableColumn<?, ?> tpriceorder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        tbookorder.setCellValueFactory(new PropertyValueFactory<>("title"));
        tdateorder.setCellValueFactory(new PropertyValueFactory<>("dateAchat"));
        tpriceorder.setCellValueFactory(new PropertyValueFactory<>("Price"));
        
        OrderCrud o=new OrderCrud();
        
        tableOrder.getItems().setAll(o.listeOrders());
    }    
    
}
