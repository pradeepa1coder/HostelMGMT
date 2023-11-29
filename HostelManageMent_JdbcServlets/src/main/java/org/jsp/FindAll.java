package org.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // PrintWriter to send HTML response
        PrintWriter out = resp.getWriter();
        
        // Database connection and query variables
        Connection con = null;
        String qry = "SELECT * FROM student";
        PreparedStatement pstmt = null;

        // ResultSet to store the result of the query
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the MySQL database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_servlets_crud?user=root&password=root");

            // Create a PreparedStatement for executing the SELECT query
            pstmt = con.prepareStatement(qry);

            // Execute the query and get the result set
            rs = pstmt.executeQuery();

            // Output HTML response
            out.println("<html><body>");
            out.println("<h1>Student Records</h1>");
            
            // Create a table to display the records
            out.println("<table border='1'>");
            
            // Table header
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>NAME</th>");
            out.println("<th>PHONE</th>");
            out.println("<th>EMAIL</th>");
            out.println("</tr>");
            out.println("</thead>");
            
            // Table body
            out.println("<tbody>");

            // Iterate through the result set and display each record in a table row
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt(1) + "</td>");
                out.println("<td>" + rs.getString(2) + "</td>");
                out.println("<td>" + rs.getInt(3) + "</td>");
                out.println("<td>" + rs.getString(4) + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</body></html>");

        } catch (ClassNotFoundException | SQLException e) {
            // Print stack trace for any exceptions that occur during execution
            e.printStackTrace();
        } finally {
            // Close the PreparedStatement, ResultSet, and Connection in the 'finally' block to ensure they are closed
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
