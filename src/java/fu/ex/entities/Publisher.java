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
public class Publisher implements Serializable{
     private String pubId;
    private String pubName;
    public Publisher(){
        pubId="";
        pubName="";
    }

    public Publisher(String pubId, String pubName) {
        this.pubId = pubId;
        this.pubName = pubName;
    }

    public String getPubId() {
        return pubId;
    }

    public void setPubId(String pubId) {
        this.pubId = pubId;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }
    
}
