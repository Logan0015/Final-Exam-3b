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
                data.setCustID(this.results.getInt("custID"));
                data.setFirstName(this.results.getString("firstName"));
                data.setLastName(this.results.getString("lastName"));
                data.setAddr1(this.results.getString("addr1"));
                data.setAddr2(this.results.getString("addr2"));
                data.setCity(this.results.getString("city"));
                data.setState(this.results.getString("state"));
                data.setZip(this.results.getString("zip"));
                data.setEmailAddr(this.results.getString("EmailAddr"));


                table += "<tr>";
                table += "<td>";
                table += data.getCustID();
                table += "</td>";

                table += "<td>";
                table += data.getFirstName();
                table += "</td>";

                table += "<td>";
                table += data.getLastName();
                table += "</td>";

                
                table += "<td>";
                table += data.getAddr1();
                table += "</td>";
                
                table += "<td>";
                table += data.getAddr2();
                table += "</td>";

                table += "<td>";
                table += data.getCity();
                table += "</td>";

                table += "<td>";
                table += data.getState();
                table += "</td>";

                table += "<td>";
                table += data.getZip();
                table += "</td>";

                table += "<td>";
                table += data.getEmailAddr();
                table += "</td>";
                
                table += "<td>";
                table += "<a href=update?custID=" + data.getCustID() + "> Update </a>" +"<a href=delete?custID=" + data.getCustID() + "> Delete </a>";
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