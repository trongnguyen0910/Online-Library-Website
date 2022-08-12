/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.dbhelper.DBUtils;
import fu.ex.entities.Book;
import fu.ex.entities.Publisher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thien Le
 */
public class BookDAO {

    private List<Book> books;

    public BookDAO(){
        try {
          this.books = getAllBook();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public List<Book> findAll(){
         return this.books;
     }
    public Book find(String id) {
        for (Book book : this.books) {
            if (book.getBookId().equalsIgnoreCase(id)) {
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> getAllBook() throws ClassNotFoundException, SQLException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Book> lb = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select BookID, BookName, BookURL, PublisherID, "
                        + "PublishingYear, Price, Quantity "
                        + "From Book";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookId = rs.getString("BookID");
                    String bookName = rs.getString("BookName");
                    String BookURL = rs.getString("BookURL");
                    String publisherId = rs.getString("PublisherID");
                    int publishingYear = rs.getInt("PublishingYear");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    PublisherDAO pdao = new PublisherDAO();
                    Publisher p = pdao.getPublisherByID(publisherId);
                    Book book = new Book(bookId, bookName, BookURL, p, publishingYear, price, quantity);                   
                    lb.add(book);
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
        return lb;
    }

    public boolean update(Book b) throws Exception {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        boolean check = false;              
        String sql = ("UPDATE Book SET Price=?, Quantity=? Where BookID=?");
        try {
            conn = DBUtils.makeConnection();
            if(conn!=null){
            preStm = conn.prepareStatement(sql);            
            preStm.setFloat(1, b.getBookPrice());
            preStm.setInt(2, b.getBookQuantity());
            preStm.setString(3, b.getBookId());
            preStm.executeUpdate();
            check = preStm.executeUpdate() > 0; 
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    // HÀM SEARCH ID AND NAME OF BOOK
     public ArrayList<Book> searchBookByIdAndName(String searchKey){
        List<Book> book = new ArrayList<>(); 
        ArrayList<Book> result = new ArrayList();
        String pattern = ".*" + searchKey.trim().toUpperCase() + ".*";
        book = findAll();   
        for (Book mb : book) {
           if(mb.getBookId().toUpperCase().matches(searchKey.trim()) || mb.getBookName().toUpperCase().matches(pattern)){
               result.add(mb);
           } 
        }
        return result;
    }
    public List<Book> searchBookbyPublishingYear(String publishingYears) throws ClassNotFoundException, SQLException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Book> lb = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select BookID, BookName, BookURL, PublisherID, "
                        + "PublishingYear, Price, Quantity "
                        + "From Book "
                        + "WHERE PublishingYear LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, publishingYears);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookId = rs.getString("BookID");
                    String bookName = rs.getString("BookName");
                    String BookURL = rs.getString("BookURL");
                    String publisherId = rs.getString("PublisherID");
                    int publishingYear = rs.getInt("PublishingYear");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    PublisherDAO pdao = new PublisherDAO();
                    Publisher p = pdao.getPublisherByID(publisherId);
                    Book book = new Book(bookId, bookName, BookURL, p, publishingYear, price, quantity);
                  lb.add(book);
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
        return lb;
    }

    public List<Book> searchBookbyAuthor(String author) throws ClassNotFoundException, SQLException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
         List<Book> lb = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT B.BookID, B.BookName, B.BookURL, B.Price,\n"
                        + " B.Price, B.PublisherID, B.PublishingYear, B.Quantity\n"
                        + "FROM  Book B, BookAuthor T, Author A\n"
                        + "WHERE B.BookID= T.BookID and T.AuthorID=A.AuthorID and A.AuthorName LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1,"%" + author + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookId = rs.getString("BookID");
                    String bookName = rs.getString("BookName");
                    String BookURL = rs.getString("BookURL");
                    String publisherId = rs.getString("PublisherID");
                    int publishingYear = rs.getInt("PublishingYear");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    PublisherDAO pdao = new PublisherDAO();
                    Publisher p = pdao.getPublisherByID(publisherId);
                    Book book = new Book(bookId, bookName, BookURL, p, publishingYear, price, quantity);
                    lb.add(book);
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
        return lb;
    }

    public List<Book> searchBookbyPublisherID(String publisherID) throws ClassNotFoundException, SQLException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
         List<Book> lb = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT B.BookID, B.BookName, B.BookURL, B.Price,\n"
                        + " B.Price, B.PublisherID, B.PublishingYear, B.Quantity\n"
                        + "FROM  Book B, Publisher P\n"
                        + "WHERE B.PublisherID=P.PublisherID and P.PublisherName LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1,"%" + publisherID + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookId = rs.getString("BookID");
                    String bookName = rs.getString("BookName");
                    String BookURL = rs.getString("BookURL");
                    String publisherId = rs.getString("PublisherID");
                    int publishingYear = rs.getInt("PublishingYear");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    PublisherDAO pdao = new PublisherDAO();
                    Publisher p = pdao.getPublisherByID(publisherId);
                    Book book = new Book(bookId, bookName, BookURL, p, publishingYear, price, quantity);
                    lb.add(book);
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
        return lb;
    }

    public boolean createNewBook(Book b) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Book "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, b.getBookId());
                stm.setString(2, b.getBookName());
                stm.setString(3, b.getBookURL());
                stm.setString(4, b.getPublisher().getPubId());
                stm.setInt(5, b.getBookPublishYear());
                stm.setFloat(6, b.getBookPrice());
                stm.setInt(7, b.getBookQuantity());
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateBook(String bookId, String bookURL, String publisherId, String publishingYear, float price, int quantity) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "UPDATE Book "
                        + "Set BookURL = ? , PublisherID = ? , PublishingYear = ? , "
                        + "Price = ? , Quantity = ? "
                        + "WHERE BookID LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, bookURL);
                stm.setString(2, publisherId);
                stm.setString(3, publishingYear);
                stm.setFloat(4, price);
                stm.setInt(5, quantity);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean deleteBook(String bookId)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM Book "
                        + "WHERE BookID LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, bookId);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void searchBookById(String id)
            throws ClassNotFoundException, SQLException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT BookID, BookName, BookURL, PublisherID,"
                        + "PublishingYear, Price, Quantity "
                        + "FROM Book "
                        + "WHERE BookID LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String bookID = rs.getString("BookID");
                    String bookName = rs.getString("BookName");
                    String bookURL = rs.getString("BookURL");
                    String publisherId = rs.getString("PublisherID");
                    int bookPublishYear = rs.getInt("PublishingYear");
                    float bookPrice = rs.getFloat("Price");
                    int bookQuantity = rs.getInt("Quantity");
                    PublisherDAO pdao = new PublisherDAO();
                    Publisher p = pdao.getPublisherByID(publisherId);
                    Book dto = new Book(bookID, bookName, bookURL, p, bookPublishYear, bookPrice, bookQuantity);
                    if (this.books == null) {
                        this.books = new ArrayList<>();
                    }
                    this.books.add(dto);
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
    }

    public void searchBookByName(String name)
            throws ClassNotFoundException, SQLException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT BookID, BookName, BookURL, PublisherID,"
                        + "PublishingYear, Price, Quantity "
                        + "FROM Book "
                        + "WHERE BookName LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                if (rs.next()) {
                    String bookID = rs.getString("BookID");
                    String bookName = rs.getString("BookName");
                    String bookURL = rs.getString("BookURL");
                    String publisherId = rs.getString("PublisherID");
                    int bookPublishYear = rs.getInt("PublishingYear");
                    float bookPrice = rs.getFloat("Price");
                    int bookQuantity = rs.getInt("Quantity");
                    PublisherDAO pdao = new PublisherDAO();
                    Publisher p = pdao.getPublisherByID(publisherId);
                    Book dto = new Book(bookID, bookName, bookURL, p, bookPublishYear, bookPrice, bookQuantity);
                    if (this.books == null) {
                        this.books = new ArrayList<>();
                    }
                    this.books.add(dto);
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
    }

}
