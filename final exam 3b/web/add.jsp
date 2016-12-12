<%-- 
    Document   : add
    Created on : Dec 10, 2016, 10:29:30 PM
    Author     : Logan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Customer</title>
        
    </head>
    
    
    <body>
        <%@include file="Administrative/adHeader.jsp" %>
        <%@include file="Administrative/adMenu.jsp" %>
        <h1>Add Customer</h1>
        <form name="AddForm" action="addCustomer" method="post">
            <table>
                <tr>
                    <td class="right">
                        First Name:
                    </td>
                    <td class ="left"><input type="text" name="firstName" value="" size="50" required/>
                    </td>
                </tr>
                
                <tr>
                    <td class="right">
                        Last Name:
                    </td>
                    <td class ="left"><input type="text" name="lastName" value="" size="50" required/>
                    </td>
                </tr>
                
                <tr>
                    <td class="right">
                        Address:
                    </td>
                    <td class ="left"><input type="text" name="addr1" value="" size="50" required/>
                    </td>
                </tr>
                
                <tr>
                    <td class="right">
                        Apt/Suite# :
                    </td>
                    <td class ="left"><input type="number" name="addr2" value="" size="50" />
                    </td>
                </tr>
                
                <tr>
                    <td class="right">
                        City:
                    </td>
                    <td class ="left"><input type="text" name="city" value="" size="50" required/>
                    </td>
                </tr>
                
                <tr>
                    <td class="left"><select name="state">
                        <option value="">  </option>
                            <option value="AL">AL</option>
                            <option value="AK">AK</option>
                            <option value="AZ">AZ</option>
                            <option value="AR">AR</option>
                            <option value="CA">CA</option>
                            <option value="CO">CO</option>
                            <option value="CT">CT</option>
                            <option value="DE">DE</option>
                            <option value="FL">FL</option>
                            <option value="GA">GA</option>
                            <option value="HI">HI</option>
                            <option value="ID">ID</option>
                            <option value="IL">IL</option>
                            <option value="IN">IN</option>
                            <option value="IA">IA</option>
                            <option value="KS">KS</option>
                            <option value="KY">KY</option>
                            <option value="LA">LA</option>
                            <option value="ME">ME</option>
                            <option value="MD">MD</option>
                            <option value="MA">MA</option>
                            <option value="MI">MI</option>
                            <option value="MN">MN</option>
                            <option value="MS">MS</option>
                            <option value="MO">MO</option>
                            <option value="MT">MT</option>
                            <option value="NE">NE</option>
                            <option value="NV">NV</option>
                            <option value="NH">NH</option>
                            <option value="NJ">NJ</option>
                            <option value="NM">NM</option>
                            <option value="NY">NY</option>
                            <option value="NC">NC</option>
                            <option value="ND">ND</option>
                            <option value="OH">OH</option>
                            <option value="OK">OK</option>
                            <option value="OR">OR</option>
                            <option value="PA">PA</option>
                            <option value="RI">RI</option>
                            <option value="SC">SC</option>
                            <option value="SD">SD</option>
                            <option value="TN">TN</option>
                            <option value="TX">TX</option>
                            <option value="UT">UT</option>
                            <option value="VT">VT</option>
                            <option value="VA">VA</option>
                            <option value="WA">WA</option>
                            <option value="WV">WV</option>
                            <option value="WI">WI</option>
                            <option value="WY">WY</option>
                        
                    </td>
                </tr>
                
                <tr>
                    <td class="right">
                        Zip:
                    </td>
                    <td class="left"><input type="number" name="zip" value="" size="50" required/>
                </tr>
                
                <tr>
                    <td class="right">
                        Email:
                    </td>
                    <td class="left"><input type="email" name="emailAddr" value="" size="50" required/>
                    </td>
                </tr>
                
            </table>   
            
            <input type="submit" name="submit" value="Submit"/>
            </form>
        <%@include file="Administrative/adFooter.jsp" %>
        
    
    </body>
    
    
</html>
