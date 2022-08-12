/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlet;

import fu.ex.daos.AuthorDAO;
import fu.ex.entities.Author;
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
@WebServlet(name = "BookAuthorFormServlet", urlPatterns = {"/BookAuthorFormServlet"})
public class BookAuthorFormServlet extends HttpServlet {
        public BookAuthorFormServlet(){
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
        if(session.getAttribute("userdata")!=null){ // Check for login
             Member member= (Member)session.getAttribute("userdata");
            if(member.getMemberRole()==1){    //Check role
        try { 
          AuthorDAO adao = new AuthorDAO();  
          List<Author> listau = adao.getAllAuthors(); // Get list authors
          request.setAttribute("aus", listau);
          request.getRequestDispatcher("/WEB-INF/view/BookAuthorForm.jsp").forward(request, response);
        }
        catch(Exception e){
            e.printStackTrace();
            log("ERROR at CreateFormAuthorServlet: " +e.getMessage());           
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
