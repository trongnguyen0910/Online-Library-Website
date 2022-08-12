/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlet;

import fu.ex.daos.BookDAO;
import fu.ex.daos.CallCardDAO;
import fu.ex.daos.CallCardDetailDAO;
import fu.ex.daos.MemberDAO;
import fu.ex.daos.ReturnCardDAO;
import fu.ex.daos.ReturnCardDetailDAO;
import fu.ex.entities.Book;
import fu.ex.entities.CallCardDetail;
import fu.ex.entities.Member;
import fu.ex.entities.ReturnCard;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
@WebServlet(name = "ReturnServlet", urlPatterns = {"/ReturnServlet"})
public class ReturnServlet extends HttpServlet {

    public ReturnServlet() {
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
        if (session.getAttribute("userdata") != null) { // check login
            Member memberLogin = (Member) session.getAttribute("userdata");
            if (memberLogin.getMemberRole() == 1) { // check role
                try {
                    String m = request.getParameter("member");
                    MemberDAO mdao = new MemberDAO();
                    Member member = mdao.find(m);
                    // Save all book confirmed by staff to the list
                    ArrayList<Book> lb = new ArrayList<>();
                    BookDAO bd = new BookDAO();
                    for (int i = 0; i < 7; i++) { // the number of books after confirm always in 1 to 7
                        if (request.getParameter("Book" + i) != null) {
                            Book b = bd.find(request.getParameter("Book" + i));
                            lb.add(b);
                        }
                    }
                    CallCardDAO ccdao = new CallCardDAO();
                    CallCardDetailDAO ccddao = new CallCardDetailDAO();
                    List<CallCardDetail> lccd = ccddao.getAllBorrowedDetailByMemberID(m); // get all books not return of this member
                    ArrayList<Integer> listTime = new ArrayList<>();
                    for (CallCardDetail c : lccd) { 
                        for (Book book : lb) { // check books in history and books after confirm by staff to count the late time 
                            if (c.getBook().getBookId().equalsIgnoreCase(book.getBookId())) {
                                long lateDay = ChronoUnit.DAYS.between(c.getCallcard().getCallCardBorrowDate(), java.time.LocalDate.now()) - 30;
                                if (lateDay >= 0) {
                                    listTime.add((int) lateDay);
                                } else {
                                    listTime.add(0);
                                }
                            }
                        }
                    }
                    // Save info after confirm to DB
                    ReturnCardDAO rcdao = new ReturnCardDAO();
                    ReturnCardDetailDAO rccdao = new ReturnCardDetailDAO();
                    // Make new ID for ReturnCard by random
                    String makeNewID;
                    do {
                        makeNewID = "";
                        Random generator = new Random();
                        for (int j = 0; j < 10; j++) {
                            int a = generator.nextInt() % 10;
                            if (a < 0) {
                                a = -a;
                            }
                            makeNewID = makeNewID.concat(Integer.toString(a));
                        }
                    } while (rcdao.getReturnCardByID(makeNewID) != null); // if duplicate => create new ID again
                    ReturnCard newrc = new ReturnCard(makeNewID, member, java.time.LocalDate.now());
                    rcdao.createNewReturnCard(newrc);// Add to table ReturnCard first
                    for (int i = 0; i < lb.size(); i++) { // Add each info to table ReturnCardDetail with the ReturnCard above
                        rccdao.addReturnCardDetail(makeNewID, lb.get(i).getBookId(), listTime.get(i) * 5000, listTime.get(i));                   
                        for (CallCardDetail c : lccd) {
                            if (c.getBook().getBookId().equalsIgnoreCase(lb.get(i).getBookId())) {
                                ccddao.update(c);// update status in table CallCardDetail from 0 (Not return) to 1 (Returned)
                            }
                        }
                        // Set book quantity in web after return by plus 1
                        Book bookUpdate = lb.get(i);
                        bookUpdate.setBookQuantity(bookUpdate.getBookQuantity() + 1);
                        BookDAO bdao = new BookDAO();
                        bdao.update(bookUpdate);
                    }
                    response.sendRedirect("ListBookServlet");
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
