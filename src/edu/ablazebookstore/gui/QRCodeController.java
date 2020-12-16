/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.gui;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import static edu.ablazebookstore.services.QRCodeAPi.createQR;
import edu.ablazebookstore.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * FXML Controller class
 *
 * @author ASUS 550
 */
public class QRCodeController implements Initializable {

    @FXML
    private ImageView qrImg;
    @FXML
    private TextField emailAdrs;
    @FXML
    private Button btPartager;
public static Random rr=new Random()  ;
            String random=rr.toString();
    @FXML
    private TextField thesender;
  
    @FXML
    private PasswordField passwordFd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
     genererQR();
    
    
    
    }    
   

   public void setImage(Image img)    { 
       this.qrImg.setImage(img);
   } 

    @FXML
    private void PartagerBook(ActionEvent event)  {
    
        String host="smtp.gmail.com";  
  String user=thesender.getText();//change accordingly  
   String password=passwordFd.getText();//change accordingly  
    
  String to=emailAdrs.getText();//change accordingly  
  
   //Get the session object  
   Properties props = new Properties(); 
props.put("mail.smtp.starttls.enable", "true");

   props.put("mail.smtp.host",host);  
   props.put("mail.smtp.auth", "true");  
     
    Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject("Spread Reading all over the world");  
     message.setText("This is the book i shared with you using my honory points from ablazebook  store");  
       
    //send the message  
     
  
      BodyPart messageBodyPart1 = new MimeBodyPart();  
    messageBodyPart1.setText("This is message body");  
      
    //4) create new MimeBodyPart object and set DataHandler object to this object      
    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
  
    String filename = "C:\\Users\\ASUS 550\\Desktop\\recordsapp\\"+random+".png";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName(filename);  
     
     
    
   Multipart multipart = new MimeMultipart();  
    multipart.addBodyPart(messageBodyPart1);  
    multipart.addBodyPart(messageBodyPart2);  
  
    //6) set the multiplart object to the message object  
    message.setContent(multipart );  
     
    //7) send message  
    Transport.send(message);  
    
    
    
    
    
   
     
     
     
     
     System.out.println("message sent successfully...");  
   
     } catch (MessagingException e) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setTitle("Error ");
        alert.setHeaderText("Credentials checkout");
        alert.setContentText("Verify your credentials");
        alert.showAndWait();
     
     }  
     System.out.println("done");
    
    
    
    
    
    
    
    
    }



public  void genererQR() {
        try {
           
          
            
            
            
// The data that the QR code will contain

String data = "https://drive.google.com/drive/u/1/folders/1uqmhhYAqv4UjnEi7qT3LERg6Af_SfnZo";

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

   
     
       
       
       
       } catch (WriterException ex) {
            Logger.getLogger(QRCodeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QRCodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    
    
    
    
    
    
}







}
