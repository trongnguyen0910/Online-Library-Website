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
import java.time.LocalDate;
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
@WebServlet(name = "CreateServlet", urlPatterns = {"/CreateServlet"})
public class CreateServlet extends HttpServlet {

    private static final String SUCCESS = "ListBookServlet";
    private static final String ERROR = "/WEB-INF/view/error.jsp";
    private static final String INVALID = "CreateFormServlet";

    public CreateServlet() {
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
        if (session.getAttribute("userdata") != null) {
            Member memberLogin = (Member) session.getAttribute("userdata");
            if (memberLogin.getMemberRole() == 1) {
                String url = ERROR;
                try {
                    String id = request.getParameter("txtID");
                    String name = request.getParameter("txtName");
                    String urlBook = request.getParameter("txtURL");
                    String publisherid = request.getParameter("txtPublisher");
                    String year = request.getParameter("txtPublishingYear");
                    String price = request.getParameter("txtPrice");
                    String quantity = request.getParameter("txtQuantity");
                    boolean valid = true;
                    BookErrorObject errorObj = new BookErrorObject();
                    if (id.trim().isEmpty()) {
                        errorObj.setIdError("ID is not supposed to be empty");
                        valid = false;
                    }
                    if (!id.matches("^[a-zA-Z0-9]{1,10}$")) {
                        errorObj.setIdError("ID can only contain letters and numbers, [1-10] characters!");
                        valid = false;
                    }
                    if (name.trim().isEmpty()) {
                        errorObj.setNameError("Name is not supposed to be empty");
                        valid = false;
                    }
                    if (name.trim().length() > 50) {
                        errorObj.setNameError("Name can not exceed 50 characters!");
                        valid = false;
                    }
                    if (!name.matches("^[\\sa-zA-Z0-9\\s]{1,50}$")) {
                        errorObj.setNameError("Name can only contain letters, numbers and spaces, [1-50] characters!");
                        valid = false;
                    }
                    if (urlBook.trim().isEmpty()) {
                        errorObj.setUrlError("Image URL is not supposed to be empty!");
                        valid = false;
                    }
                    if (urlBook.trim().length() > 20) {
                        errorObj.setUrlError("Image URL can not exceed 20 characters!");
                        valid = false;
                    }
                    if (year.trim().equals("")) {
                        errorObj.setPublishingYearError("Year is not supposed to be empty!");
                        year = "0";
                        valid = false;
                    }
                    if (!year.trim().equals("")) {
                        LocalDate localDate = LocalDate.now();
                        int yearNow = localDate.getYear();
                        if (Integer.parseInt(year) < 0 || Integer.parseInt(year) > yearNow) {
                            errorObj.setPublishingYearError("Year can not exceed the current year and cannot be negative!");
                            valid = false;
                        }
                    }
                    if (price.trim().equals("")) {
                        errorObj.setPriceError("Price is not supposed to be empty!");
                        price = "0";
                        valid = false;
                    }
                    if (!price.trim().equals("")) {
                        if (Float.parseFloat(price) < 0 || Float.parseFloat(price) > 100000) {
                            errorObj.setPriceError("Price must be in range [0, 100000]");
                            valid = false;
                        }
                    }
                    if (quantity.trim().equals("")) {
                        errorObj.setQuantityError("Quantity is not supposed to be empty!");
                        quantity = "0";
                        valid = false;
                    }
                    if (!quantity.trim().equals("")) {
                        if (Integer.parseInt(quantity) < 0 || Integer.parseInt(quantity) > 20) {
                            errorObj.setQuantityError("Quantity must be in range [0, 20]");
                        }
                    }
                    BookDAO dao = new BookDAO();
                    if (dao.find(id) != null) {
                        errorObj.setIdError("ID is existed");
                        valid = false;
                    }
                    PublisherDAO pdao = new PublisherDAO();
                    Publisher p = pdao.getPublisherByID(publisherid);
                    Book book = new Book(id.trim(), name.trim(), urlBook.trim(), p, Integer.parseInt(year), Float.parseFloat(price), Integer.parseInt(quantity));
                    if (valid) {
                        if (dao.createNewBook(book)) {
                            url = SUCCESS;
                        } else {
                            request.setAttribute("errMessage", "Add failed");
                        }
                    } else {
                        url = INVALID;
                        request.setAttribute("INVALID", errorObj);
                        request.setAttribute("bookObj", book);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
