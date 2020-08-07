package com.BankApplication.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadController
 */
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log=Logger.getLogger(LoginController.class.getName());
	static boolean DbInsertion(String arr[]) {
		// Declarations
		String sql = "insert into data values(?,?,?,?,?,?,?,?)";
		String sql2 = "select * from data where Account_number= ? ORDER BY Date_ DESC,Time_ DESC LIMIT 1";
		String url = "jdbc:mysql://localhost:3306/sonik";
		String username = "root";
		String password = "sonik";
		String balance = new String();

		try {
			//LOGGER
			FileHandler fh= new FileHandler("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Logs\\Log.txt",true);
			log.addHandler(fh);
			SimpleFormatter formatter=new SimpleFormatter();
			fh.setFormatter(formatter);
			log.info("Values inserted in Database");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);

			// Finding running balance for the particular account
			PreparedStatement st2 = con.prepareStatement(sql2);
			st2.setString(1, arr[1]);
			ResultSet rs2 = st2.executeQuery();
			rs2.next();
			balance = rs2.getString("Running_balance");

			// Calculating Running Balance
			Float rb = Float.parseFloat(balance);

			if (arr[5].equals("0")) {
				Float credit = Float.parseFloat(arr[6]);
				rb += credit;
			} else {
				Float with = Float.parseFloat(arr[5]);
				rb -= with;
			}

			// Inserting values
			PreparedStatement st = con.prepareStatement(sql);
			String temp = Float.toString(rb);

			st.setString(1, arr[0]);
			st.setString(2, arr[1]);
			st.setString(3, arr[2]);
			st.setString(4, arr[3]);
			st.setString(5, arr[4]);
			st.setString(6, arr[5]);
			st.setString(7, arr[6]);
			st.setString(8, temp);

			int flag = st.executeUpdate();

			if (flag == 1)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	static void FileProcess() {
		// Loading the file
		File filename = new File("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Work\\temp.csv");
		String data = "";

		// File iterations
		try {
			
			//LOGGER
			FileHandler fh= new FileHandler("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Logs\\Log.txt",true);
			log.addHandler(fh);
			SimpleFormatter formatter=new SimpleFormatter();
			fh.setFormatter(formatter);			
			//Traversing file
			BufferedReader br = new BufferedReader(new FileReader(filename));
			br.readLine();
			log.info("File traversing started");
			while ((data = br.readLine()) != null) {
				String arr[] = data.split("\\,");

				boolean flag = DbInsertion(arr);

				if (flag) {
					System.out.println("Values Inserted");
				}
			}
			br.close();
		} catch (IOException e) {
			log.severe("Error:"+e);
		} finally {
			filename.delete();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Getting the file from the client
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		try {
			//LOGGER
			FileHandler fh= new FileHandler("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Logs\\Log.txt",true);
			log.addHandler(fh);
			SimpleFormatter formatter=new SimpleFormatter();
			fh.setFormatter(formatter);
			log.info("File Fetched");
			
			//File Processing
			List<FileItem> f = sf.parseRequest(request);
			for (FileItem i : f) {
				i.write(new File("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Work\\" + "temp.csv"));
				FileProcess();
				JOptionPane.showMessageDialog(null, "File Uploaded and database has been updated successfully");
				response.sendRedirect("home.jsp");

			}

		} catch (Exception e) {
			log.severe("Error:"+e);
		}
	}

}
