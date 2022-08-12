<%-- 
    Document   : cart
    Created on : Feb 27, 2022, 2:03:54 PM
    Author     : LENOVO
--%>

<%@page import="fu.ex.entities.ShoppingCartItem"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" 
        pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@page import="fu.ex.entities.Book" %>
<%@page import="fu.ex.entities.Member" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>       
        <title>Book Page</title>
        <style type="text/css">
            header{
                height: 50px;
                width: 100%;                
            }
            h1{
                color: black;
                font-weight: bold;
                margin-left: 40%;
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
                margin-left: 22%;
                color: white;
            }
            section{
                display: inline-block;
                width: 70%;
                margin-bottom: 20px;              
            }
            .img{
                text-align: center;
                border: 2px solid rgb(0,191,255);
                padding: 5px;
                margin: 17px;
                height: 250px;
                width: 200px;
                position: relative;
                float: left;
            }
            footer{             
                height: 120px;
                width: 100%;
                padding-top: 5px;
                padding-bottom: 10px;
                background-color: rgb(0,191,255);
                text-align: center;
                border: 3px solid green;
                clear: both;
            }
            .search{               
                display: block;                                            
                padding-left: 15px;
                padding-bottom: 30px;
                width:70%;               
                margin-top: 20px;
            }
            .container{
                width: 28%;               
                float: left;               
            }            
            .info{
                display: inline-block;             
                font-size: 20px;
                font-weight: bold;
                padding: 15px;
                border: 3px solid black;
                margin-top: 10px;
                background-color: rgba(0,191,255,0.3);
            }
            th{
                background-color: #00BFFF;
                font-weight: bold;
                font-size: 25px;
                color: white;
            }
            .each{
                display: inline-block;
                width: 100%;
                margin-bottom: 25px;
                border: 2px solid black;
                padding: 0px 0px 15px 12px;
            }
            .borrow{
                display: inline-block;
                margin-left: 300px;
                margin-bottom: 20px;
                font-size: 25px;
                font-weight: bold;
                color: white;
                background-color: rgb(0,191,255);
            }
            .delete{               
                margin-left: 20%;
                margin-bottom: 20px;
                font-size: 25px;
                font-weight: bold;
                color: white;
                background-color: rgb(223, 0, 41);
            }
            .check{
                margin-left: 45%;
            }
            font{
                clear: both;
                font-weight: bold;
                font-size: 30px;
            }
        </style>            
    </head>
    <body>
        <% Member member= (Member)session.getAttribute("userdata"); %>
        <header>            
            <nav>
                <a href="ListBookServlet">Home</a>
                <a href="CartServlet">Cart</a>
                <a href="HistoryServlet?userID=<%= member.getMemberId()%>">History</a>
            </nav>           
        </header>
<div class="container">                   
            <div class="info">
                <p>Name: <%=member.getMemberFullName()%></p>
                <p>ID: <%=member.getMemberId()%></p>
                <a class="out" href="LogoutServlet">Log out</a>
            </div>    
            
    <div class="search">
        
        <div class="each">   
        <h3>SEARCH BOOK BY AUTHOR</h3>
        <form action="SearchServlet">
            <input type="text" name="txtSearch" value="" required/>
            <input type="submit"  value="Search" />
        </form>
        </div>
        
        <div class="each">   
        <h3>SEARCH BOOK BY PUBLISHER</h3>
        <form action="SearchServlet">
            <input type="text" name="txtSearch1" value="" required/>
             <input type="submit"  value="Search" />
        </form>
        </div> 
            
        <div class="each">   
        <h3>SEARCH BOOK BY PUBLISHING YEAR</h3>
        <form action="SearchServlet">
            <input type="text" name="txtSearch2" value="" required/>
             <input type="submit"  value="Search" />
        </form>
        </div>
         
    </div>
</div>
                
            <section>
                
           <h1>Your Cart</h1>
           <form>
            <table cellpadding="0" cellspacing="0" border="1" align="center" width="75%">
                <tr>
                    <th>Book ID</th>
                    <th>Name</th>
                    <th>Photo</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Remove</th>  
                    <th>Remove Items</th> 
                </tr>
             
       <%
                ArrayList<ShoppingCartItem> ldt = new ArrayList<>();
                ldt = (ArrayList<ShoppingCartItem>) session.getAttribute("cart");
                double total = 0;
                int i = 0;
                if(ldt != null){
                    for(ShoppingCartItem dt: ldt){                       
                        total = total + dt.getBook().getBookPrice();                      
                %>
            <tr>
                <td align="center"><%= dt.getBook().getBookId()%></td>
                <td align="center"><%= dt.getBook().getBookName()%></td>
                <td align="center">
                    <img src="images/<%=dt.getBook().getBookURL()%>" width="100">
                </td>
                <td align="center"><%=dt.getBook().getBookPrice()%></td>
                <td align="center"><%=dt.getQuantity() %></td>                               
                <td align="center">
                    <a href="CartServlet?action=remove&bookId=<%=dt.getBook().getBookId()%>"
                       onclick="return confirm('Are you sure?')">Remove</a>
                </td>
                <td>
                       <input type="checkbox" class="check" name="Book<%=i%>" value="<%= dt.getBook().getBookId()%>" />
                </td>
            </tr>
                       
          <%  i++;}
            }
        else if(ldt == null){
      %>
      <tr>
          <td colspan="7" align="center"><br /> Your cart is empty!</td>
      </tr>
      <%
          }
      %>
      <tr>
          <td colspan="6" align="center">Total Price: <%=total %></td>         
      </tr>
            </table>
      <% if(ldt != null){
      if(!ldt.isEmpty()){    
      %>
     <button type="submit" class="borrow" formaction="BorrowServlet" >Borrow</button>
     <button type="submit" class="delete" formaction="CartServlet" name="action" value="RemoveItems" onclick="return confirm('Are you sure?')">Delete items</button>       
         <% }} %>
           </form>
        </section>           
          <% String errorBorrow = (String) request.getAttribute("errorBorrow");
             if(errorBorrow != null){%>     
            <font color="red"><%= errorBorrow %></font>
            <% } %>
        <footer>
                <p>Online Library Website 2</p>
                <p>Phone number: 0123456789</p>
                <p>Email: Library@gmail.com</p>
            </footer>
    </body>
</html>
