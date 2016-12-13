<%-- 
    Document   : adminSearch
    Created on : Dec 11, 2016, 1:29:50 AM
    Author     : Logan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="custom.css"/>
        <title>Admin Search</title>
    </head>
    
    <body>
        <form name ="searchForm" action="adminSearch" method="post">
            <input type="text" name="searchVal" value=""/>
            <input type="submit" name="search" value="Search" />
        </form>
    </body>
</html>
