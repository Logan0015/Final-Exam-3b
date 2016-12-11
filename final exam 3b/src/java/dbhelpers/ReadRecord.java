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
public class ReadRecord {

    private Connection conn;
    private ResultSet results;
    private Customers data = new Customers();
    private int customerID;

    public ReadRecord(int customerID) {

        Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }

        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String passwd = props.getProperty("user.password");

        this.customerID = customerID;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doRead() {

        try {
            String query = "SELECT * FROM customers WHERE customerID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, customerID);
            this.results = ps.executeQuery();
            this.results.next();
            data.setCustomerID(this.results.getInt("customerID"));
            data.setfName(this.results.getString("firstName"));
            data.setlName(this.results.getString("lastName"));
            data.setAddress(this.results.getString("address"));
            data.setAddress2(this.results.getString("address2"));
            data.setCity(this.results.getString("city"));
            data.setState(this.results.getString("state"));
            data.setZipCode(this.results.getString("zipCode"));
            data.setEmailAddress(this.results.getString("emailAddress"));
        } catch (SQLException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Customers getCustomer() {
        return this.data;
    }
}
