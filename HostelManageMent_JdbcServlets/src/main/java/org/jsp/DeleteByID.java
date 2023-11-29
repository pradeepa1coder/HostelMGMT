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

public class DeleteByID extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get the 'sid' parameter from the request
        String ssid = req.getParameter("sid");
        int id = Integer.parseInt(ssid);  // Parse the parameter as an integer

        PrintWriter out = resp.getWriter();  // PrintWriter to send HTML response
        Connection con = null;  // Database connection
        PreparedStatement pstmt = null;  // Prepared statement for executing SQL queries

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the MySQL database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_servlets_crud?user=root&password=root");

            // Create a prepared statement for deleting a record with a specific ID from the 'student' table
            pstmt = con.prepareStatement("DELETE FROM student WHERE sid=?");

            // Set the parameter value in the prepared statement
            pstmt.setInt(1, id);

            // Execute the update query and get the result
            int res = pstmt.executeUpdate();

            // Output HTML response
            out.println("<html><body>");

            // Check the result of the update operation
            if (res > 0) {
                out.println("<h2>ID " + id + " is Successfully Deleted</h2>");
            } else {
                out.println("<h2>ID " + id + " is Not Found</h2>");
            }

            out.println("</body></html>");

        } catch (ClassNotFoundException | SQLException e) {
            // Print stack trace for any exceptions that occur during execution
            e.printStackTrace();
        } finally {
            // Close the PreparedStatement and Connection in the 'finally' block to ensure they are closed
            try {
                if (pstmt != null) {
                    pstmt.close();
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
