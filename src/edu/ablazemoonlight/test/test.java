/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazemoonlight.test;

import edu.ablazebookstore.controllers.BookController;

/**
 *
 * @author JARR
 */
public class test { public static void main(String[] args) {
        MyConnection mc = MyConnection.getInstance();
            BookController bc = new BookController();
           //c.deleteBook(1);   //
       
            System.out.println(bc.listBooks());
}
}