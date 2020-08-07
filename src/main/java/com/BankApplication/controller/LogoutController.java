package com.BankApplication.controller;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log=Logger.getLogger(LoginController.class.getName());
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			//LOGGER
			FileHandler fh= new FileHandler("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Logs\\Log.txt",true);
			log.addHandler(fh);
			SimpleFormatter formatter=new SimpleFormatter();
			fh.setFormatter(formatter);
			log.info("Application closed");
			log.info("----------------------------------------------------------------------------------------");
		}
		catch(Exception e) {
			log.severe("Error:"+e);
		}
		// Removing key attribute
		session.removeAttribute("key");
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

}
