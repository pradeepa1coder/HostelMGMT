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

public class UpdateById1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Getting parameters from the request
		String sid = req.getParameter("num");
		int ssid = Integer.parseInt(sid);
		String name = req.getParameter("nm");
		String sphone = req.getParameter("ph");
		int phone = Integer.parseInt(sphone);
		String semail = req.getParameter("psd");
		PrintWriter out = resp.getWriter();

		Connection con = null;
		PreparedStatement st = null;
		String qry = "UPDATE student SET sname=?, phone=?, email=? WHERE sid=?";
		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish a connection to the MySQL database
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_servlets_crud", "root", "root");
			// Create a prepared statement for the UPDATE query
			st = con.prepareStatement(qry);

			// Set parameter values in the prepared statement
			st.setString(1, name);
			st.setInt(2, phone);
			st.setString(3, semail);
			st.setInt(4, ssid);

			// Execute the UPDATE query
			int res = st.executeUpdate();
			if (res > 0)
				out.println("Updated successfully!!");
		} catch (ClassNotFoundException | SQLException e) {
			// Print the stack trace if an exception occurs
			e.printStackTrace();
		} finally {
			// Close the PreparedStatement and Connection in the 'finally' block to ensure
			// they are closed
			try {
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// Print the stack trace if an exception occurs while closing resources
				e.printStackTrace();
			}
		}
	}
}
