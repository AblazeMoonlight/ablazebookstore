/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.models;

import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author JARR
 */
public class Book {
    private ImageView photo;

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
    private int id;
    private float price;
    private String title, author, isbn, publisher, cover;
   
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

  
   

 

    private Date releasedate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", price=" + price + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", publisher=" + publisher + ", releasedate=" + releasedate + '}';
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public Book() {
    }

    public Book(float price, String title, String author, String isbn, String publisher, Date releasedate, String cover) {
        this.price = price;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.releasedate = releasedate;
        this.cover = cover;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;

    }
}
