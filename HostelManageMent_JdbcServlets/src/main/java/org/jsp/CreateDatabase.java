package org.jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
			// in src folder
			//module-info.java we should delete else we may get exception
			// Main method, the entry point of the program
    public static void main(String[] args) {
        Connection con = null; // Connection object for database connection
        Statement st = null; // Statement object for executing SQL queries

        String qry = "create database jdbc_servlets_crud";// provide name:jdbc_crud

        try {
            // Load the MySQL JDBC driver. It's important to include the driver in the classpath.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the MySQL database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");

            // Create a Statement object from the Connection
            st = con.createStatement();

            // Execute the SQL query to create the database
            st.execute(qry);

            // execute() is return true if our query is DQL type because,
            // DQL always returns some data, any other like DDL, DML will never
            // returns anything, so in that scenario execute() method always return false
            System.out.println("DATABASE IS CREATED WITH NAME : jdbc_crud");
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions (e.g., ClassNotFoundException for driver loading, SQLException for database operations)
            e.printStackTrace();
        } finally {
            try {
                // Close the Statement and Connection objects to free up resources
                if (st != null) {
                    st.close();
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
/*
 * Documentation:
 * 
 * 
 * in JDBC WE HAVE TO WRITE OUR OWN PROGRAM TO CREATE DATABASE &
 * WE HAVE TO WRITE OUR OWN PROGRAM TO CREATE TABLE
 * 
 * 
 * =>we can create our JBC project in 2 ways
 *  1.Java Project 2.Maven Project 
 *  
 * [1]=> in case of Java Application we need to add Driver.jar(old version) file or
 * mysql-connector-java-8.0.28.jar(new version) explicitly steps to add jar file
 * in our class path. 
 * =>we can do in 2 ways :
 * 1.explicitly adding jar file ->Right
 * click on project->Build path->configure build path->libraries->class
 * path...then add External jars(select jar file from you hard
 * memory)->apply,apply and close 
 * [ this jar file we can download from maven
 * repositoy**even no need to extract]
 * 
 * 2. implicitly adding jar file(always recommended) -> first right click on
 * project ,->new ,create new folder name it for this as a lib -> copy the jar file from
 * your hard memory and paste it to the lib folder =>->Right click on
 * project->Build path->configure build path->libraries->class path...then add
 * jars(select jar file from your project->lib)->apply,apply and close
 * 
 * [2]=> in case of Maven Application,we will get a pom.xml file ,there you can 
 * add mysql-connector-java-8.0.28 dependency directly ans save it ,
 * so that no need to add any jar files in externally
 * 
 * 
 */
