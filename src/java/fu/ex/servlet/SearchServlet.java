/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlet;

import fu.ex.daos.BookAuthorDAO;
import fu.ex.daos.BookDAO;
import fu.ex.daos.CallCardDAO;
import fu.ex.daos.CallCardDetailDAO;
import fu.ex.daos.MemberDAO;
import fu.ex.entities.Book;
import fu.ex.entities.BookAuthor;
import fu.ex.entities.CallCardDetail;
import fu.ex.entities.Member;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    public SearchServlet() {
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
            try {
                String book1 = request.getParameter("Search");
                String view = request.getParameter("viewBookBorrowByMemberID");
                String txtSearch = request.getParameter("txtSearch");
                String txtSearch1 = request.getParameter("txtSearch1");
                String txtSearch2 = request.getParameter("txtSearch2");

                if (book1 != null) { // search BookID / BookName for staff
                    if (memberLogin.getMemberRole() == 1) {// check role
                        BookDAO bk = new BookDAO();
                        List<Book> bok = bk.searchBookByIdAndName(book1);// function search, return the result with data inputted
                        request.setAttribute("books", bok);
                        BookAuthorDAO baDao = new BookAuthorDAO();
                        List<BookAuthor> baList = baDao.getAllBookAuthors();// get list author+book to show author of the book search on the result screen
                        request.setAttribute("BookAuthorList", baList);
                        request.getRequestDispatcher("/WEB-INF/view/staffview.jsp").forward(request, response);
                    } else {
                        request.setAttribute("errormessage", "Incorrect Role!");
                        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
                    }
                }
                if (view != null) { // search member history borrow book without return
                    if (memberLogin.getMemberRole() == 1) {
                        MemberDAO mdao = new MemberDAO();
                        Member m = mdao.find(view.trim());
                        if (m != null) {
                            CallCardDetailDAO ccddao = new CallCardDetailDAO();
                            List<CallCardDetail> lccd = ccddao.getAllBorrowedDetailByMemberID(m.getMemberId());// get all history of member by memberID from search
                            request.setAttribute("result", lccd);
                            request.setAttribute("memberBorrow", m);
                            request.getRequestDispatcher("/WEB-INF/view/ViewResult.jsp").forward(request, response);
                        } else {
                            request.setAttribute("errorMember", "This member does not exist!");
                            request.getRequestDispatcher("/WEB-INF/view/ViewResult.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("errormessage", "Incorrect Role!");
                        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
                    }
                }

                if (txtSearch != null) { // search book by author
                    if (memberLogin.getMemberRole() == 0) {
                        BookDAO dao = new BookDAO();
                        List<Book> list = dao.searchBookbyAuthor(txtSearch);
                        request.setAttribute("books", list);
                        request.getRequestDispatcher("/WEB-INF/view/userview.jsp").forward(request, response);
                    } else {
                        request.setAttribute("errormessage", "Incorrect Role!");
                        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
                    }
                }
                if (txtSearch1 != null) { // search book by publisher
                    if (memberLogin.getMemberRole() == 0) {
                        BookDAO dao = new BookDAO();
                        List<Book> list1 = dao.searchBookbyPublisherID(txtSearch1);
                        request.setAttribute("books", list1);
                        request.getRequestDispatcher("/WEB-INF/view/userview.jsp").forward(request, response);
                    } else {
                        request.setAttribute("errormessage", "Incorrect Role!");
                        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
                    }
                }
                if (txtSearch2 != null) { // search book by publishing year
                    if (memberLogin.getMemberRole() == 0) {
                        BookDAO dao = new BookDAO();
                        List<Book> list3 = dao.searchBookbyPublishingYear(txtSearch2);
                        request.setAttribute("books", list3);
                        request.getRequestDispatcher("/WEB-INF/view/userview.jsp").forward(request, response);
                    } else {
                        request.setAttribute("errormessage", "Incorrect Role!");
                        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
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
