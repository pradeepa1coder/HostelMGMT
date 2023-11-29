package org.jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {

        // Declare variables for database connection and statement
        Connection con = null;
        Statement st = null;

        // SQL query to create a table named 'student'
        String str = "CREATE TABLE student (" +
                     "  sid INT(4) PRIMARY KEY," +
                     "  sname VARCHAR(45) NOT NULL," +
                     "  phone BIGINT(10) UNIQUE," +
                     "  email VARCHAR(20)" +
                     ");";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_servlets_crud", "root", "root");

            // Create a Statement object for executing SQL queries
            st = con.createStatement();

            // Execute the SQL query to create the 'student' table
            st.execute(str);

            // Print a message indicating that the table is created successfully
            System.out.println("Table is created");

        } catch (ClassNotFoundException | SQLException e) {
            // Print stack trace for any exceptions that occur during execution
            e.printStackTrace();
        } finally {
            // Close the Statement and Connection in the 'finally' block to ensure they are closed
            if (st != null) {
                try {
                    st.close();
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
