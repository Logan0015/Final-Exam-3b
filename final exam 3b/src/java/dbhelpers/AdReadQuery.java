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
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    String driver = props.getProperty("driver.name");
    String url = props.getProperty("server.name");
    String username = props.getProperty("user.name");
    String passwd = props.getProperty("user.password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }    
}    

    public void doRead() {
    try{
        String query = "Select * from Customers";
        PreparedStatement ps = conn.prepareStatement(query);
        this.results = ps.executeQuery();
        } catch (SQLException ex) {
        Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
    }

    }

    public String getHTMLtable() {
        String table = "";   
        table+="<tr>";
        table+="<th> Customer ID </th>";
        table+="</tr>";
        
        table+="<tr>";
        table+="<th> First Name </th>";
        table+="</tr>";
        
        table+="<tr>";
        table+="<th> Last Name </th>";
        table+="</tr>";
        
        table+="<tr>";
        table+="<th> Address </th>";
        table+="</tr>";
        
        table+="<tr>";
        table+="<th> Apt/Suite # </th>";
        table+="</tr>";
        
        table+="<tr>";
        table+="<th> City </th>";
        table+="</tr>";
        
        table+="<tr>";
        table+="<th> State </th>";
        table+="</tr>";
        
        table+="<tr>";
        table+="<th> Zip Code </th>";
        table+="</tr>";
        
        table+="<tr>";
        table+="<th> Email Address </th>";
        table+="</tr>";
        
        try {
        while(this.results.next()){
            Customers test = new Customers();
            test.setCustID(this.results.getInt("custID"));
            test.setFirstName(this.results.getString("firstName"));
            test.setLastName(this.results.getString("lastName"));
            test.setAddr1(this.results.getString("addr1"));
            test.setAddr2(this.results.getString("addr2"));
            test.setCity(this.results.getString("city"));
            test.setState(this.results.getString("state"));
            test.setZip(this.results.getString("zip"));
            test.setEmailAddr(this.results.getString("emailAddr"));
            
            table+= "<tr>";
            table+= "<td>";
            table+= test.getCustID();
            table+= "/td>";
            table+= "</tr>";
            
            table+= "<tr>";
            table+= "<td>";
            table+= test.getFirstName();
            table+= "/td>";
            table+= "</tr>";
            
            table+= "<tr>";
            table+= "<td>";
            table+= test.getLastName();
            table+= "/td>";
            table+= "</tr>";
            
            table+= "<tr>";
            table+= "<td>";
            table+= test.getAddr1();
            table+= "/td>";
            table+= "</tr>";
            
            table+= "<tr>";
            table+= "<td>";
            table+= test.getAddr2();
            table+= "/td>";
            table+= "</tr>";
            
            table+= "<tr>";
            table+= "<td>";
            table+= test.getCity();
            table+= "/td>";
            table+= "</tr>";
            
            table+= "<tr>";
            table+= "<td>";
            table+= test.getState();
            table+= "/td>";
            table+= "</tr>";
            
            table+= "<tr>";
            table+= "<td>";
            table+= test.getZip();
            table+= "/td>";
            table+= "</tr>";
            
            table+= "<tr>";
            table+= "<td>";
            table+= test.getEmailAddr();
            table+= "/td>";
            table+= "</tr>";
            
            table+= "<tr>";
            table+= "<td>";
            table+= "a href=update?custID=" + test.getCustID()+ "> Update </a>" + "<a href=delete?custID"+ test.getCustID()+ "> Delete </a>";
            table+= "/td>";
            table+= "</tr>";
        }
        } catch (SQLException ex){
            Logger.getLogger(AdReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        table +="</table>";
        return table;
    }
}