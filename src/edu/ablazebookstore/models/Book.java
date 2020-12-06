/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.models;

/**
 *
 * @author JARR
 */
public class Book {
    private int id,nbpages,price;
    private String title,author,isbn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbpages() {
        return nbpages;
    }

    public void setNbpages(int nbpages) {
        this.nbpages = nbpages;
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
        return "Book{" + "id=" + id + ", nbpages=" + nbpages + ", title=" + title + ", author=" + author + ", isbn=" + isbn + '}';
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Book() {
    }

    public Book(int nbpages, String title, String author, String isbn) {
        this.nbpages = nbpages;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
    
    
    
}
