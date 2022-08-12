<%-- 
    Document   : history.jsp
    Created on : Mar 13, 2022, 3:53:38 PM
    Author     : Thien Le
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
                margin-left: 33%;
                color: white;
            }
            </style> 
    </head>
    <body>
        <header>            
            <nav>
                <a href="ListBookServlet">Home</a>
                <a href="CartServlet">Cart</a>
            </nav>           
        </header>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">No</th>
                    <th scope="col">Borrow Date</th>
                    <th scope="col">Book Name</th>
                    <th scope="col">Is return ?</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="history" items="${requestScope.LIST_HISTORY}" varStatus="no">
                    <tr>
                        <th scope="row">${no.count}</th>
                        <td>${history.callcard.callCardBorrowDate}</td>
                        <td>${history.book.bookName}</td>
                        <c:if test="${history.status eq 1}"><td>Returned</td></c:if>
                          <c:if test="${history.status eq 0}"><td>Not Return</td></c:if>
                    </tr>
                </c:forEach>
                    

            </tbody>
        </table>





















        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>
