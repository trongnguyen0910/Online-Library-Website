/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlet;

import fu.ex.daos.BookDAO;
import fu.ex.daos.CallCardDAO;
import fu.ex.daos.CallCardDetailDAO;
import fu.ex.entities.Book;
import fu.ex.entities.CallCard;
import fu.ex.entities.CallCardDetail;
import fu.ex.entities.Member;
import fu.ex.entities.ShoppingCartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "BorrowServlet", urlPatterns = {"/BorrowServlet"})
public class BorrowServlet extends HttpServlet {

    public BorrowServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session==null){
               request.setAttribute("errormessage", "Please login!");
               request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response); 
            }
        if (session.getAttribute("userdata") != null) {  // Check login
            Member memberLogin = (Member) session.getAttribute("userdata");
            if (memberLogin.getMemberRole() == 0) { // Check role
                try {
                    // Get all items in cart after borrow
                    List<ShoppingCartItem> listCart = (List<ShoppingCartItem>) request.getSession().getAttribute("cart");
                    // Check number of books in cart. If more than 7 => cancel borrow action                    
                    if (listCart.size() > 7) {
                        request.setAttribute("errorBorrow", "Maximum 7!");
                        request.getRequestDispatcher("CartServlet").forward(request, response);
                    }
                    // if cart has one book same BookID with the previous book that member has been borrowed but has not returned => cancel borrow action   
                    Member m = (Member) request.getSession().getAttribute("userdata");
                    CallCardDAO ccdao = new CallCardDAO();
                    CallCardDetailDAO ccddao = new CallCardDetailDAO();
                    List<CallCardDetail> lccd = ccddao.getAllBorrowedDetailByMemberID(m.getMemberId()); // get all history borrow of this member
                    int count = 0;
                    for (ShoppingCartItem listBookInCart : listCart) {
                        for (CallCardDetail callCardDetail : lccd) { // check book in cart that same ID with the previous book 
                                                                     // that member has been borrowed but has not returned by 2 loop nested
                            if (listBookInCart.getBook().getBookId().equalsIgnoreCase(callCardDetail.getBook().getBookId())) {
                                count++; // count increasing if found. 
                            }
                        }
                    }
                    if (count > 0) { // if count >0 => cancel borrow action   
                        request.setAttribute("errorBorrow", "Your cart has the book same the book that you borrowed at the previous time without return!");
                        request.getRequestDispatcher("CartServlet").forward(request, response);

                    } else if (lccd.size() == 7) { // if this member borrowed max 7 books at the previous time => cancel borrow action         
                        request.setAttribute("errorBorrow", "You can not borrow more books because max 7!");
                        request.getRequestDispatcher("CartServlet").forward(request, response);

                    } else if (listCart.size() + lccd.size() > 7) { // if book borrowed + book will borrow > 7 => cancel borrow action
                        int canBorrow = 7 - lccd.size();
                        request.setAttribute("errorBorrow", "You can only borrow more " + canBorrow + " books!");
                        request.getRequestDispatcher("CartServlet").forward(request, response);
                    } else {
                        // Create new ID for CallCard by random.
                        String makeNewID;
                        do {
                            makeNewID = "";
                            Random generator = new Random();
                            for (int i = 0; i < 10; i++) {
                                int a = generator.nextInt() % 10; // save the result after random. % 10 to get 1 digit
                                if (a < 0) {  // ID can not negative, if the result negative => change it to the positive number
                                    a = -a;
                                }
                                makeNewID = makeNewID.concat(Integer.toString(a)); // combination each result to get 1 string with 10 digits
                            }
                        } while (ccdao.getCallCardByID(makeNewID) != null); // if duplicate => create new ID again
                        // Create new CallCard and insert to the CallCard table in sql
                        CallCard newcc = new CallCard(makeNewID, m, java.time.LocalDate.now());
                        ccdao.createNewCallCard(newcc);
                        // Add each item borrow after check all with the previous condition into the CallCardDetail table in sql
                        for (ShoppingCartItem l : listCart) {
                            ccddao.addCallCardDetail(newcc.getCallCardId(), l.getBook().getBookId());// add to the CallCardDetail table in sql
                            Book bookUpdate = l.getBook();
                            bookUpdate.setBookQuantity(l.getBook().getBookQuantity() - 1); // decrease quantity of the book borrow in web by 1
                            BookDAO bdao = new BookDAO();
                            bdao.update(bookUpdate); // update quantity of book in web after set up above
                        }
                        request.setAttribute("errorBorrow", "Borrow successful.");
                        request.getRequestDispatcher("CartServlet").forward(request, response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("errormessage", "Incorrect Role!");
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errormessage", "Please login!");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
