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
        
        <h1>All Customers</h1>
    <% String table = (String) request.getAttribute("table");%>
    <%= table %>
    </body>
</html>
