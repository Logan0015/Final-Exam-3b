/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class AdSearchQuery {
    
    private Connection conn;
    private ResultSet results;
    
    public AdSearchQuery (){
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
            String query = "SELECT * FROM customers WHERE UPPER(firstName) LIKE ? OR UPPER(lastName) LIKE ? ORDER BY custID ASC";
            
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

                Customers test2 = new Customers();
                test2.setCustID(this.results.getInt("custID"));
                test2.setFirstName(this.results.getString("firstName"));
                test2.setLastName(this.results.getString("lastName"));
                test2.setAddr1(this.results.getString("addr1"));
                test2.setAddr2(this.results.getString("addr2"));
                test2.setCity(this.results.getString("city"));
                test2.setState(this.results.getString("state"));
                test2.setZip(this.results.getString("zip"));
                test2.setEmailAddr(this.results.getString("emailAddr"));


                table += "<tr>";
                table += "<td>";
                table += test2.getCustID();
                table += "</td>";

                table += "<td>";
                table += test2.getFirstName();
                table += "</td>";

                table += "<td>";
                table += test2.getLastName();
                table += "</td>";

                
                table += "<td>";
                table += test2.getAddr1();
                table += "</td>";
                
                table += "<td>";
                table += test2.getAddr2();
                table += "</td>";

                table += "<td>";
                table += test2.getCity();
                table += "</td>";

                table += "<td>";
                table += test2.getState();
                table += "</td>";

                table += "<td>";
                table += test2.getZip();
                table += "</td>";

                table += "<td>";
                table += test2.getEmailAddr();
                table += "</td>";
                
                table += "<td>";
                table += "<a href=update?custID=" + test2.getCustID() + "> Update </a>" +"<a href=delete?custID=" + test2.getCustID() + "> Delete </a>";
                table += "</td>";
                

                table += "</tr>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        table += "</table>";

        return table;

    }
    
}

