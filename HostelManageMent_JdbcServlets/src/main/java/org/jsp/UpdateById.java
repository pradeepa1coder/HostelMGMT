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

public class UpdateById extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Getting the 'sid' parameter from the request and converting it to an integer
        String sid = req.getParameter("sid");
        int id = Integer.parseInt(sid);

        PrintWriter out = resp.getWriter();  // PrintWriter to send HTML response to the client

        // Database logic

        Connection con = null;
        PreparedStatement pstmt = null;
        String qry = "select * from student where sid=?";
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_servlets_crud?user=root&password=root");
            pstmt = con.prepareStatement(qry);

            // Set the parameter value in the prepared statement
            pstmt.setInt(1, id);

            // Execute the SELECT query
            rs = pstmt.executeQuery();

            out.println("<html><body>");

            // Check if the result set has any data
            if (rs.next()) {
                // Output details of the found record
                out.println("<h2>ID: " + rs.getInt(1) + "</h2>");
                out.println("<h2>Name: " + rs.getString(2) + "</h2>");
                out.println("<h2>Phone: " + rs.getInt(3) + "</h2>");
                out.println("<h2>Email: " + rs.getString(4) + "</h2>");

                // Get the ID value for creating a link to the update page
                int paramValue = rs.getInt(1);

                out.println("<br>");
                // Create a link to the update page with the ID as a parameter
                out.println("<a href='update1.jsp?paramName=" + paramValue + "'>Click here to go to the Update page</a>");
            } else {
                // Output message if the record with the specified ID is not found
                out.println("<h2>ID " + id + " is Not Found</h2>");
            }

            out.println("</body></html>");

        } catch (Exception e) {
            // Print the stack trace if an exception occurs
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
