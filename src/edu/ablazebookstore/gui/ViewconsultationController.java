/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.ablazebookstore.models.Book;
import edu.ablazebookstore.services.BookCrud;
import edu.ablazebookstore.services.ConsultationLivreCRUD;
import edu.ablazebookstore.test.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;




/**
 * FXML Controller class
 *
 * @author ASUS 550
 */
public class ViewconsultationController implements Initializable {

    @FXML
    private TableView<Book> allBooksTable;
    @FXML
    private TableColumn<Book, Integer> idBk;
    @FXML
    private TableColumn<Book, String> title;
    @FXML
    private TableColumn<Book, String> auth;
    @FXML
    private TableColumn<Book, String> rlSdate;
    @FXML
    private TableColumn<Book, String> category;
    @FXML
    private Label stext;
    @FXML
    private TextField searchForBook;
    Stage stage;
   ObservableList<Book> listB;
  ObservableList<Book> mesDonnees;
    @FXML
    private ContextMenu cntxMenu;
    @FXML
    private MenuItem viewDetmnItem;
    Connection cnx = MyConnection.getInstance().getCnx();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
      
        
        
        /*idBk.setCellValueFactory(new PropertyValueFactory<>("id"));
         title.setCellValueFactory(new PropertyValueFactory<>("title"));
         auth.setCellValueFactory(new PropertyValueFactory<>("author"));
         rlSdate.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
         category.setCellValueFactory(new PropertyValueFactory<>("categorie"));*/
        
        
        search_Books();
       
     ConsultationLivreCRUD crd=new ConsultationLivreCRUD();
    // allBooksTable.getItems().setAll(crd.listBooks());*/
        // allBooksTable.setItems((ObservableList<Book>) crd.listBooks());*/
    
         ArrayList<String> stuff=new ArrayList<String>();
        
         
         stuff.add(searchForBook.getText());
       // TextFields.bindAutoCompletion(searchForBook,crd.listBooks(). );
        
    
    }    
    
 
    void search_Books() { 
        
         idBk.setCellValueFactory(new PropertyValueFactory<>("id"));
         title.setCellValueFactory(new PropertyValueFactory<>("title"));
         auth.setCellValueFactory(new PropertyValueFactory<>("author"));
         rlSdate.setCellValueFactory(new PropertyValueFactory<>("releasedate"));
         category.setCellValueFactory(new PropertyValueFactory<>("category"));
         ConsultationLivreCRUD crd=new ConsultationLivreCRUD();
        mesDonnees=crd.listBooks();
        allBooksTable.getItems().setAll(mesDonnees);
        FilteredList<Book> filteredData = new FilteredList<>(mesDonnees, b -> true);		
       searchForBook.textProperty().addListener((observable,oldValue,newValue)-> {
            filteredData.setPredicate(Book-> { 
                if (newValue == null || newValue.isEmpty()) {
					return true;
				}				
				String lowerCaseFilter = newValue.toLowerCase();
                                
				if (String.valueOf(Book.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
                              } else if (Book.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches password
				}
				else if (Book.getAuthor().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;// Filter matches email
                                else if (Book.getCategory().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				    return true;
                                else if(String.valueOf(Book.getReleasedate()).toLowerCase().indexOf(lowerCaseFilter) != -1)
                                return true;
                                else  
				    	 return false; // Does not match.
			});
		});		
		SortedList<Book> sortedData = new SortedList<>(filteredData);		
		sortedData.comparatorProperty().bind(allBooksTable.comparatorProperty());		
		allBooksTable.setItems(sortedData);      
          
        
    }
    
     
    
    
    
    
    /*public ObservableList<String> ListNames() {
        return this.listCategories().stream()
                .map(c -> c.getName())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));*/

    @FXML
    private void ViewDetails(ActionEvent event) throws IOException {
    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reviewBookDetails.fxml"));
        
        Parent root =fxmlLoader.load();
        
         ReviewBookDetailsController rv=fxmlLoader.getController();
          
         Stage stage=new Stage();
         stage.setScene(new Scene(root));
         stage.show();

       Book bk=allBooksTable.getSelectionModel().getSelectedItem();
       System.out.println(bk.getId());
      List<Book> myList = new ArrayList<Book>();
      ImageView img =new ImageView()  ;
      try {
            String requete = "SELECT * FROM Book where bookid='"+bk.getId()+"'";
            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Book p = new Book();
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString("title"));
                p.setIsbn(rs.getString("author"));
                p.setPublisher(rs.getString("publisher"));
                p.setPrice(rs.getFloat("price"));
             
                p.setPhoto(new ImageView((new Image((rs.getString("cover"))))));
                
                p.setReleasedate(rs.getDate("releaseDate"));
                p.setAuthor(rs.getString("author"));
                p.setCover(rs.getString("cover"));
                p.setDescription(rs.getString("description"));
                p.setCategory(rs.getString("categorie"));
                        
                
             myList.add(p);
              rv.setprixLab("THE PRICE : " +String.valueOf(p.getPrice()) +" DT");
               rv.setdesCtext("ISBN : "+p.getIsbn()+"\n THE AUTHOR :"+p.getAuthor()+"\n THE PUBLISHER :"+p.getPublisher()+"\n RELEASED IN :"+p.getReleasedate()+ "\n"+p.getDescription());
               rv.setbkTitle(p.getTitle());
             
             
                     
               rv.setImage(new Image(p.getCover()));
            }
        
         
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
    }
       
    
    
    
    




