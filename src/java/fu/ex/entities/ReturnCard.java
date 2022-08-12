/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.entities;

import java.time.LocalDate;


/**
 *
 * @author LENOVO
 */
public class ReturnCard {
    private String reCardId;
    private Member member;
    private LocalDate returnDate;
    public ReturnCard(){
        reCardId="";
        member=null;
        returnDate=null;
    }

    public ReturnCard(String reCardId, Member member, LocalDate returnDate) {
        this.reCardId = reCardId;
        this.member = member;
        this.returnDate = returnDate;
    }

    /**
     * @return the reCardId
     */
    public String getReCardId() {
        return reCardId;
    }

    /**
     * @param reCardId the reCardId to set
     */
    public void setReCardId(String reCardId) {
        this.reCardId = reCardId;
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
     * @return the returnDate
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    
}
