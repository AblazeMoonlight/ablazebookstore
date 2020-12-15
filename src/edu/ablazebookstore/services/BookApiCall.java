/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.services;

import edu.ablazebookstore.gui.DisplayBookController;
import edu.ablazebookstore.models.Book;
import static edu.ablazebookstore.services.BookApiCall.isbn;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author JARR
 */

public class BookApiCall {
    public static String isbn;
    private static HttpURLConnection connection;
    
    static Date parseToDate(String str) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                                        .appendPattern("[dd-MM-uuuu][uuuu]")
                                        .parseDefaulting(ChronoField.MONTH_OF_YEAR, today.getMonthValue())
                                        .parseDefaulting(ChronoField.DAY_OF_MONTH, today.getDayOfMonth())                                       
                                        .toFormatter(Locale.ENGLISH);
        return Date.valueOf(LocalDate.parse(str, formatter));
    }

    public static Book gbconnect(String isbn) {
        BufferedReader reader;
        String line;
        BookApiCall.isbn=isbn;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            return parse(responseContent.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }

    public static Book parse(String responseBody) {
        Book book = new Book();

        System.out.println("Response as String : " + responseBody);
        JSONObject responseObj = new JSONObject(responseBody);
        JSONArray arr = responseObj.getJSONArray("items");
        book.setTitle(arr.getJSONObject(0).getJSONObject("volumeInfo").getString("title"));
        book.setAuthor(responseObj.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("authors").getString(0));

        book.setCategory(responseObj.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("categories").getString(0));
        book.setDescription(responseObj.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("description"));
        book.setPublisher(responseObj.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("publisher"));

        String xx = arr.getJSONObject(0).getJSONObject("volumeInfo").getString("publishedDate");
//     LocalDate date = LocalDate.parse(xx,DateTimeFormatter.BASIC_ISO_DATE);
        if (xx.length() != 4) {
            book.setReleasedate(Date.valueOf(xx));
        } else {
            
                                book.setReleasedate(parseToDate(xx));

        }
        book.setCover(responseObj.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail"));
        book.setIsbn(isbn);
        return book;
    }
}
