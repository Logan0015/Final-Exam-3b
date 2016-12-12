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

public class ReadQuery {

    private Connection conn;
    private ResultSet results;

    public ReadQuery() {

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
        try {
            String query = "Select * from customers";

            PreparedStatement ps = conn.prepareStatement(query);
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public String getHTMLTable() {

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

                Customers test = new Customers();
                test.setCustID(this.results.getInt("custID"));
                test.setFirstName(this.results.getString("firstName"));
                test.setLastName(this.results.getString("lastName"));
                test.setAddr1(this.results.getString("addr1"));
                test.setAddr2(this.results.getString("addr2"));
                test.setCity(this.results.getString("city"));
                test.setState(this.results.getString("state"));
                test.setZip(this.results.getString("zip"));
                test.setEmailAddr(this.results.getString("EmailAddr"));


                table += "<tr>";
                table += "<td>";
                table += test.getCustID();
                table += "</td>";

                table += "<td>";
                table += test.getFirstName();
                table += "</td>";

                table += "<td>";
                table += test.getLastName();
                table += "</td>";

                
                table += "<td>";
                table += test.getAddr1();
                table += "</td>";
                
                table += "<td>";
                table += test.getAddr2();
                table += "</td>";

                table += "<td>";
                table += test.getCity();
                table += "</td>";

                table += "<td>";
                table += test.getState();
                table += "</td>";

                table += "<td>";
                table += test.getZip();
                table += "</td>";

                table += "<td>";
                table += test.getEmailAddr();
                table += "</td>";
                
                table += "<td>";
                
                table += "</td>";
                

                table += "</tr>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        table += "</table>";

        return table;
    }
}