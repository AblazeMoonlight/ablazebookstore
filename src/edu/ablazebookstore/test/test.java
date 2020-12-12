/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.test;

import edu.ablazebookstore.models.Book;
import edu.ablazebookstore.services.BookApiCall;
import edu.ablazebookstore.services.BookCrud;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author JARR
 */
public class test { public static void main(String[] args) {
      
    BookApiCall.gbconnect("9780434009404");
    
}
}