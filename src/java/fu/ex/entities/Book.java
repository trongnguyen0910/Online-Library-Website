/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.entities;

import java.io.Serializable;

/**
 *
 * @author LENOVO
 */
public class Book implements Serializable{
    private String bookId;
    private String bookName;
    private String bookURL;
    private Publisher publisher;
    private int bookPublishYear;
    private float bookPrice;
    private int bookQuantity;
    public Book(){
        bookId="";
        bookName="";
        bookURL="";
        publisher=null;
        bookPublishYear=0;
        bookPrice=0;
        bookQuantity=0;
    }

    public Book(String bookId, String bookName, String bookURL, Publisher publisher, int bookPublishYear, float bookPrice, int bookQuantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookURL = bookURL;
        this.publisher = publisher;
        this.bookPublishYear = bookPublishYear;
        this.bookPrice = bookPrice;
        this.bookQuantity = bookQuantity;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookURL() {
        return bookURL;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public int getBookPublishYear() {
        return bookPublishYear;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookURL(String bookURL) {
        this.bookURL = bookURL;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setBookPublishYear(int bookPublishYear) {
        this.bookPublishYear = bookPublishYear;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    
}
