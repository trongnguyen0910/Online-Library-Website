/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.dbhelper.DBUtils;
import fu.ex.entities.Publisher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class PublisherDAO {
    private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    public PublisherDAO(){       
    }
    
    private void closeConnection() throws Exception{
        if(rs!=null){
            rs.close();
        }
        if(preStm!=null){
            preStm.close();
        }
        if(con!=null){
            con.close();
        }
    }
    
    public Publisher getPublisherByID(String id) throws Exception{
     Publisher result = null;
        try {
            con = DBUtils.makeConnection();
            if(con!=null){
            String sql = "Select * From Publisher Where PublisherID=?";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if(rs.next()){
                String pid = rs.getString("PublisherID");
                String pname = rs.getString("PublisherName");             
                result = new Publisher(pid, pname);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<Publisher> getAllPublishers() throws Exception{
        List<Publisher> result = null;
        try {
           
            con = DBUtils.makeConnection();
            if(con!=null){
                String sql = "Select * From Publisher";          
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery(); 
            result = new ArrayList<>();
            while (rs.next()){
                String pid = rs.getString("PublisherID");
                String pname = rs.getString("PublisherName");     
                Publisher p = new Publisher(pid, pname);
                result.add(p);
               }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
