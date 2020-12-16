/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import edu.ablazebookstore.test.*;
import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.QRCode;
import com.mysql.jdbc.log.Log;

import edu.ablazebookstore.models.Book;
import edu.ablazebookstore.models.Commentaire;
import edu.ablazebookstore.models.Order;
import edu.ablazebookstore.models.User;
import edu.ablazebookstore.services.BookCrud;
import edu.ablazebookstore.services.CommentaireCrud;
import edu.ablazebookstore.services.HonoryPointsCrud;
import edu.ablazebookstore.services.OrderCrud;
import edu.ablazebookstore.services.QRCodeAPi;
import static edu.ablazebookstore.services.QRCodeAPi.createQR;
import edu.ablazebookstore.services.UserService;
import edu.ablazebookstore.test.MyConnection;

import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author ASUS 550
 */
public class ReviewBookDetailsController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private ImageView bookImg;
    @FXML
    private TextArea desCtxt;
    @FXML
    private Button Commenter;
    @FXML
    private Button shareBook;
    @FXML
    private Button ajtFavoris;
    @FXML
    private Label prixLab;
    Connection cnx = MyConnection.getInstance().getCnx();
    @FXML
    private Label bkTitle;
    @FXML
    private Button addCart;
    @FXML
    private TextArea cmtSec;

    /**
     * Initializes the controller class.
     */

    public void setbkTitle(String message) {
        this.bkTitle.setText(message);
    }

    public void setImage(Image img) {

        this.bookImg.setImage(img);
    }

    public void setprixLab(String message) {
        this.prixLab.setText(message);

    }

    public void setdesCtext(String message) {
        this.desCtxt.setText(message);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void shareThebook(ActionEvent event)  {
      
      
      
            /*Random rr=new Random()  ;
            String random;
            random=rr.toString();
            
            
            
            
            // The data that the QR code will contain
            
            String data = "https://drive.google.com/drive/u/0/folders/1G1dEhfuv_lebCcLAV5XBR9xA00bwDtpP";
            
            // The path where the image will get saved
            String path = "C:\\Users\\ASUS 550\\Desktop\\recordsapp\\"+random+".png";
            
            // Encoding charset
            String charset = "UTF-8";
            
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap
            = new HashMap<EncodeHintType,
            ErrorCorrectionLevel>();
            
            hashMap.put(EncodeHintType.ERROR_CORRECTION,
            ErrorCorrectionLevel.L);
            
            // Create the QR code and save
            // in the specified folder
            // as a jpg file
            createQR(data, path, charset, hashMap, 200, 200);
            System.out.println("QR Code Generated!!! ");
            QRCodeController qr =new QRCodeController ();
            qr.setImage(new Image(path));*/
            HonoryPointsCrud dd = new HonoryPointsCrud();
  
            
         int test=dd.getUserHonoryPoints(1).get(0);;
           
             if(test<5) { 
              Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Sharing Book");
        alert.setHeaderText("Invalid Operation");
        alert.setContentText("You don't have enough Honory points to acces this functionality");
        alert.showAndWait();
             }
            
            
             else  {
             
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("using honory points");
        alert.setHeaderText(null);
        alert.setContentText("confirm your use for honory point");
        Optional<ButtonType> answer = alert.showAndWait();
       
         if (answer.get().equals(ButtonType.OK)) {
                 
                
                
                 
                 
                 
                 
                 try {
            
                       HonoryPointsCrud p =new HonoryPointsCrud();
         int use=UserService.connectedUser.getUserID();
         int hono= UserService.connectedUser.gethPoints();
               String a1=UserService.connectedUser.getuFirstName();
               String a2=UserService.connectedUser.getuLastName();
               String  a3=UserService.connectedUser.getEmail();
               Character a4=UserService.connectedUser.getuRole();
               String a5=UserService.connectedUser.getuPassword();
               String a6=UserService.connectedUser.getuAddress();
               String a7=UserService.connectedUser.getTokenResetPassword();
               p.updateHonory(new User(use,a1,a2,a3,a6,a5,a4,hono,a7));
                     
                     
                     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QRCode.fxml"));
            
            Parent root = fxmlLoader.load();
            
            QRCodeController rv = fxmlLoader.getController();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
                     
           
 }      catch (IOException ex) {  
            Logger.getLogger(ReviewBookDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
                
             }
    }

        
    
    }
    

   


@FXML
        private void addCart(ActionEvent event) {
            ViewconsultationController vv=new ViewconsultationController();
            Order o=new Order();
            Book b=new Book();
            ViewconsultationController vb=new ViewconsultationController();
            b=vv.donnerBook();
            o.setIdBook(b.getId());
            o.setQuantitie(0);
            o.setIdClient(UserService.connectedUser.getUserID());
            OrderCrud or=new OrderCrud();
            or.addOrder(o);
    }

    @FXML
    private void createComment(ActionEvent event) {
    
    
        if(cmtSec.getText().trim().isEmpty()) {
    
    Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("no empty comments");
        alert.setHeaderText("Invalid Operation");
        alert.setContentText("you need to insert a comment");
        alert.showAndWait();
        }
        else  
        
        {
        
     
       CommentaireCrud r= new CommentaireCrud();
       r.addCommentaire(new Commentaire(UserService.connectedUser.getUserID(),cmtSec.getText()));
       
     
         
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajouté");
        alert.setHeaderText("Information");
        alert.setContentText("Commentaire ajouté avec succès vous pouvez lui consulter");
        alert.showAndWait();
    
    
    
    
    }
    
    
    
    
    
    }

    private void checkComments(ActionEvent event) throws IOException {
    
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QRCode.fxml"));
            
            Parent root = fxmlLoader.load();
            
            QRCodeController rv = fxmlLoader.getController();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    
    
    }

    @FXML
    private void consultComments(ActionEvent event) throws IOException {
   
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("commentSection.fxml"));
            
            Parent root = fxmlLoader.load();
            
            CommentSectionController rv = fxmlLoader.getController();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    
    
    
    
    
    
    }

    
 












}
