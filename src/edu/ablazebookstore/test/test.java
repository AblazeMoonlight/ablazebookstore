/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.test;

import edu.ablazebookstore.models.Book;
import edu.ablazebookstore.services.BookCrud;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author JARR
 */
public class test { public static void main(String[] args) {
        MyConnection mc = MyConnection.getInstance();
            BookCrud bc = new BookCrud();
            bc.deleteBook(10);   //
            Book b = new Book(55, "obama","life", "565fsq", "156f", Date.valueOf("2020-12-02"), "dzda");
            bc.updateBook(b, 12);
            System.out.println(bc.listBooks());
}
}