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
public class AdReadQuery {
    private Connection conn;
    private ResultSet results;

    public AdReadQuery(){
    Properties props = new Properties();
    InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(AdReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(AdReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    String driver = props.getProperty("driver.name");
    String url = props.getProperty("server.name");
    String username = props.getProperty("user.name");
    String passwd = props.getProperty("user.password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(AdReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }    
}    

    public void doRead() {
    try{
        String query = "SELECT * FROM Customers";
        PreparedStatement ps = conn.prepareStatement(query);
        this.results = ps.executeQuery();
        } catch (SQLException ex) {
        Logger.getLogger(AdReadQuery.class.getName()).log(Level.SEVERE, null, ex);
    }

    }

    public String getHTMLTable() {
        String custTable = "";
       
        try {
            
        custTable+="<tr>";
        custTable+="<th> Customer ID </th>";
        custTable+="</tr>";
        
        custTable+="<tr>";
        custTable+="<th> First Name </th>";
        custTable+="</tr>";
        
        custTable+="<tr>";
        custTable+="<th> Last Name </th>";
        custTable+="</tr>";
        
        custTable+="<tr>";
        custTable+="<th> Address </th>";
        custTable+="</tr>";
        
        custTable+="<tr>";
        custTable+="<th> Apt/Suite # </th>";
        custTable+="</tr>";
        
        custTable+="<tr>";
        custTable+="<th> City </th>";
        custTable+="</tr>";
        
        custTable+="<tr>";
        custTable+="<th> State </th>";
        custTable+="</tr>";
        
        custTable+="<tr>";
        custTable+="<th> Zip Code </th>";
        custTable+="</tr>";
        
        custTable+="<tr>";
        custTable+="<th> Email Address </th>";
        custTable+="</tr>";
        
        
        while(this.results.next()){
            Customers data = new Customers();
            data.setCustomerID(this.results.getInt("customerID"));
            data.setfName(this.results.getString("firstName"));
            data.setlName(this.results.getString("lastName"));
            data.setAddress(this.results.getString("address"));
            data.setAddress2(this.results.getString("address2"));
            data.setCity(this.results.getString("City"));
            data.setState(this.results.getString("State"));
            data.setZipCode(this.results.getString("Zip Code"));
            data.setEmailAddress(this.results.getString("Email Address"));
            
            custTable+= "<tr>";
            custTable+= "<td>";
            custTable+= data.getCustomerID();
            custTable+= "/td>";
            custTable+= "</tr>";
            
            custTable+= "<tr>";
            custTable+= "<td>";
            custTable+= data.getfName();
            custTable+= "/td>";
            custTable+= "</tr>";
            
            custTable+= "<tr>";
            custTable+= "<td>";
            custTable+= data.getlName();
            custTable+= "/td>";
            custTable+= "</tr>";
            
            custTable+= "<tr>";
            custTable+= "<td>";
            custTable+= data.getAddress();
            custTable+= "/td>";
            custTable+= "</tr>";
            
            custTable+= "<tr>";
            custTable+= "<td>";
            custTable+= data.getAddress2();
            custTable+= "/td>";
            custTable+= "</tr>";
            
            custTable+= "<tr>";
            custTable+= "<td>";
            custTable+= data.getCity();
            custTable+= "/td>";
            custTable+= "</tr>";
            
            custTable+= "<tr>";
            custTable+= "<td>";
            custTable+= data.getState();
            custTable+= "/td>";
            custTable+= "</tr>";
            
            custTable+= "<tr>";
            custTable+= "<td>";
            custTable+= data.getZipCode();
            custTable+= "/td>";
            custTable+= "</tr>";
            
            custTable+= "<tr>";
            custTable+= "<td>";
            custTable+= data.getEmailAddress();
            custTable+= "/td>";
            custTable+= "</tr>";
            
            custTable+= "<tr>";
            custTable+= "<td>";
            custTable+= "a href=update?customerID=" + data.getCustomerID()+ "> Update </a>" + "<a href=delete?customerID"+ data.getCustomerID()+ "> Delete </a>";
            custTable+= "/td>";
            custTable+= "</tr>";
        }
        } catch (SQLException ex){
            Logger.getLogger(AdReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        custTable +="</table>";
        return custTable;
    }
}