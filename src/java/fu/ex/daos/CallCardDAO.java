/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.dbhelper.DBUtils;
import fu.ex.entities.CallCard;
import fu.ex.entities.Member;
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
public class CallCardDAO {
    private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    public CallCardDAO(){       
    }
    public CallCard getCallCardById(String id) throws Exception{
        for(CallCard card: getAllCallCard()){
            if(card.getCallCardId().equalsIgnoreCase(id)){
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
    
    public List<CallCard> getAllCallCardByMemberID(String mid) throws Exception{
     List<CallCard> result = null;
        try {
            con = DBUtils.makeConnection();
            if(con!=null){
            String sql = "Select * From CallCard Where UserID=?";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, mid);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            if(rs.next()){
                String callCardId = rs.getString("CallCardID");
                String userId = rs.getString("UserID");                
                String getDate = rs.getString("BorrowDate");
                LocalDate dateBorrow =  LocalDate.parse(getDate);
                MemberDAO mdao = new MemberDAO();
                Member m = mdao.find(userId);
                CallCard cc = new CallCard(callCardId, m, dateBorrow);
                result.add(cc);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    public ArrayList<CallCard> getAllCallCard() throws SQLException, Exception {
        ArrayList<CallCard> result = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
             if(con != null) {
                String sql = "Select * From CallCard";
                preStm = con.prepareStatement(sql);
                rs = preStm.executeQuery();
                while (rs.next()) {
                String callCardId = rs.getString("CallCardID");
                String userId = rs.getString("UserID"); 
                String getDate = rs.getString("BorrowDate");                
                LocalDate dateBorrow = LocalDate.parse(getDate); 
                MemberDAO mdao = new MemberDAO();
                Member m = mdao.find(userId);
                CallCard cc = new CallCard(callCardId, m, dateBorrow);
                result.add(cc);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    public CallCard getCallCardByID(String ccid) throws Exception{
     CallCard result = null;
        try {
            con = DBUtils.makeConnection();
            if(con!=null){
            String sql = "Select * From CallCard Where CallCardID=?";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, ccid);
            rs = preStm.executeQuery();
            if(rs.next()){
                String callCardId = rs.getString("CallCardID");
                String userId = rs.getString("UserID");                
                String getDate = rs.getString("BorrowDate");
                LocalDate dateBorrow =  LocalDate.parse(getDate);
                MemberDAO mdao = new MemberDAO();
                Member m = mdao.find(userId);
                result = new CallCard(callCardId, m, dateBorrow);                
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean createNewCallCard(CallCard c) throws SQLException, Exception {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO CallCard "
                        + "VALUES (?, ?, ?)";
                preStm = con.prepareStatement(sql);
                preStm.setString(1, c.getCallCardId());
                preStm.setString(2, c.getMember().getMemberId());
                preStm.setString(3, c.getCallCardBorrowDate().toString());                
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
    public boolean deleteCallCard(String ccId)
            throws SQLException, Exception {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM CallCard "
                        + "WHERE CallCardID LIKE ? ";
                preStm = con.prepareStatement(sql);
                preStm.setString(1, ccId);
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
