<%-- 
    Document   : read
    Created on : Dec 11, 2016, 1:32:24 AM
    Author     : Logan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Read</title>
    </head>
    
    <body>
    <%@ include file="includes/header.jsp" %>    
    <%@ include file="includes/menu.jsp" %>
    <h1>Customers</h1>
        
    <p>
       
        <% String table = (String) request.getAttribute("table");%>
        <%= table %>
    </p>
        
    <br>
    <br>
        <%@ include file="includes/footer.jsp" %>
        </div>
    </body>
        
</html>
