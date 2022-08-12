/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlet;

import fu.ex.daos.CallCardDAO;
import fu.ex.daos.CallCardDetailDAO;
import fu.ex.entities.CallCard;
import fu.ex.entities.CallCardDetail;
import fu.ex.entities.Member;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thien Le
 */
@WebServlet(name = "HistoryServlet", urlPatterns = {"/HistoryServlet"})
public class HistoryServlet extends HttpServlet {

    public HistoryServlet() {
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
            if(member.getMemberRole()==0){ // check role
            String url = "";
            try {
                String memberID = request.getParameter("userID");
                CallCardDetailDAO cardDAO = new CallCardDetailDAO();
                List<CallCardDetail> listHistory = cardDAO.History(memberID); // get all history borrow book of this member
                    request.setAttribute("LIST_HISTORY", listHistory);
                    request.getRequestDispatcher("/WEB-INF/view/history.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
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
        // processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
