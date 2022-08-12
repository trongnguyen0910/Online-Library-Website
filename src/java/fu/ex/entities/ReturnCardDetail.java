/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.entities;

/**
 *
 * @author LENOVO
 */
public class ReturnCardDetail {
    private ReturnCard returncard;
    private Book book;
    private float citeMoney;
    private int lateDate;

    public ReturnCardDetail() {
        
    }

    public ReturnCardDetail(ReturnCard returncard, Book book, float citeMoney, int lateDate) {
        this.returncard = returncard;
        this.book = book;
        this.citeMoney = citeMoney;
        this.lateDate = lateDate;
    }

    /**
     * @return the returncard
     */
    public ReturnCard getReturncard() {
        return returncard;
    }

    /**
     * @param returncard the returncard to set
     */
    public void setReturncard(ReturnCard returncard) {
        this.returncard = returncard;
    }

    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return the citeMoney
     */
    public float getCiteMoney() {
        return citeMoney;
    }

    /**
     * @param citeMoney the citeMoney to set
     */
    public void setCiteMoney(float citeMoney) {
        this.citeMoney = citeMoney;
    }

    /**
     * @return the lateDate
     */
    public int getLateDate() {
        return lateDate;
    }

    /**
     * @param lateDate the lateDate to set
     */
    public void setLateDate(int lateDate) {
        this.lateDate = lateDate;
    }

    
}
