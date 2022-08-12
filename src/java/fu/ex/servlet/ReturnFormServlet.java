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
import fu.ex.entities.CallCardDetail;
import fu.ex.entities.Book;
import fu.ex.entities.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ReturnFormServlet", urlPatterns = {"/ReturnFormServlet"})
public class ReturnFormServlet extends HttpServlet {

    public ReturnFormServlet() {
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
                    String m = request.getParameter("member"); // get the id of member that want to return book 
                    ArrayList<Book> lb = new ArrayList<>();
                    BookDAO bd = new BookDAO();
                    for (int i = 0; i < 7; i++) { // get all book checked to return
                        if (request.getParameter("Book" + i) != null) {
                            Book b = bd.find(request.getParameter("Book" + i));
                            lb.add(b);
                        }
                    }
                    if (lb.size() == 0) {
                    }
                    CallCardDAO ccdao = new CallCardDAO();
                    CallCardDetailDAO ccddao = new CallCardDetailDAO();
                    List<CallCardDetail> lccd = ccddao.getAllBorrowedDetailByMemberID(m);// get all books not return of this member
                    ArrayList<Integer> listTime = new ArrayList<>();
                    for (CallCardDetail c : lccd) {
                        for (Book book : lb) { // check book in history and book checked to return by staff to count the late time 
                            if (c.getBook().getBookId().equalsIgnoreCase(book.getBookId())) {
                                // count the difference between 2 time and minus by 30. Because deadline borrow can in 30 days.
                                long lateDay = ChronoUnit.DAYS.between(c.getCallcard().getCallCardBorrowDate(), java.time.LocalDate.now()) - 30;
                                // if the difference between 2 time exceed 30, minus it to 30 and save to the list with the book corresponding
                                if (lateDay >= 0) {
                                    listTime.add((int) lateDay);
                                } else { // if the difference between 2 time not exceed 30 days, save to list is 0.
                                    listTime.add(0);
                                }
                            }
                        }
                    }
                    MemberDAO mdao = new MemberDAO();
                    Member member = mdao.find(m); // get info of member return book to dispatcher it
                    request.setAttribute("mem", member);
                    request.setAttribute("listBookReturns", lb);
                    request.setAttribute("listTime", listTime);
                    request.getRequestDispatcher("/WEB-INF/view/ReturnForm.jsp").forward(request, response); // this view show for staff to confirm after insert to database
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
        // processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
