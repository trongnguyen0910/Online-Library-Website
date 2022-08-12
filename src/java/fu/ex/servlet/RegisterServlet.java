/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlet;

import fu.ex.daos.MemberDAO;
import fu.ex.entities.Member;
import fu.ex.entities.MemberErrorObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    private static final String SUCCESS = "LoginServlet";
    private static final String ERROR = "/WEB-INF/view/error.jsp";
    private static final String INVALID = "RegisterFormServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            String id = request.getParameter("id");
            String pass = request.getParameter("password");
            String fullName = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            
            boolean valid = true;
            MemberErrorObject errorObj = new MemberErrorObject();
            if(id.trim().isEmpty()){
                errorObj.setIdError("ID is not supposed to be empty!");
                valid = false;
            }
            else if(!id.matches("^[a-zA-Z0-9]{3,20}$")){
                errorObj.setIdError("ID can only contain letters and numbers, [3-20] characters!");
                valid = false;
            }
            if (pass.trim().isEmpty()) {
                errorObj.setPasswordError("Password is not supposed to be empty!");
                valid = false;
            }
            else if (!pass.trim().matches("^[a-zA-Z0-9]{3,20}$")) {
                errorObj.setPasswordError("Password can only contain letters and numbers, [3-20] characters!");
                valid = false;
            }
            if(fullName.trim().isEmpty()){
                errorObj.setFullnameError("Your name is not supposed to be empty!");
                valid=false;
            }

            else if(!fullName.matches("^[\\sa-zA-Z\\s]+$") || fullName.trim().length()>30){
                errorObj.setFullnameError("Your name can only contain letters, [1-30] characters!");
                valid=false;
            }
            if (phone.trim().isEmpty()) {
                errorObj.setPhoneError("Phone number is not supposed to be empty!");
                valid = false;
            }
            else if (!phone.matches("[0-9]{10}")) {
                errorObj.setPhoneError("Phone number must be numerical, 10 digits");
                valid = false;
            }
            if (address.trim().isEmpty()) {
                errorObj.setAddressError("Address is not supposed to be empty!");
                valid = false;
            }
//            else if (address.length()>50) {
//                errorObj.setAddressError("Address cannot exceed 50 characters!");
//                valid = false;
//            }
            else if (!address.matches("^[\\sa-zA-Z0-9\\s]{1,50}$") || address.length()>50 ) {
                errorObj.setAddressError("Address can only contain letters, numbers and spaces, [1-50] characters!");
                valid = false;
            }
            MemberDAO mdao = new MemberDAO();
            if (mdao.findMemberById(id)) {
                errorObj.setIdError("ID is existed");
                valid = false;
            }
            if (mdao.findMemberByPhone(phone)) {
                errorObj.setPhoneError("Phone number is existed");
                valid = false;
            }
            Member m = new Member(id.trim(), pass.trim(), fullName.trim(), address.trim(), phone.trim(), 0);
            if (valid) {
                if (mdao.register(m)) {
                    url = SUCCESS;
                    request.setAttribute("success", "Register Successful. Please log in.");
                } else {
                    request.setAttribute("errMessage", "Register failed");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
                request.setAttribute("mObj", m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
