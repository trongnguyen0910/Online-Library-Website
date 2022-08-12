<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <meta charset ="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
            header{
                margin-top: 50px;
                height: 100px;
                margin-left: 35%;
                font-size: 50px;
                font-weight: bold;                                            
            }
            body{
                background-color: rgba(0,191,255,0.3);
            }
            div{
                margin-left: 20%;  
                border: 2px solid black;
                display: inline-block;
                padding: 2%;
            }
                input{
                    width: 300px;
                    height: 30px;
                    margin-bottom: 10px;
                }
                .button{
                    
                    background-color: rgba(0,0,255,0.8);  
                    width: 100px;
                    height: 40px;
                    margin-left: 175px;
                    color: white;
                    font-size: 20px;
                    font-weight: bold;
                }
                td{
                    font-size: 25px;
                    font-weight: bold;                                         
                }
                p{
                    font-size: 20px;
                    font-weight: bold;
                    
                }
                .regis{
                    color: blue;
                    text-decoration: none;
                    
                }
                font{
                    font-size: 20px;
                    font-weight: bold;
                }
            
        </style>        
    </head>
    <body>
        <header>Register</header>

        <div>
            <form action="RegisterServlet">
            <table>
                <tr>
                    <td>ID</td> <td>: <input type="text" name="id" value="${mObj.memberId}" />
                    <font color="red">${INVALID.idError}</font>
                    </td>
                    
                </tr>
                
                <tr>
                    <td>Password</td> <td>: <input type="password" name="password"
                    value="${mObj.memberPassword}"/>
                    <font color="red">${INVALID.passwordError}</font>
                    </td>
                    
                </tr>
                <tr>
                    <td>Full name</td> <td>: <input type="text" name="name" 
                    value="${mObj.memberFullName}" />
                    <font color="red">${INVALID.fullnameError}</font>
                    </td>
                    
                </tr>
                <tr>
                    <td>Address</td> <td>: <input type="text" name="address" 
                    value="${mObj.memberAddress}" />
                    <font color="red">${INVALID.addressError}</font>
                    </td>
                    
                </tr>
                <tr>
                    <td>Phone number</td> <td>: <input type="text" name="phone" 
                    value="${mObj.memberPhone}" />
                    <font color="red">${INVALID.phoneError}</font>
                    </td>                    
                </tr>                
                <tr>
                    <td colspan="2"><input class="button" type="submit" value="Register"</td>
                </tr>                                 
            </table>                
        </form>
        <p>If you had an account, please <a class="regis" href="LoginServlet">Log in</a>.</p>                       
        </div>
    </body>
</html>
