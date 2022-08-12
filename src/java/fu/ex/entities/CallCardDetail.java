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
public class CallCardDetail implements Serializable{
    private CallCard callcard;
    private Book book;
    private int status;

    public CallCardDetail() {
        
    }

    public CallCardDetail(CallCard callcard, Book book, int status) {
        this.callcard = callcard;
        this.book = book;
        this.status = status;
    }

    public CallCard getCallcard() {
        return callcard;
    }

    public void setCallcard(CallCard callcard) {
        this.callcard = callcard;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
     
}
