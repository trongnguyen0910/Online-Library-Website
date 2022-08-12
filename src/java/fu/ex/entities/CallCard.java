/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.entities;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author LENOVO
 */
public class CallCard implements Serializable{
    private String callCardId;
    private Member member;
    private LocalDate callCardBorrowDate;
    public CallCard(){
        callCardId="";
        member = null;
        callCardBorrowDate=null;
    }

    public CallCard(String callCardId, Member member, LocalDate callCardBorrowDate) {
        this.callCardId = callCardId;
        this.member = member;
        this.callCardBorrowDate = callCardBorrowDate;
    }

    /**
     * @return the callCardId
     */
    public String getCallCardId() {
        return callCardId;
    }

    /**
     * @param callCardId the callCardId to set
     */
    public void setCallCardId(String callCardId) {
        this.callCardId = callCardId;
    }

    /**
     * @return the member
     */
    public Member getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * @return the callCardBorrowDate
     */
    public LocalDate getCallCardBorrowDate() {
        return callCardBorrowDate;
    }

    /**
     * @param callCardBorrowDate the callCardBorrowDate to set
     */
    public void setCallCardBorrowDate(LocalDate callCardBorrowDate) {
        this.callCardBorrowDate = callCardBorrowDate;
    }

    
}
