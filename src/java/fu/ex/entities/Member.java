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
public class Member {
    private String memberId;
    private String memberPassword;
    private String memberFullName;
    private String memberAddress;
    private String memberPhone;
    private int memberRole;
    public Member(){
        memberId="";
        memberPassword="";
        memberFullName="";
        memberAddress="";
        memberPhone="";
        memberRole=0;
    }

    public Member(String memberId, String memberPassword, String memberFullName, String memberAddress, String memberPhone, int memberRole) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberFullName = memberFullName;
        this.memberAddress = memberAddress;
        this.memberPhone = memberPhone;
        this.memberRole = memberRole;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberFullName() {
        return memberFullName;
    }

    public void setMemberFullName(String memberFullName) {
        this.memberFullName = memberFullName;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public int getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(int memberRole) {
        this.memberRole = memberRole;
    }
    
}
