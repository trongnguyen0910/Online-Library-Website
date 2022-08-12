<%-- 
    Document   : BookAuthorForm
    Created on : Mar 9, 2022, 9:16:43 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            h1{
              font-size: 30px;
              font-weight: bold;
              text-align: center;
              color: black;
              padding-top: 10px;
              padding-bottom: 10px;            
            }           
            .form{
                display: inline-block;
                margin-left: 30%;
                padding: 20px;
                border: 3px solid black;
            }
            td{
                font-size: 20px;
                font-weight: bold;
            }
            input{
                width: 400px;
                height: 30px;
            }
            .button{  
                    background-color: rgba(0,0,255,0.8);  
                    width: 100px;
                    height: 40px;
                    margin-left: 87px;
                    color: white;
                    font-size: 20px;
                    font-weight: bold;
                    font-variant-caps: small-caps;
                    margin-bottom: 20px;
                }               
                .selectAu{
                 width: 408px;
                height: 38px;  
                font-size: 15px;
                }
        </style> 
        <title>ADD AUTHORS FOR BOOK</title>
    </head>
    <body>
        <header>
            <nav>
                <a href="ListBookServlet">Home</a>
                <a href="LogoutServlet">Log out</a>
            </nav>
        </header>
        <h1>ADD AUTHORS FOR BOOK</h1>
        <div class="form">
        <form action="BookAuthorServlet">        
        <table>
        <tr>
                    <td>Book ID</td>
                    <td>: <input type="text" name="txtID" 
                                 value="${bookID}"/>
                        <font color="red">${INVALID.idError}</font>
                    </td>                  
                </tr>
         <tr>
                <td>Author</td>
                    <td>:
                         <select class="selectAu" name="txtAuthor" >
                             <c:forEach var="dt" items="${aus}" >                                  
                                 <option value="${dt.auId}"><c:out value="${dt.auName}"/></option>                                
                             </c:forEach>
                         </select> 
                    </td>
                    <td><font color="red">${error}</font></td>                    
                </tr>
          <tr>
               <td colspan="2"><input class="button" type ="submit" value="CREATE"/></td>
          </tr>
          <tr>
              <td></td><td>${successAdd}</td>
          </tr>
         </table>
                    </form>         
                </div>
          
    </body>
</html>
