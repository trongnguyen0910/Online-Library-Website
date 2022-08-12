/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.dbhelper.DBUtils;
import fu.ex.entities.Member;
import fu.ex.entities.ReturnCard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ReturnCardDAO {
    private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    public ReturnCardDAO(){       
    }
    public ReturnCard find(String id) throws Exception{
        for(ReturnCard card: getAllReturnCard()){
            if(card.getReCardId().equalsIgnoreCase(id)){
                return card;                
            }
        }
        return null;
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
    
    public List<ReturnCard> getAllReturnCardByMemberID(String mid) throws Exception{
     List<ReturnCard> result = null;
        try {
            con = DBUtils.makeConnection();
            if(con!=null){
            String sql = "Select * From ReturnCard Where UserID=?";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, mid);
            rs = preStm.executeQuery();
            if(rs.next()){
                String returnCardId = rs.getString("ReturnCardID");
                String userId = rs.getString("UserID");                
                String getDate = rs.getString("ReturnDate");
                LocalDate returnDate =  LocalDate.parse(getDate);
                MemberDAO mdao = new MemberDAO();
                Member m = mdao.find(userId);
                ReturnCard rc = new ReturnCard(returnCardId, m, returnDate);
                result.add(rc);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    public ArrayList<ReturnCard> getAllReturnCard() throws SQLException, Exception {
        ArrayList<ReturnCard> result = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
             if(con != null) {
                String sql = "Select * From ReturnCard";
                preStm = con.prepareStatement(sql);
                rs = preStm.executeQuery();
                while (rs.next()) {
                String returnCardId = rs.getString("ReturnCardID");
                String userId = rs.getString("UserID");                
                String getDate = rs.getString("ReturnDate");
                LocalDate returnDate =  LocalDate.parse(getDate);
                MemberDAO mdao = new MemberDAO();
                Member m = mdao.find(userId);
                ReturnCard rc = new ReturnCard(returnCardId, m, returnDate);
                result.add(rc);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    public ReturnCard getReturnCardByID(String ccid) throws Exception{
     ReturnCard result = null;
        try {
            con = DBUtils.makeConnection();
            if(con!=null){
            String sql = "Select * From ReturnCard Where ReturnCardID=?";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, ccid);
            rs = preStm.executeQuery();
            if(rs.next()){
                String returnCardId = rs.getString("ReturnCardID");
                String userId = rs.getString("UserID");                
                String getDate = rs.getString("ReturnDate");
                LocalDate returnDate =  LocalDate.parse(getDate);
                MemberDAO mdao = new MemberDAO();
                Member m = mdao.find(userId);
                result = new ReturnCard(returnCardId, m, returnDate);          
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean createNewReturnCard(ReturnCard rc) throws SQLException, Exception {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO ReturnCard "
                        + "VALUES (?, ?, ?)";
                preStm = con.prepareStatement(sql);
                preStm.setString(1, rc.getReCardId());
                preStm.setString(2, rc.getMember().getMemberId());
                preStm.setString(3, rc.getReturnDate().toString());                
                int row = preStm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
