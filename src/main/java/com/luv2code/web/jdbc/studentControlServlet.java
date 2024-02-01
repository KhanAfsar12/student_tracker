package com.luv2code.web.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class studentControlServlet
 */
@WebServlet("/studentControlServlet")
public class studentControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private studentDButil studentDButil;
       @Resource(name="jdbc/web-student-tracker")
       
       private DataSource dataSource;
    
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			studentDButil = new studentDButil(dataSource);
		} catch (Exception e) {
               throw new ServletException();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	

}
