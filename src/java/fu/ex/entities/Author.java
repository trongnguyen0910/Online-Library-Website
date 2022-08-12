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
public class Author implements Serializable{
    private String auId;
    private String auName;
    public Author(){
        auId="";
        auName="";
    }

    public Author(String auId, String auName) {
        this.auId = auId;
        this.auName = auName;
    }

    public String getAuId() {
        return auId;
    }

    public void setAuId(String auId) {
        this.auId = auId;
    }

    public String getAuName() {
        return auName;
    }

    public void setAuName(String auName) {
        this.auName = auName;
    }
    
}
