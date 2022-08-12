<%-- 
    Document   : userview
    Created on : Mar 6, 2022, 10:39:05 AM
    Author     : Admin
--%>
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
                float: left;
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
                overflow: hidden;
            }
            .child{
                position: absolute;
                width: 100%;
                bottom: 0px;  
                left: 0px;
                font-size: 25px;
                font-weight: bold;
                background-color: rgb(0,191,255);
                padding: 5px 0px 5px 0px;
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
            .each{
                display: inline-block;
                width: 100%;
                margin-bottom: 25px;
                border: 2px solid black;
                padding: 0px 0px 15px 12px;
            }
            .action{
                color: white;
                text-decoration: none;
            }
            .out{
                font-size: 25px;
                font-weight: bold;
                color: blue;              
            } 
            .notice{
                font-size: 30px;
                font-weight: bold;
                margin-left: 20px;
                margin-bottom: 20px;
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
           <% String errorAdd = (String) request.getAttribute("errorBuy");
            if(errorAdd!=null){%>
            <font class="notice" color="red"><%= errorAdd %></font>
            <% } %> 
        <% ArrayList<Book> ldt = new ArrayList<Book>();
            ldt = (ArrayList<Book>) request.getAttribute("books");
        if(ldt!=null){
        %>
            <section>           
            <% for(Book dt : ldt){ %>
            <div class="img">
                <%=dt.getBookId()%> - <%=dt.getBookName()%> <br>
                <img src="images/<%=dt.getBookURL()%>" width="120"> <br>
                <%=dt.getBookPrice()%> <br>
                <a href="DetailBookServlet?bid=<%=dt.getBookId()%>">View more</a>
                
                <div class="child">
                    <a class="action" href="CartServlet?action=buy&bookId=<%=dt.getBookId()%>">+</a>                    
                </div>
            </div>
            <%}%>
        </section>
         <% }%>
            <footer>
                <p>Online Library Website 2</p>
                <p>Phone number: 0123456789</p>
                <p>Email: Library@gmail.com</p>
            </footer>
    </body>
</html>
