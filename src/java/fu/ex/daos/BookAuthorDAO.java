/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.dbhelper.DBUtils;
import fu.ex.entities.Author;
import fu.ex.entities.Book;
import fu.ex.entities.BookAuthor;
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
public class BookAuthorDAO {
    private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    public BookAuthorDAO(){       
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
    
    public List<BookAuthor> getBookAuthorByBookID(String BookId) throws Exception{
     List<BookAuthor> result = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if(con!=null){
            String sql = "Select * From BookAuthor Where BookID=?";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, BookId);
            rs = preStm.executeQuery();
            while(rs.next()){
                String aId = rs.getString("AuthorID");
                String bId = rs.getString("BookID");     
                BookDAO bdao = new BookDAO();
                Book b = bdao.find(bId);
                AuthorDAO adao = new AuthorDAO();
                Author a = adao.getAuthorByID(aId);
                BookAuthor ba = new BookAuthor(b, a);               
                result.add(ba);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<BookAuthor> getAllBookAuthors() throws Exception{
        List<BookAuthor> result = null;
        try {
           
            con = DBUtils.makeConnection();
            if(con!=null){
                String sql = "Select * From BookAuthor";          
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery(); 
            result = new ArrayList<>();
            while (rs.next()){
                String aId = rs.getString("AuthorID");
                String bId = rs.getString("BookID");     
                BookDAO bdao = new BookDAO();
                Book b = bdao.find(bId);
                AuthorDAO adao = new AuthorDAO();
                Author a = adao.getAuthorByID(aId);
                BookAuthor ba = new BookAuthor(b, a);
                result.add(ba);
               }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    public boolean addAuthorForBook(String authorID, String bookID) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO BookAuthor VALUES (?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, authorID);
                stm.setString(2, bookID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }
     
    public BookAuthor checkAuthorOfBook(String authorID, String bookID) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        BookAuthor ba = null;
        try{
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT AuthorID, BookID FROM BookAuthor WHERE AuthorID=? AND BookID=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, authorID);
                stm.setString(2, bookID);
                rs= stm.executeQuery();
                if (rs.next()) {
                    String bid = rs.getString("BookID");
                    String aid = rs.getString("AuthorID");
                    BookDAO bdao = new BookDAO();
                    Book book = bdao.find(bid);
                    AuthorDAO adao = new AuthorDAO();
                    Author author = adao.getAuthorByID(aid);
                    ba = new BookAuthor(book, author);                    
                 }
            }
        } finally {
           closeConnection();
        }
        return ba;
    }
    public boolean deleteAuthorOfBook(String bookId)
            throws SQLException, Exception {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM BookAuthor "
                        + "WHERE BookID LIKE ? ";
                preStm = con.prepareStatement(sql);
                preStm.setString(1, bookId);
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
