/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.dbhelper.DBUtils;
import fu.ex.entities.Book;
import fu.ex.entities.CallCard;
import fu.ex.entities.CallCardDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class CallCardDetailDAO {
    private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    public CallCardDetailDAO(){       
    }
    public CallCardDetail getBorrowedDetailById(String id) throws Exception{
        for(CallCardDetail d: getAllDetail()){
            if(d.getCallcard().getCallCardId().equalsIgnoreCase(id)){
                return d;
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
    
    public List<CallCardDetail> getAllDetail() throws ClassNotFoundException, SQLException, Exception {
        List<CallCardDetail> result = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * From CallCardDetail";
                preStm = con.prepareStatement(sql);
                rs = preStm.executeQuery();
                while (rs.next()) {
                String callCardId = rs.getString("CallCardID");
                String bookId = rs.getString("BookID"); 
                int status = rs.getInt("Status");
                CallCardDAO cdao = new CallCardDAO();
                CallCard cc = cdao.getCallCardById(callCardId);
                BookDAO bdao = new BookDAO();
                Book b = bdao.find(bookId);
                CallCardDetail ccd = new CallCardDetail(cc, b, status);
                result.add(ccd);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<CallCardDetail> getAllBorrowedDetailByMemberID(String mId) throws Exception{
     List<CallCardDetail> result = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if(con!=null){
            String sql = "select CCD.CallCardID, BookID, Status " +
                        "from Member m inner join CallCard CC on m.MemberID = CC.UserID " +
                        "inner join CallCardDetail CCD on CC.CallCardID = CCD.CallCardID " +
                        "where MemberID LIKE ? and Status = 0";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, mId);
            rs = preStm.executeQuery();
            while(rs.next()){
                String callCardId = rs.getString("CallCardID");
                String bookId = rs.getString("BookID"); 
                int status = rs.getInt("Status");
                CallCardDAO cdao = new CallCardDAO();
                CallCard cc = cdao.getCallCardById(callCardId);
                BookDAO bdao = new BookDAO();
                Book b = bdao.find(bookId);
                CallCardDetail ccd = new CallCardDetail(cc, b, status);
                result.add(ccd);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
     public List<CallCardDetail> History(String mId) throws Exception{
     List<CallCardDetail> result = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if(con!=null){
            String sql = "select CCD.CallCardID, BookID, Status " +
                        "from Member m inner join CallCard CC on m.MemberID = CC.UserID " +
                        "inner join CallCardDetail CCD on CC.CallCardID = CCD.CallCardID " +
                        "where MemberID LIKE ? ";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, mId);
            rs = preStm.executeQuery();
            while(rs.next()){
                String callCardId = rs.getString("CallCardID");
                String bookId = rs.getString("BookID"); 
                int status = rs.getInt("Status");
                CallCardDAO cdao = new CallCardDAO();
                CallCard cc = cdao.getCallCardById(callCardId);
                BookDAO bdao = new BookDAO();
                Book b = bdao.find(bookId);
                CallCardDetail ccd = new CallCardDetail(cc, b, status);
                result.add(ccd);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean addCallCardDetail(String ccId, String bookId) throws SQLException, Exception {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO CallCardDetail "
                        + "VALUES (?, ?, ?)";
                preStm = con.prepareStatement(sql);
                preStm.setString(1, ccId);
                preStm.setString(2, bookId);
                preStm.setInt(3, 0);               
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
    public boolean update(CallCardDetail c) throws Exception {
        boolean check = false;              
        String sql = ("UPDATE CallCardDetail SET Status=? Where CallCardID=? AND BookID=?");
        try {
            con = DBUtils.makeConnection();
            if(con!=null){
            preStm = con.prepareStatement(sql);            
            preStm.setInt(1, 1);
            preStm.setString(2, c.getCallcard().getCallCardId());
            preStm.setString(3, c.getBook().getBookId());
            preStm.executeUpdate();
            check = preStm.executeUpdate() > 0; 
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean deleteCallCardDetailByBookID(String bid)
            throws SQLException, Exception {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM CallCardDetail "
                        + "WHERE BookID LIKE ? ";
                preStm = con.prepareStatement(sql);
                preStm.setString(1, bid);
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
    public List<CallCardDetail> getBookNotReturnByBookID(String bId) throws Exception{
     List<CallCardDetail> result = new ArrayList<>();;
        try {
            con = DBUtils.makeConnection();
            if(con!=null){
            String sql = "Select * From CallCardDetail Where Status=0 and BookID like ?";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, bId);
            rs = preStm.executeQuery();            
            while(rs.next()){
                String callCardId = rs.getString("CallCardID");
                String bookId = rs.getString("BookID"); 
                int status = rs.getInt("Status");
                CallCardDAO cdao = new CallCardDAO();
                CallCard cc = cdao.getCallCardById(callCardId);
                BookDAO bdao = new BookDAO();
                Book b = bdao.find(bookId);
                CallCardDetail ccd = new CallCardDetail(cc, b, status);
                result.add(ccd);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
