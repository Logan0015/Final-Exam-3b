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
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customers;

/**
 *
 * @author Logan
 */
public class UpdateQuery {
    private Connection conn;
    
    public UpdateQuery (){
    
    Properties props = new Properties();
    InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    String driver = props.getProperty("driver.name");
    String url = props.getProperty("server.name");
    String username = props.getProperty("user.name");
    String passwd = props.getProperty("user.password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
        
    public void doUpdate (Customers data){
        
        try {
            String query = "UPDATE customers SET fName = ?, lName = ?, address = ?, address2 = ?, city = ?, state = ?, zipCode = ?, emailAddress = ? WHERE customerID = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, data.getfName());
            ps.setString(2, data.getlName());
            ps.setString(3, data.getAddress());
            ps.setString(4, data.getAddress2());
            ps.setString(5, data.getCity());
            ps.setString(6, data.getState());
            ps.setString(7, data.getZipCode());
            ps.setString(8, data.getEmailAddress());
            ps.setInt(9, data.getCustomerID());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
