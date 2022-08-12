/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlet;

import fu.ex.daos.BookAuthorDAO;
import fu.ex.daos.BookDAO;
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
 * @author Admin
 */
@WebServlet (name = "ListBookServlet", urlPatterns = {"/ListBookServlet"})
public class ListBookServlet extends HttpServlet {
        public ListBookServlet(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if(session==null){
               request.setAttribute("errormessage", "Please login!");
               request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response); 
            }
            Member m = (Member) session.getAttribute("userdata");
            if(session.getAttribute("userdata")!=null && m.getMemberRole()==0){ // check login + role (member). If success, come the user view
                BookDAO book = new BookDAO();
                request.setAttribute("books", book.getAllBook());    // get all books            
                request.getRequestDispatcher("WEB-INF/view/userview.jsp").forward(request, response);
            } else if(session.getAttribute("userdata")!=null && m.getMemberRole()==1){ // check login + role (staff). If success, come the staff view
                BookDAO book = new BookDAO();
                request.setAttribute("books", book.getAllBook()); // get all books
                BookAuthorDAO baDao = new BookAuthorDAO(); 
                List<BookAuthor> baList = baDao.getAllBookAuthors(); // get all authors
                request.setAttribute("BookAuthorList", baList);
                request.getRequestDispatcher("WEB-INF/view/staffview.jsp").forward(request, response);
            }else{
                 request.setAttribute("errormessage", "Please login!");
                 request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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