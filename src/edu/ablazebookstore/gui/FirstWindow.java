/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;
    
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author JARR
 */
public class FirstWindow extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("LoginU.fxml"));
            
            Scene scene = new Scene(root, 750, 500);
            
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
           /* primaryStage.setMinHeight(780);
            primaryStage.setMinWidth(1350);
            primaryStage.setMaxHeight(780);
            primaryStage.setMaxWidth(1350);*/
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
