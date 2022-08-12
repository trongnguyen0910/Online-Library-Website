<%-- 
    Document   : staffview
    Created on : Mar 6, 2022, 10:39:15 AM
    Author     : Admin
--%>
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
                height: 60px;
                width: 100%;                
            }
            h1{
              background-color: #00BFFF;
              font-size: 30px;
              font-weight: bold;
              text-align: center;
              color: white;
              padding-top: 10px;
              padding-bottom: 10px;
              border: 3px solid green;
            }           
            section{
                display: inline-block;
                width: 70%;
                float: left;
                margin-bottom: 20px;
                margin-top: 15px;
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
                width: 25%;               
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
                border: 3px solid black;
                padding: 0 15px 15px 15px;
            }
            .add{
                display:block;
                width: 68%;
                padding-left: 10px;
                margin-top: 20px; 
                margin-left: 20px;
                text-decoration: none;
                font-weight: bold;
                font-size: 30px;
                color: blue;
                border: 2px solid black;
                background-color: rgba(0,191,255,0.3);
            }
            .option{
                text-decoration: none;
                font-weight: bold;
                font-size: 15px;
                color: blue;
            }
            th{
                background-color: #00BFFF;
                font-weight: bold;
                font-size: 25px;
                color: white;
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
            <h1>List Books</h1>          
        </header>
<div class="container">
            <div class="info">
                <p>Name: <%=member.getMemberFullName()  %></p>
                <p>ID: <%=member.getMemberId()%></p>
                <a class="out" href="LogoutServlet">Log out</a>
            </div>    
         <a class="add" href="CreateFormServlet">ADD NEW BOOK</a>    
    <div class="search">

        <div class="each">   
        <h3>SEARCH BOOK BY ID</h3>
        <form action="SearchServlet">
            <input type="text" name="Search" value="" required placeholder="Enter a Book ID"/>
            <input type="submit"  value="Search" />
        </form>
        </div>
        
        <div class="each">   
        <h3>SEARCH BOOK BY NAME</h3>
        <form action="SearchServlet">          
            <input type="text" name="Search" value="" required placeholder="Enter a Book Name"/>
             <input type="submit"  value="Search" />
        </form>
        </div>
        
        <div class="each"> 
        <h3>VIEW BOOK BORROWING BY MEMBER</h3>
        <form action="SearchServlet">
            <input type="text" name="viewBookBorrowByMemberID" value="" required placeholder="Enter Member ID"/>
            <input type="submit"  value="Go" />
        </form>
        </div>
        
        <%--  <div class="each"> 
         <h3>VIEW BOOK BORROWED BY BOOK</h3>
        <form action="">
            <input type="text" name="id" value="" required placeholder="Enter a Book ID"/>
            <input type="submit"  value="Go" />
        </form>
        </div>
        
        <div class="each"> 
        <h3>VIEW BOOK BORROWED DATE</h3>
        <form action="">
            <input type="text" name="id" value="" required placeholder="Enter a Book ID"/>
            <input type="submit"  value="Go" />
        </form>
        </div> --%>
   </div>
</div>   
        <% 
             ArrayList<Book> listBooks = new ArrayList<Book>();
             listBooks = (ArrayList<Book>) request.getAttribute("books");
             ArrayList<BookAuthor> lba = new ArrayList<BookAuthor>();
             lba = (ArrayList<BookAuthor>) request.getAttribute("BookAuthorList");
             String errorDelete = (String) request.getAttribute("errorDelete");
            if(errorDelete!=null){%>
            <font class="notice" color="red"><%=errorDelete%></font>
            <% } %>
        <%  if(!listBooks.isEmpty()) {%>
            <section>            
            <table cellpadding="0" cellspacing="0" border="1" align="center" width="100%">
                <tr>
                    <th>Book ID</th>
                    <th>Name</th>
                    <th>Photo</th>
                    <th>Publisher Name</th>
                    <th>Publishing year</th>
                    <th>Author</th>
                    <th>Price</th>
                    <th>Quantity</th>  
                    <th>Option</th>
                </tr> 
        <% for (Book dt : listBooks){                
              %>
              <tr>
                  <td align="center"><%=dt.getBookId()%></td>
                  <td align="center"><%=dt.getBookName()%></td>
                  <td>
                      <img src="images/<%=dt.getBookURL()%>" width="100">
                  </td>
                  <td align="center"><%=dt.getPublisher().getPubName()%></td>
                  <td align="center"><%=dt.getBookPublishYear() %></td>                 
                  <td align="center">
                 <%  for (BookAuthor ba : lba){ 
                    if(dt.getBookId().equalsIgnoreCase(ba.getBook().getBookId())){
                  %>                 
                  <%= ba.getAuthor().getAuName() %>   |                      
                  <% } }%>
                  </td>
                  <td align="center"><%=dt.getBookPrice()%></td>
                  <td align="center"><%=dt.getBookQuantity()%></td>                  
                  <td align="center">
                      <a class="option"  href="UpdateFormServlet?bid=<%=dt.getBookId()%>">EDIT</a>     |     
                      <a class="option" href="DeleteServlet?bid=<%=dt.getBookId()%>" onclick="return confirm('Are you sure?')">REMOVE</a>    
                         
                  </td>
              </tr>
       <%     }   %>         
            </table>
        </section>
            <% } %>
            <footer>
                <p>Online Library Website 2</p>
                <p>Phone number: 0123456789</p>
                <p>Email: Library@gmail.com</p>
            </footer>
    </body>
</html>
