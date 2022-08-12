/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlet;

import fu.ex.daos.BookDAO;
import fu.ex.daos.PublisherDAO;
import fu.ex.entities.Book;
import fu.ex.entities.BookErrorObject;
import fu.ex.entities.Member;
import fu.ex.entities.Publisher;
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
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    private static final String SUCCESS = "ListBookServlet";
    private static final String ERROR = "/WEB-INF/view/error.jsp";
    private static final String INVALID = "UpdateFormServlet";

    public UpdateServlet() {
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
                String url = ERROR;
                try {
                    String bookId = request.getParameter("txtID");
                    String bookName = request.getParameter("txtName");
                    String bookURL = request.getParameter("txtURL");
                    String bookPublisher = request.getParameter("txtPublisher");
                    String bookPublishingYear = request.getParameter("txtPublishingYear");
                    String bookPrice = request.getParameter("txtPrice");
                    String bookQuantity = request.getParameter("txtQuantity");

                    boolean valid = true;
                    BookErrorObject errorObj = new BookErrorObject();
                    if(bookPrice.trim().equals("")){
                     errorObj.setPriceError("Price is not supposed to be empty!");
                     bookPrice="0";
                     valid = false;
                    }
                    if(!bookPrice.trim().equals("")){
                        if(Float.parseFloat(bookPrice) < 0 ||  Float.parseFloat(bookPrice) > 100000){
                            errorObj.setPriceError("Price must be in range [0, 100000]");
                            valid = false;
                        }
                    }
                    if(bookQuantity.trim().equals("")){                        
                     errorObj.setQuantityError("Quantity is not supposed to be empty!");
                     bookQuantity="0";
                     valid = false;
                    }
                    if(!bookQuantity.trim().equals("")){
                    if(Integer.parseInt(bookQuantity)<0 || Integer.parseInt(bookQuantity) > 20){
                        errorObj.setQuantityError("Quantity must be in range [0, 20]");
                    }
                    }
                    PublisherDAO pdao = new PublisherDAO();
                    Publisher p = pdao.getPublisherByID(bookPublisher);
                    BookDAO dao = new BookDAO();
                    Book bookObj = new Book(bookId, bookName, bookURL, p, Integer.parseInt(bookPublishingYear), Float.parseFloat(bookPrice), Integer.parseInt(bookQuantity));
                    if (valid) {
                        if (dao.update(bookObj)) {
                            url = SUCCESS;
                        } else {
                            request.setAttribute("errMessage", "Update failed");
                        }
                    } else {
                        url = INVALID;
                        request.setAttribute("INVALID", errorObj);
                        request.setAttribute("bookObj", bookObj);
                    }
                } catch (Exception e) {
                    log("ERROR at UpdateServlet: " + e.getMessage());
                } finally {
                    request.getRequestDispatcher(url).forward(request, response);
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
