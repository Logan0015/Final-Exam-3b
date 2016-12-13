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
        <link rel="stylesheet" type="text/css" href="custom.css"/>
        <title>Read</title>
    </head>
    
    <body>
    <%@ include file="includes/header.jsp" %>    
    <%@ include file="includes/menu.jsp" %>
    
    <div class="page">  
    <div class="row">
                <div class="col-xs-12">
                    <!-- content that takes all 12 cols -->
                    <h2>All customers in database</h2>
                    <p>
                        <% String table = (String) request.getAttribute("table");%>



                        <%= table%>
                    </p>
                </div>

            </div>
        
    <br>
    <br>
        <%@ include file="includes/footer.jsp" %>
        </div>
     
    </body>
        
</html>
