/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.dbhelper.DBUtils;
import fu.ex.entities.Book;
import fu.ex.entities.Member;
import fu.ex.entities.Publisher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class MemberDAO {
    private List<Member> members;
    
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    private void closeConnection() throws Exception{
        if(rs != null){
            rs.close();
        }
        if(pstmt != null){
            pstmt.close();
        }
        if(con != null){
            con.close();
        }
    }
    public Member find(String id) throws Exception{
         for (Member m : getAllMember()) {
             if(m.getMemberId().equalsIgnoreCase(id)){
                 return m;
             }
         }
         return null;
     }

     public Member checkLogin(String username, String password) throws Exception{
        Member acc = null;
        try{
            String sql = "Select MemberID , MemberPassword, FullName, Address, Phone, Role From Member "
                    + "Where MemberID=? And MemberPassword=?";
            con = DBUtils.makeConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                acc = new Member(rs.getString("MemberID"), rs.getString("MemberPassword"), rs.getString("Fullname"), rs.getString("Address"), rs.getString("Phone"), rs.getInt("Role"));
            }
             
        }finally{
            closeConnection();
        }
        return acc;
    }
   public boolean register(Member m) throws SQLException{
       try{
           con = DBUtils.makeConnection();
           if(con != null){
               String sql = "INSERT INTO Member "
                       + "VALUES (?, ?, ?, ?, ?, ?)";
               pstmt = con.prepareStatement(sql);
               pstmt.setString(1, m.getMemberId());
               pstmt.setString(2, m.getMemberPassword());
               pstmt.setString(3, m.getMemberFullName());
               pstmt.setString(4, m.getMemberAddress());
               pstmt.setString(5, m.getMemberPhone());
               pstmt.setFloat(6, m.getMemberRole());           
               int row = pstmt.executeUpdate();
               if(row > 0){
                   return true;
               }
           }
       }finally{
        if(pstmt != null){
            pstmt.close();
        }
        if(con != null){
            con.close();
        }
       }
       return false;
   }
    public boolean findMemberById(String mid) throws SQLException, Exception {
        String sql = "SELECT * FROM Member WHERE MemberID=?";        
        try {
            con = DBUtils.makeConnection(); 
            if (con != null) {
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, mid);
                rs = pstmt.executeQuery();
                if (rs.next()) { 
                    String memberId = rs.getString("MemberID");
                    String password = rs.getString("MemberPassword");                    
                    String memberName = rs.getString("FullName");
                    String memberAddress = rs.getString("Address");
                    String memberPhone = rs.getString("Phone");
                    int memberRole = rs.getInt("Role");
                    Member m = new Member(memberId, memberPhone, memberName, memberAddress, memberPhone, memberRole);
                    if(m!=null){
                        return true;
                    }
                }
            }
        } finally {
        closeConnection();
        }
        return false;
    }
    public ArrayList<Member> getAllMember() throws SQLException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Member> listmem = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM Member";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String memberId = rs.getString("MemberID");
                    String password = rs.getString("MemberPassword");                    
                    String memberName = rs.getString("FullName");
                    String memberAddress = rs.getString("Address");
                    String memberPhone = rs.getString("Phone");
                    int memberRole = rs.getInt("Role");
                    Member mb = new Member(memberId, memberPhone, memberName, memberAddress, memberPhone, memberRole);                   
                    listmem.add(mb);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listmem;
    }

    public boolean findMemberByPhone(String mp) throws NamingException, SQLException {
        String sql = "SELECT * FROM Member WHERE Phone=?"; 
        try {
            con = DBUtils.makeConnection(); 
            if (con != null) {
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, mp);
                rs = pstmt.executeQuery();
                if (rs.next()) { 
                    String memberId = rs.getString("MemberID");
                    String password = rs.getString("MemberPassword");                    
                    String memberName = rs.getString("FullName");
                    String memberAddress = rs.getString("Address");
                    String memberPhone = rs.getString("Phone");
                    int memberRole = rs.getInt("Role");
                    Member mb = new Member(memberId, memberPhone, memberName, memberAddress, memberPhone, memberRole);
                    if(mb!=null){
                      return true;  
                    }
                }
            }
        } finally {
        if(rs != null){
            rs.close();
        }
        if(pstmt != null){
            pstmt.close();
        }
        if(con != null){
            con.close();
        }
        }
        return false;
    }
}

