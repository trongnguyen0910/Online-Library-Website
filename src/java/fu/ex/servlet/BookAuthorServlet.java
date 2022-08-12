/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlet;

import fu.ex.daos.BookAuthorDAO;
import fu.ex.daos.BookDAO;
import fu.ex.entities.Author;
import fu.ex.entities.Book;
import fu.ex.entities.BookAuthor;
import fu.ex.entities.BookErrorObject;
import fu.ex.entities.Member;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "BookAuthorServlet", urlPatterns = {"/BookAuthorServlet"})
public class BookAuthorServlet extends HttpServlet {

    public BookAuthorServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            request.setAttribute("errormessage", "Please login!");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
        if (session.getAttribute("userdata") != null) {  // Check for login
            Member memberLogin = (Member) session.getAttribute("userdata");
            if (memberLogin.getMemberRole() == 1) {   // Check for Role
                try {
                    String id = request.getParameter("txtID");
                    String authorid = request.getParameter("txtAuthor");
                    boolean valid = true;
                    BookErrorObject errorObj = new BookErrorObject();
                    BookAuthorDAO daos = new BookAuthorDAO();
                    BookDAO dao = new BookDAO();
                    if (id.trim().isEmpty()) {
                        errorObj.setIdError("ID is not supposed to be empty!");
                        valid = false;
                    }
                    if (!id.trim().isEmpty()) {
                        Book book = dao.find(id.trim());
                        if (book == null) {  // Check book exist or not
                            errorObj.setIdError("This Book does not exist!");
                            valid = false;
                        }
                        BookAuthor ba = daos.checkAuthorOfBook(authorid, id.trim());
                        if (ba != null) { // Check duplicate author
                            valid = false;
                            request.setAttribute("error", "Duplicated Author!");
                        }
                    }
                    if (valid) {   // After all condition ok => Add author for book
                        daos.addAuthorForBook(authorid, id.trim());
                        request.setAttribute("bookID", id);
                        request.setAttribute("successAdd", "Add author for book successfully.");
                    } else {
                        request.setAttribute("INVALID", errorObj);
                        request.setAttribute("bookID", id);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    request.getRequestDispatcher("BookAuthorFormServlet").forward(request, response);
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
