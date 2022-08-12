/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlet;

import fu.ex.daos.BookAuthorDAO;
import fu.ex.daos.BookDAO;
import fu.ex.daos.CallCardDetailDAO;
import fu.ex.daos.ReturnCardDetailDAO;
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
@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteServlet"})
public class DeleteServlet extends HttpServlet {

    public DeleteServlet() {
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
            Member member= (Member)session.getAttribute("userdata");
            if(member.getMemberRole()==1){ // check role
            try {
                String id = request.getParameter("bid");
                BookDAO bdao = new BookDAO();
                BookAuthorDAO badao = new BookAuthorDAO();
                CallCardDetailDAO ccddao = new CallCardDetailDAO();
                List<CallCardDetail> lccd = ccddao.getBookNotReturnByBookID(id); // get history book that not return (status = 0 )
                ReturnCardDetailDAO rccdao = new ReturnCardDetailDAO();
                if (lccd.isEmpty()) { // if the book is not borrowed => delete this
                    ccddao.deleteCallCardDetailByBookID(id); // delete detail borrow
                    rccdao.deleteReturnCardDetailByBookID(id); // delete detail return
                    badao.deleteAuthorOfBook(id); // delete authors of book
                    bdao.deleteBook(id); // delete book
                    request.getRequestDispatcher("ListBookServlet").forward(request, response);
                } else {
                    request.setAttribute("errorDelete", "This book is currently borrowing by member!");
                    request.getRequestDispatcher("ListBookServlet").forward(request, response);
                }
            } catch (Exception e) {
                log("ERROR at DeleteServlet: " + e.getMessage());
            }
            }else{
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
