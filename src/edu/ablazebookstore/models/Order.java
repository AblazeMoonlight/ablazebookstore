package edu.ablazebookstore.models;


import java.util.Date;
import javafx.scene.control.ComboBox;
/**
 *
 * @author userJT
 */
public class Order {
    private int idOrder,idClient,idBook;
    private int quantitie;
    private Date dateAchat;
    private String title;
    private int price;
    private ComboBox txquantity;
    
    public Order(){}

    public Order(int idOrder, int idClient, int idBook, int quantitie,String title,int price) {
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.idBook = idBook;
        //this.quantitie = quantitie;
        this.dateAchat = dateAchat;
        this.title=title;
        this.price=price;
        this.txquantity=new ComboBox();
}

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ComboBox getTxquantity() {
        return txquantity;
    }

    public void setTxquantity(ComboBox txquantity) {
        this.txquantity = txquantity;
    }

    
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getQuantitie() {
        return quantitie;
    }

    public void setQuantitie(int quantitie) {
        this.quantitie = quantitie;
    }

    public String getDateAchat() {
        return dateAchat.toString();
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    @Override
    public String toString() {
        return "Order{" + "idOrder=" + idOrder + ", idClient=" + idClient + ", idBook=" + idBook + ", quantitie=" + quantitie + ", dateAchat=" + dateAchat + '}';
    }
    
    
    
}
