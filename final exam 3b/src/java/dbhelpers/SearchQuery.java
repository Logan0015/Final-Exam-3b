
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
            String query = "SELECT * FROM customers WHERE UPPER(firstName) LIKE ? OR UPPER(lastName) LIKE ? ORDER BY CustID ASC";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + name.toUpperCase() + "%");
            ps.setString(2, "%" + name.toUpperCase() + "%");
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SearchQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public String getHTMLtable(){
            
            String table = "";
            table += "<table border=5>";
            table += "<tr>";
            table += "<th> Customer ID </th>";
            table += "<th> First Name </th>";
            table += "<th> Last Name </th>";
            table += "<th> Address </th>";
            table += "<th> Address </th>";
            table += "<th> City </th>";
            table += "<th> State </th>";
            table += "<th> Zip </th>";
            table += "<th> Email </th>";
            //table += "<th> Update/Delete </th>";        
            table += "</tr>";
            
            
            
            try {
            while(this.results.next()){
                
                Customers data = new Customers();
                data.setCustomerID(this.results.getInt("customerID"));
                data.setFName(this.results.getString("fName"));
                data.setLName(this.results.getString("lName"));
                data.setAddress(this.results.getString("address"));
                data.setAddress2(this.results.getString("address2"));
                data.setCity(this.results.getString("city"));
                data.setState(this.results.getString("state"));
                data.setZipCode(this.results.getString("zipCode"));
                data.setEmailAddress(this.results.getString("emailAddress"));
                
                table += "<tr>";
                table += "<td>";
                table += data.getCustomerID();
                table += "</td>";
                
                table += "<td>";
                table += data.getFName();
                table += "</td>";
                
                table += "<td>";
                table += data.getLName();
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
                
                //table += "<td>";
                //table += "<a href=update?custID=" + customer.getCustID()+ "> Update </a>" + "<a href=delete?custID=" + customer.getCustID() + "> Delete </a>";
                //table += "</td>";
                
                
                table += "</tr>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            table +="</table>";
            
            return table;
}
}