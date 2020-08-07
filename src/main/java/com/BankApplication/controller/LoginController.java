package com.BankApplication.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log=Logger.getLogger(LoginController.class.getName());
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Declarations
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		String key = "Sonik";
		String sql = "select * from login where uname=? and pass=?";
		String url = "jdbc:mysql://localhost:3306/sonik";
		String username = "root";
		String password = "sonik";
		
		try {
			//LOGGER
			FileHandler fh= new FileHandler("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Logs\\Log.txt",true);
			log.addHandler(fh);
			SimpleFormatter formatter=new SimpleFormatter();
			fh.setFormatter(formatter);
			log.info("Application Started");
			
			//JDBC
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, uname);
			st.setString(2, pass);

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				log.info("User successfully signed in!");
				// Creating a session for security
				HttpSession session = request.getSession();
				session.setAttribute("key", key);

				// Setting success status for ajax call
				response.setStatus(200);
			}
			else {
				log.warning("Invalid credentials");
				// Setting forbidden status
				response.setStatus(403);
			}
		} catch (Exception e) {
			log.severe("Error:"+e);
		}
		
	}
	
}
