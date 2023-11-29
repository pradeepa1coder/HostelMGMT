package org.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveData extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Getting parameters from the request
        String sid = req.getParameter("num");
        int id = Integer.parseInt(sid);
        String name = req.getParameter("nm");
        String sphone = req.getParameter("ph");
        int phone = Integer.parseInt(sphone);
        String password = req.getParameter("psd");
        PrintWriter out = resp.getWriter();  // PrintWriter to send HTML response
        
        Connection con = null;
        PreparedStatement pstmt = null;
        String str = "insert into student values(?, ?,?,?)";  // SQL query
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver class is loaded");

            // Establish a connection to the MySQL database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_servlets_crud", "root", "root");
            System.out.println("Connection established");

            // Create a PreparedStatement for executing the INSERT query with parameters
            pstmt = con.prepareStatement(str);
            System.out.println("Statement created");

            // Set parameter values in the prepared statement
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, phone);
            pstmt.setString(4, password);

            // Execute the INSERT query
            pstmt.executeUpdate();
            
            // Output HTML response indicating successful data insertion
            out.println("<html><body> " + name + " is saved with ID : " + id + "  </body></html>");
            System.out.println("Query executed successfully");
            System.out.println("Data inserted successfully");
            
        } catch (ClassNotFoundException | SQLException e) {
            // Output HTML response for duplicate ID error and print the stack trace
            out.println("<html><body> Data is Not Accepted because ID " + id
                    + " is duplicate, Try with another ID  </body></html>");
            e.printStackTrace();
        } finally {
            // Close the PreparedStatement and Connection in the 'finally' block to ensure they are closed
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
