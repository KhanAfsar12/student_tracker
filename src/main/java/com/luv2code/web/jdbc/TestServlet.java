package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get up the printWriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		//Get a connection to a database
		Connection myConn = null;
		Statement myStm = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
		//Create a SQL Statement
			
			String sql = "select * from student";
			myStm = myConn.createStatement();
			
		//Execute SQL Query
	    	myRs = myStm.executeQuery(sql);
	    	
		//Process the Result set
	    	while (myRs.next()) {
			String email = myRs.getString("email");
			String id = myRs.getString("id");
			out.println(email);
			out.println(id);
			}
		}
		catch (Exception e) {
            e.printStackTrace();
		}
	}

}
