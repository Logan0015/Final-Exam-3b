<%-- 
    Document   : adRead
    Created on : Dec 11, 2016, 11:54:19 PM
    Author     : Logan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="custom.css"/>
        <title>JSP Page</title>
    </head>
    <body>
            <% String table = (String) request.getAttribute("table");%>



            <%= table %>

    </body>
</html>
