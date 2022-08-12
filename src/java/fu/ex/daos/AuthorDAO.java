/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.dbhelper.DBUtils;
import fu.ex.entities.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class AuthorDAO {
     private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    public AuthorDAO(){       
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
    
    public Author getAuthorByID(String id) throws Exception{
     Author result = null;
        try {
            con = DBUtils.makeConnection();
            if(con!=null){
            String sql = "Select * From Author Where AuthorID=?";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if(rs.next()){
                String aid = rs.getString("AuthorID");
                String aName = rs.getString("AuthorName");             
                result = new Author(aid, aName);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<Author> getAllAuthors() throws Exception{
        List<Author> result = null;
        try {
           
            con = DBUtils.makeConnection();
            if(con!=null){
                String sql = "Select * From Author";          
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery(); 
            result = new ArrayList<>();
            while (rs.next()){
                String aid = rs.getString("AuthorID");
                String aName = rs.getString("AuthorName");    
                Author a = new Author(aid, aName);
                result.add(a);
               }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
