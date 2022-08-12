<%-- 
    Document   : computerform
    Created on : Feb 16, 2022, 9:45:45 PM
    Author     : LENOVO
--%>

<%@page import="fu.ex.entities.Book"%>
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
                margin-left: 25%;
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
                    margin-left: 150px;
                    color: white;
                    font-size: 20px;
                    font-weight: bold;
                    font-variant-caps: small-caps;
                    margin-bottom: 20px;
                }
                .addAu{
                    text-decoration: none;
                    font-size: 20px;
                    font-weight: bold;
                    background-color: rgba(0,0,255,0.8); 
                    color: white;
                    margin-left: 152px;
                    padding: 7px;
                    border: 2px solid black;
                }
                .selectPub{
                 width: 408px;
                height: 38px;  
                font-size: 15px;
                }
        </style>      
     <c:if test="${action eq 'create'}">
            <title>ADD NEW BOOK</title>
        </c:if>
       <c:if test="${action eq 'update'}">
            <title>UPDATE BOOK</title>
        </c:if>
    </head>
    <body>
        <header>
            <nav>
                <a href="ListBookServlet">Home</a>
                <a href="LogoutServlet">Log out</a>
            </nav>
        </header>
        <c:if test="${action eq 'create'}">
            <h1>ADD NEW BOOK</h1>
        </c:if>
       <c:if test="${action eq 'update'}">
            <h1>UPDATE BOOK</h1>
        </c:if>
        <div class="form">
        <form <c:if test="${action eq 'create'}"> action="CreateServlet"</c:if>
                  <c:if test="${action eq 'update'}">action="UpdateServlet"</c:if>                        
                                                   >
            <table>
                <tr>
                    <td>Book ID</td>
                    <td>: <input type="text" name="txtID" 
                                 value="${bookObj.bookId}" 
                                 <c:if test="${action eq 'update'}">readonly</c:if>
                                 />
                        <font color="red">${INVALID.idError}</font>
                    </td>                  
                </tr>
                <tr>
                    <td>Name</td>                   
                    <td>: <input type="text" name="txtName" 
                                 value="${bookObj.bookName}" <c:if test="${action eq 'update'}">readonly</c:if>/>
                    <font color="red">${INVALID.nameError}</font>
                    </td>                  
                </tr>
                <tr>
                    <td>Image URL</td>
                    <td>: <input type="text" name="txtURL" 
                                 value="${bookObj.bookURL}" <c:if test="${action eq 'update'}">readonly</c:if> />
                    <font color="red">${INVALID.urlError}</font>
                    </td>                  
                </tr>
                
                <tr>
                <td>Publisher</td>
                    <td>:
                         <select class="selectPub" name="txtPublisher" >
                             <c:forEach var="dt" items="${pubs}" >                                  
                                 <option value="${dt.pubId}"><c:out value="${dt.pubName}"/></option>
                             </c:forEach>
                         </select> 
                    </td>            
                </tr>
                <tr>
                    <td>Publishing year</td>
                    <td>: <input type="number" name="txtPublishingYear" 
                                 value="${bookObj.bookPublishYear}" <c:if test="${action eq 'update'}">readonly</c:if>/>
                    <font color="red">${INVALID.publishingYearError}</font>
                    </td>                  
                </tr>
                <tr>
                    <td>Price</td>
                    <td>: <input type="number"  step="0.1"   name="txtPrice"
                                 value="${bookObj.bookPrice}" />
                    <font color="red">${INVALID.priceError}</font>
                    </td>                    
                </tr>
                <tr>
                <td>Quantity</td>
                <td>: <input type="number"  step="1"  name="txtQuantity" 
                                 value="${bookObj.bookQuantity}" />
                    <font color="red">${INVALID.quantityError}</font>
                    </td>             
                </tr>
                <tr>
                    <td colspan="2"><input class="button" type ="submit" value="${action}"/></td>
                </tr>
            </table>
        </form>  
                <a class="addAu" href="BookAuthorFormServlet">ADD AUTHORS FOR BOOK</a>
                </div>               
    </body>
</html>
