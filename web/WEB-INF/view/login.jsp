<%-- 
    Document   : login
    Created on : Mar 6, 2022, 10:14:28 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset ="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
            header{
                margin-top: 20px;
                height: 100px;
                margin-left: 45%;
                font-size: 50px;
                font-weight: bold; 
            }
            body{
                background-color: rgba(0,191,255,0.3);
            }
            div{
                margin-left: 33%;  
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
                    margin-left: 120px;
                    color: white;
                    font-size: 20px;
                    font-weight: bold;
                }
                td{
                    font-size: 25px;
                    font-weight: bold
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
        <header>Log in</header>
        <% 
            String err = (String) request.getAttribute("errormessage"); 
            String success = (String) request.getAttribute("success");  
            String keepUser = request.getParameter("name");
             if(keepUser==null){keepUser="";};
        %>
        <div>
        <form action="LoginServlet" method="POST">
            <table>
                <tr>
                    <td>ID</td> <td>: <input type="text" name="name" value="<%= keepUser %>"</td>
                </tr>
                <tr>
                    <td>Password</td> <td>: <input type="password" name="password" 
                    value=""</td>
                </tr>
               <tr>
                    <td colspan="2"><input class="button" type="submit" value="Log in"</td>
                </tr> 
                <br>                
            </table>                
        </form>
            <%    if(err != null){ %> <font color="red" > <%= err %></font><% } %>
            <%    if(success != null){ %> <font color="blue" > <%= success %></font><% } %>
                <p>If you don't have account, please <a class="regis" href="RegisterFormServlet">Register</a>.</p>    
        </div>
    </body>
</html>
