/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlet;

import fu.ex.daos.BookDAO;
import fu.ex.daos.PublisherDAO;
import fu.ex.entities.Book;
import fu.ex.entities.Member;
import fu.ex.entities.Publisher;
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
@WebServlet(name = "UpdateFormServlet", urlPatterns = {"/UpdateFormServlet"})
public class UpdateFormServlet extends HttpServlet {
    public UpdateFormServlet(){
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
        if(session.getAttribute("userdata")!=null){  // check login
            Member member= (Member)session.getAttribute("userdata");
            if(member.getMemberRole()==1){ // check role
          try {
          String bId = request.getParameter("bid");   
          if(bId!=null){                               
          BookDAO bdao = new BookDAO();
          Book b = bdao.find(bId); // find book update
          request.setAttribute("action","update");
          PublisherDAO pdao = new PublisherDAO();
          List<Publisher> listpub = pdao.getAllPublishers();// get all Publishers
          request.setAttribute("pubs", listpub);
          request.setAttribute("bookObj", b);
          request.getRequestDispatcher("/WEB-INF/view/form.jsp").forward(request, response);
          }
          else if(request.getAttribute("bookObj")!=null){ // in the situation update fail, this else help keep the date show on the input tag
          BookDAO bdao = new BookDAO();
          Book b  = (Book) request.getAttribute("bookObj");
          request.setAttribute("action","update");
          request.setAttribute("bookObj", b); 
          PublisherDAO pdao = new PublisherDAO();
          List<Publisher> listpub = pdao.getAllPublishers();
          request.setAttribute("pubs", listpub);
          request.getRequestDispatcher("/WEB-INF/view/form.jsp").forward(request, response);
          }
        }
        catch(Exception e){
            e.printStackTrace();
            log("ERROR at UpdateFormServlet: " +e.getMessage());           
            }
        }else{
            request.setAttribute("errormessage", "Incorrect Role!");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }
        }
        else{
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