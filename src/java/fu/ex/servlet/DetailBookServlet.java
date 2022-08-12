/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlet;

import fu.ex.daos.BookAuthorDAO;
import fu.ex.daos.BookDAO;
import fu.ex.entities.Book;
import fu.ex.entities.BookAuthor;
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
@WebServlet(name = "DetailBookServlet", urlPatterns = {"/DetailBookServlet"})
public class DetailBookServlet extends HttpServlet {

    public DetailBookServlet() {
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
        if (session.getAttribute("userdata") != null) {
            Member member = (Member) session.getAttribute("userdata");
            if (member.getMemberRole() == 0) {
                try {
                    String bid = request.getParameter("bid");
                    BookDAO bdao = new BookDAO();
                    Book b = bdao.find(bid);
                    BookAuthorDAO badao = new BookAuthorDAO();
                    List<BookAuthor> lba = badao.getBookAuthorByBookID(bid); // get list book with author
                    request.setAttribute("bookDetail", b);
                    request.setAttribute("authors", lba);
                    request.getRequestDispatcher("/WEB-INF/view/BookDetail.jsp").forward(request, response);
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
