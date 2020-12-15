/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.services;

import edu.ablazebookstore.models.Book;
import java.sql.Connection;
import edu.ablazebookstore.test.MyConnection;
import java.io.FileInputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author JARR
 */
public class BookCrud {

    Connection cnx;
    private FileInputStream fis;
    public BookCrud() {
        cnx = MyConnection.getInstance().getCnx();

    }

    public void addBook() {
        try {
            String requete = "INSERT INTO Book(title,author) VALUES"
                    + " ('the shining','stephen king')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Book added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addBook(Book b) {
        try {

            String requete = "INSERT INTO Book(title,author,price,publisher,isbn,releaseDate,cover,image,description,categorie) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx
                    .prepareStatement(requete);
            pst.setString(1, b.getTitle());
            pst.setString(2, b.getAuthor());
            pst.setFloat(3, b.getPrice());
            pst.setString(4, b.getPublisher());
            pst.setString(5, b.getIsbn());
            pst.setDate(6, b.getReleasedate());
            pst.setString(7, b.getCover());

                        pst.setString(8, b.getCover());
                           pst.setString(9, b.getDescription());
                        pst.setString(10, b.getCategory());

            pst.executeUpdate();
            System.out.println("Book added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean deleteBook(Book b) {
        boolean etat = false;
        try {
            String requete = "DELETE FROM Book WHERE idBook=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, b.getId());
            pst.executeUpdate();
            System.out.println("Book deleted");
            etat = true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            etat = false;
        }
        return etat;
    }

    public List<Book> listBooks() {
        List<Book> myList = new ArrayList<Book>();
        try {
            String requete = "SELECT * FROM Book";
            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Book p = new Book();
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString("title"));
                p.setIsbn(rs.getString("isbn"));
                p.setPublisher(rs.getString("publisher"));
                p.setPrice(rs.getFloat("price"));
                System.out.println("doing image");
               ImageView iv= new ImageView(new Image((rs.getString("cover"))));
               iv.setFitWidth(150);
               iv.setFitHeight(150);
                p.setPhoto(iv);
                System.out.println("image done");
                p.setReleasedate(rs.getDate("releaseDate"));
                p.setAuthor(rs.getString("author"));
                p.setCover(rs.getString("cover"));
                p.setDescription(rs.getString("description"));
                p.setCategory(rs.getString("categorie"));
//                ImageView bphoto = new ImageView(new Image(p.getCover()));
//                p.setPhoto(bphoto);
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public boolean updateBook(Book p) {
        try {
            String requete = "Update    Book  SET title=?,author=?,isbn=?,publisher=?,releasedate=?,cover=? WHERE bookid=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, p.getTitle());
            pst.setString(2, p.getAuthor());
            pst.setString(3, p.getIsbn());
            pst.setString(4, p.getPublisher());
            pst.setDate(5, p.getReleasedate());
            pst.setString(6, p.getCover());
            
            pst.setInt(7, p.getId());

            pst.executeUpdate();
            System.out.println("book updated");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }catch(NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
        return true;

    }
}
