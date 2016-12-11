package dbhelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customers;

/**
 *
 * @author Logan
 */
public class SearchQuery {
    private Connection conn;
    private ResultSet results;
    
    public SearchQuery (){
        Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(SearchQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(SearchQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String passwd = props.getProperty("user.password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(SearchQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void doSearch (String name){
        try {
            String query = "SELECT * FROM customers WHERE UPPER(firstName) LIKE ? OR UPPER(lastName) LIKE ? ORDER BY CustomerID ASC";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + name.toUpperCase() + "%");
            ps.setString(2, "%" + name.toUpperCase() + "%");
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SearchQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public String getHTMLtable() {

        String table = "";
        table += "<table>";

        try {

            table += "<tr>";
            table += "<th> Customer ID </th>";
            table += "<th> First Name </th>";
            table += "<th> Last Name </th>";
            table += "<th> Address Line 1 </th>";
            table += "<th> Apt/Suite # </th>";
            table += "<th> City </th>";
            table += "<th> State </th>";
            table += "<th> Zip Code </th>";
            table += "<th> Email Address </th>";
            table += "</tr>";

            while (this.results.next()) {

                Customers data = new Customers();
                data.setCustomerID(this.results.getInt("customerID"));
                data.setfName(this.results.getString("firstName"));
                data.setlName(this.results.getString("lastName"));
                data.setAddress(this.results.getString("address1"));
                data.setAddress2(this.results.getString("address2"));
                data.setCity(this.results.getString("city"));
                data.setState(this.results.getString("state"));
                data.setZipCode(this.results.getString("zipCode"));
                data.setEmailAddress(this.results.getString("Email Address"));


                table += "<tr>";
                table += "<td>";
                table += data.getCustomerID();
                table += "</td>";

                table += "<td>";
                table += data.getfName();
                table += "</td>";

                table += "<td>";
                table += data.getlName();
                table += "</td>";

                
                table += "<td>";
                table += data.getAddress();
                table += "</td>";
                
                table += "<td>";
                table += data.getAddress2();
                table += "</td>";

                table += "<td>";
                table += data.getCity();
                table += "</td>";

                table += "<td>";
                table += data.getState();
                table += "</td>";

                table += "<td>";
                table += data.getZipCode();
                table += "</td>";

                table += "<td>";
                table += data.getEmailAddress();
                table += "</td>";
                
                table += "<td>";
                table += "<a href=update?customerID=" + data.getCustomerID() + "> Update </a>" +"<a href=delete?customerID=" + data.getCustomerID() + "> Delete </a>";
                table += "</td>";
                

                table += "</tr>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        table += "</table>";

        return table;
    }
}