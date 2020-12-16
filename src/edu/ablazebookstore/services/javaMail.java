/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.services;

import edu.ablazebookstore.test.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Nour
 */
public class javaMail {

    Connection cnx = MyConnection.getInstance().getCnx();

    public void mail(String email) throws SQLException {

        String[] to = {email}; // list of recipient email addresses
        String subject = "Reset password";
        String body = given();
        sendFromGMail("noreply@nuevera.com", "noreplynoreply", to, subject, body);
        String requete = "update Users set TokenResetPassword='" + body + "' where email='" + email + "'";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Token generated");
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

    }

    public String given() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return  generatedString ;
    }

    public void ResetPass(String token, String password, String email) throws SQLException {
        String requete = "SELECT TokenResetPassword FROM  Users where email='" + email + "'";
        PreparedStatement prepStmt = cnx.prepareStatement(requete);
        ResultSet resultSet = prepStmt.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString("TokenResetPassword").equalsIgnoreCase(token)) {
                String requete2 = "update Users set password='" + password + "' where email='" + email + "'";
                PreparedStatement pst = cnx.prepareStatement(requete2);
                pst.executeUpdate();
                System.out.println("ok");
            }

        }

    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        {

            String host = "ssl0.ovh.net";
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);

            try {
                message.setFrom(new InternetAddress(from));
                InternetAddress[] toAddress = new InternetAddress[to.length];

                // To get the array of addresses
                for (int i = 0; i < to.length; i++) {
                    toAddress[i] = new InternetAddress(to[i]);
                }

                for (int i = 0; i < toAddress.length; i++) {
                    message.addRecipient(Message.RecipientType.TO, toAddress[i]);
                }

                message.setSubject(subject);
                message.setText(body, "utf-8", "html");
                Transport transport = session.getTransport("smtp");
                transport.connect(host, from, pass);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            } catch (AddressException ae) {
                ae.printStackTrace();
            } catch (MessagingException me) {
                me.printStackTrace();
            }
        }
    }
}
