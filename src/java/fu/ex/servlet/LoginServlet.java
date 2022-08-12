package fu.ex.servlet;

import fu.ex.daos.MemberDAO;
import fu.ex.entities.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet (name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();           
            String u = request.getParameter("name");
            String p = request.getParameter("password");            
            MemberDAO dao = new MemberDAO();
            Member user = dao.checkLogin(u, p); // Check member acccount
            if(user != null){  // if true  => member can use web
                session.setAttribute("userdata", user);
                response.sendRedirect("ListBookServlet");
            }
            else{ // login again if fail             
                request.setAttribute("errormessage", "Incorrect ID or Password!");
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE,null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
