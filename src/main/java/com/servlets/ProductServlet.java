package com.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private Connection con;
	private PreparedStatement stmt;
	
	public void init(ServletConfig config) {
		
		ServletContext servletContext = config.getServletContext();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(servletContext.getInitParameter("dbUrl"),servletContext.getInitParameter("dbUser"),
					servletContext.getInitParameter("dbPassword"));
			
			PreparedStatement statement = con.prepareStatement("insert into product values(?,?,?,?)");
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public void destroy() {
		
	  try {
		  con.close();
		stmt.close();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}	
	}

}
