package org.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindById extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get the 'sid' parameter from the request
        String sid = req.getParameter("sid");
        int id = Integer.parseInt(sid);  // Parse the parameter as an integer

        PrintWriter out = resp.getWriter();  // PrintWriter to send HTML response

        // Database logic
        Connection con = null;
        PreparedStatement pstmt = null;
        String qry = "SELECT * FROM student WHERE sid=?";
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the MySQL database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_servlets_crud?user=root&password=root");

            // Create a PreparedStatement for executing the SELECT query with a parameter
            pstmt = con.prepareStatement(qry);

            // Set the parameter value in the prepared statement
            pstmt.setInt(1, id);

            // Execute the query and get the result set
            rs = pstmt.executeQuery();

            // Output HTML response
            if (rs.next()) {
                out.println("<html><body> " + rs.getInt(1) + "  </body></html>");
                out.println("<html><body> " + rs.getString(2) + " </body></html>");
                out.println("<html><body> " + rs.getInt(3) + " </body></html>");
                out.println("<html><body> " + rs.getString(4) + " </body></html>");
            } else {
                out.println("<html><body> " + id + " is Not Found </body></html>");
            }

        } catch (Exception e) {
            // Print stack trace for any exceptions that occur during execution
            e.printStackTrace();
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection in the 'finally' block to ensure they are closed
            try {
                if (rs != null) {
                    rs.close();
                }
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
