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
import fu.ex.entities.ReturnCard;
import fu.ex.entities.ReturnCardDetail;
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
public class ReturnCardDetailDAO {
    private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;
   public ReturnCardDetailDAO(){       
    }
    public ReturnCardDetail find(String id) throws Exception{
        for(ReturnCardDetail card: getAllDetail()){
            if(card.getReturncard().getReCardId().equalsIgnoreCase(id)){
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
    public List<ReturnCardDetail> getAllDetail() throws SQLException, Exception {
        List<ReturnCardDetail> result = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * From ReturnCardDetail";
                preStm = con.prepareStatement(sql);
                rs = preStm.executeQuery();
                while (rs.next()) {
                String callCardId = rs.getString("ReturnCardID");
                String bookId = rs.getString("BookID"); 
                float citePrice = rs.getFloat("CitePirce");
                int late = rs.getInt("Late");
                ReturnCardDAO rcdao = new ReturnCardDAO();
                ReturnCard rc = rcdao.getReturnCardByID(bookId);
                BookDAO bdao = new BookDAO();
                Book b = bdao.find(bookId);
                ReturnCardDetail rcd = new ReturnCardDetail(rc, b, citePrice, late);
                result.add(rcd);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean addReturnCardDetail(String rcId, String bookId, float citeMoney, int late) throws SQLException, Exception {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO ReturnCardDetail "
                        + "VALUES (?, ?, ?, ?)";
                preStm = con.prepareStatement(sql);
                preStm.setString(1, rcId);
                preStm.setString(2, bookId);
                preStm.setFloat(3, citeMoney);   
                preStm.setInt(4, late); 
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
    public boolean deleteReturnCardDetailByBookID(String bid)
            throws SQLException, Exception {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM ReturnCardDetail "
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
}
