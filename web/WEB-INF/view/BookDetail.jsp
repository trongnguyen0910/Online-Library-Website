<%@page import="fu.ex.entities.BookAuthor"%>
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
            .info{
                display: inline-block;             
                font-size: 20px;
                font-weight: bold;
                padding: 15px;
                border: 3px solid black;
                margin-top: 10px;
                background-color: rgba(0,191,255,0.3);
                float: left;
            }
            .out{
                font-size: 25px;
                font-weight: bold;
                color: blue;              
            }    
            .item-container{
                display: inline-block;
                width: 70%;
                position: relative;
                margin-top: 50px;
                margin-bottom: 100px
            }.detail-item{
                display: inline-block;
                margin-left: 50px;
                width: 45%;
                box-shadow: 2px 2px 5px rgb(0,191,255);
                margin-bottom: 30px;
                padding-bottom: 70px;
                position: absolute;
                padding-bottom: 50px;
            }
            .item{
                display: inline-block;
                margin-left: 20px;
                box-shadow: 2px 2px 5px rgb(0,191,255);
                width: 45%;
            }
            .img-item{
                width: 100%;
            }
            h2{
                font-size: 35px;
                text-align: center;
            }
            .BookInfor{
                font-size: 20px;
                margin-left: 20px;               
            }
            h1{
                font-size: 50px;
                font-weight: bold;
                margin-left: 40%
            }
            .buy{
              font-size: 30px;
              font-weight: bold;
              color: white;
               background-color: rgb(0,191,255);
               text-decoration: none;
               border: 2px solid green;
               margin-left: 20px;
               padding: 10px;
            }
        </style>            
    </head>
    <body>
        <% Member member = (Member) session.getAttribute("userdata");%>
        <header>            
            <nav>
                <a href="ListBookServlet">Home</a>
                <a href="CartServlet">Cart</a>
                <a href="HistoryServlet?userID=<%= member.getMemberId()%>">History</a>
            </nav>           
        </header>                   
            <div class="info">
                <p>Name: <%=member.getMemberFullName()%></p>
                <p>ID: <%=member.getMemberId()%></p>
                <a class="out" href="LogoutServlet">Log out</a>
            </div>    

        <% Book b = (Book) request.getAttribute("bookDetail");
            ArrayList<BookAuthor> lba = new ArrayList<BookAuthor>();
            lba = (ArrayList<BookAuthor>) request.getAttribute("authors");
        %>   
        <div class="item-container">
            <h1>Book Detail</h1>
            <div class="item">
                <img class="img-item" src="images/<%=b.getBookURL()%>"/>			
            </div>		
            <div class="detail-item">
                <h2>Detail information</h2>			
                <p class="BookInfor">Book ID: <%=b.getBookId()%></p>
                <p class="BookInfor">Name: <%=b.getBookName()%></p>
                <p class="BookInfor">Author: 
                <% for (BookAuthor ba : lba) {%>
                <%=ba.getAuthor().getAuName()%>,
                <%}%></p>
                <p class="BookInfor">Publisher Name: <%=b.getPublisher().getPubName()%></p>
                <p class="BookInfor">Publishing Year: <%=b.getBookPublishYear()%></p>               
                <p class="BookInfor">Quantity: <%=b.getBookQuantity()%></p>
                <p class="BookInfor">Price: <%=b.getBookPrice()%></p>
                <% if(b.getBookQuantity() > 0 ){ %>
                <a class="buy" href="CartServlet?action=buy&bookId=<%=b.getBookId()%>">Add to cart</a>
               <% } %>
            </div>

        </div> 
        <footer>
            <p>Online Library Website 2</p>
            <p>Phone number: 0123456789</p>
            <p>Email: Library@gmail.com</p>
        </footer>
    </body>
</html>