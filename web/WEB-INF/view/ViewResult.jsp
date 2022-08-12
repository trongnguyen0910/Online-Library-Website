<%-- 
    Document   : ViewResult
    Created on : Mar 12, 2022, 2:08:08 PM
    Author     : LENOVO
--%>

<%@page import="fu.ex.entities.Member"%>
<%@page import="fu.ex.entities.CallCardDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            header{
                height: 100px;
                width: 100%;                
            }
            body{
                background-color: rgba(0,191,255,0.3);
            }
            nav{
                background-color: #00BFFF;
                padding: 10px;
                border: 3px solid green;
            }
            nav a{
                text-decoration: none;
                font-size: 20px;
                font-weight: bold;
                margin-left: 30%;
                color: white;
            }          
            .form{
                display: inline-block;
                margin-left: 25%;
                padding: 20px;
                border: 3px solid black;
            }
            td{
                font-size: 20px;
                font-weight: bold;
            }
            .button{  
                    background-color: rgba(0,0,255,0.8);  
                    width: 200px;
                    height: 40px;
                    margin-left: 150px;
                    color: white;
                    font-size: 20px;
                    font-weight: bold;
                    margin-bottom: 20px;
                    margin-top: 20px;
                    margin-left: 86%
                }
   
            .info{
                text-align: center;
                display: block;             
                font-size: 20px;
                font-weight: bold;
                padding: 15px;
                border: 3px solid black;
                background-color: rgba(0,191,255,0.3);
                margin-bottom: 50px;
            }
            th{
                font-size: 25px;
                font-weight: bold;
                background-color: rgba(0,191,255,0.3);
            }
            .notice{
                font-size: 30px;
                font-weight: bold;
                margin-left: 37%;
            }
        </style>      
        <title>Result Page</title>
    </head>
    <body>
        <header>
            <nav>
                <a href="ListBookServlet">Home</a>
                <a href="LogoutServlet">Log out</a>
            </nav>
        </header>
        <% 
             ArrayList<CallCardDetail> lccd = new ArrayList<CallCardDetail>();
             lccd = (ArrayList<CallCardDetail>) request.getAttribute("result");
             Member memberBorrow = (Member) request.getAttribute("memberBorrow");
             String errorMember = (String) request.getAttribute("errorMember");
             if(lccd!=null){
        %>

            <div class="info">
        <p>Member ID: <%= memberBorrow.getMemberId() %></p>
        <p>Member Name: <%= memberBorrow.getMemberFullName() %></p>
        <p>Member Phone: <%= memberBorrow.getMemberPhone() %></p>
        <p>Member Address: <%= memberBorrow.getMemberAddress() %></p>
        </div>
        <%if(!lccd.isEmpty()){  %>
        <div>
        <form action="ReturnFormServlet">
        <table cellpadding="0" cellspacing="0" border="1" align="center" width="100%">
                <tr>
                    <th>Call Card ID</th>
                    <th>Book ID</th>
                    <th>Book Name</th>
                    <th>Borrow Date</th>
                    <th>Is Return?</th>
                    <th>Return check</th>
                </tr> 
        <%  int i = 0;
            for (CallCardDetail dt : lccd){                
              %>
              <tr>
                  <td align="center"><%=dt.getCallcard().getCallCardId() %></td>
                  <td align="center"><%=dt.getBook().getBookId()%></td>
                  <td align="center"><%=dt.getBook().getBookName() %></td>
                  <td align="center"><%=dt.getCallcard().getCallCardBorrowDate() %></td>
                  <td align="center"><%if(dt.getStatus()==0){out.print("Not Return");}else{out.print("Returned");}  %></td>                                                  
                  <td align="center">
                       <input type="checkbox" name="Book<%=i%>" value="<%= dt.getBook().getBookId()%>" />
                </td>
              </tr>
       <%  i++ ;  } %>         
            </table>
             <input type="hidden" name="member" value="<%=memberBorrow.getMemberId()%>">
            <input class="button" type="submit" value="Return Checked"/>
            </form>
            <% } }%>
            
            <% if (lccd != null && memberBorrow != null) {
                    if (lccd.isEmpty()) {
            %>            
            <font class="notice" color="red"><%= "This user does not borrow book now "%></font>
            <% }
                }%>

            <%  if (errorMember != null) {%>
            <font class="notice" color="red"><%= errorMember%></font>
            <% }%>
            </div>
    </body>
</html>
