<%-- 
    Document   : ReturnForm
    Created on : Mar 12, 2022, 3:14:23 PM
    Author     : LENOVO
--%>

<%@page import="fu.ex.entities.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fu.ex.entities.Book"%>
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
        <title>Return Form</title>
    </head>
    <body>
        <header>
            <nav>
                <a href="ListBookServlet">Home</a>
                <a href="LogoutServlet">Log out</a>
            </nav>
        </header>
         <% 
             ArrayList<Book> lb = new ArrayList<>();
             lb = (ArrayList<Book>) request.getAttribute("listBookReturns");
             ArrayList<Integer> listTime = new ArrayList<>();
             listTime = (ArrayList<Integer>) request.getAttribute("listTime");
             Member memberBorrow = (Member) request.getAttribute("mem");            
             if(lb!=null){
        %>
        <div class="info">
        <p>Member ID: <%= memberBorrow.getMemberId() %></p>
        <p>Member Name: <%= memberBorrow.getMemberFullName() %></p>
        <p>Member Phone: <%= memberBorrow.getMemberPhone() %></p>
        <p>Member Address: <%= memberBorrow.getMemberAddress() %></p>
        </div>
        <% if(!lb.isEmpty()){ %>
        <form action="ReturnServlet">
        <table cellpadding="0" cellspacing="0" border="1" align="center" width="100%">
                <tr>
                    <th>Book ID</th>
                    <th>Book Name</th>
                    <th>Cite Money</th>
                    <th>Late Date</th>
                </tr> 
        <%  int i = 0;
            for (Book dt : lb){                
              %>
              <tr>
                  <td align="center"><%=dt.getBookId() %></td>
                  <td align="center"><%=dt.getBookName()%></td>
                  <td align="center"><%=listTime.get(i)*5000%></td>
                  <td align="center"><%= listTime.get(i)%></td>
                  <input type="hidden" name="Book<%=i%>" value="<%=dt.getBookId()%>">
              </tr>
       <%  i++ ;  }   %>         
            </table>
            <input type="hidden" name="member" value="<%=memberBorrow.getMemberId()%>">
            <input class="button" type="submit" value="Confirm" onclick="return confirm('Are you sure?')"/>
            </form>
            <% }} %>
            
            <% if(lb!=null){
                if (lb.isEmpty()) {%>
                <font class="notice" color="red"><%= "Please choose books to return!"%></font>
            <% }
                }%>
    </body>
</html>
