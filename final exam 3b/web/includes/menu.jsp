<%-- 
    Document   : menu
    Created on : Dec 10, 2016, 7:04:12 PM
    Author     : Logan
--%>

<div class="navigationbar">
    <ul class="navigation">
        <li class="active"><a href="index.jsp">Home</a></li>
        <li><a href="read">View Customers</a></li>
        
        <li><a href="admin.jsp">Admin</a></li>
    </ul>
    <div class="search">
        <form name="searchForm" action="search" method="post">
                            <input type="text" name="searchVal" value=""/>
                            <input type="submit" name="submit" value="Search"/>
        </form>                
    
    </div>