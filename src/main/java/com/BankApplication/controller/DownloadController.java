package com.BankApplication.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BankApplication.model.AccountsModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



/**
 * Servlet implementation class DownloadController
 */
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log=Logger.getLogger(LoginController.class.getName());
	
	// Searching with account number
		static ArrayList<AccountsModel> searchwithact(String actno) {
			// Declarations for MySQL
			ArrayList<AccountsModel> list = new ArrayList<AccountsModel>();
			String url = "jdbc:mysql://localhost:3306/sonik";
			String username = "root";
			String password = "sonik";
			String sql = "select * from data where Account_number= ? ORDER BY Date_ ASC,Time_ ASC ";

			// Declaration for pdf
			String pdf = "C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Generated files\\Records.pdf";
			Document document = new Document();

			// Declaration for Excel
			WritableWorkbook wb;

			try {
				//LOGGER
				FileHandler fh= new FileHandler("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Logs\\Log.txt",true);
				log.addHandler(fh);
				SimpleFormatter formatter=new SimpleFormatter();
				fh.setFormatter(formatter);
				log.info("Searching with account number function called");
				
				//JDBC
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, username, password);

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, actno);

				// Generating Excel file
				wb = Workbook.createWorkbook(
						new File("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Generated files\\Records.xls"));
				WritableSheet wsheet = wb.createSheet("First Sheet", 0);
				Label label = new Label(0, 2, "A label record");
				wsheet.addCell(label);

				// Setting column names for Excel
				label = new Label(0, 0, "Txn_ref_number");
				wsheet.addCell(label);
				label = new Label(1, 0, "Account_number");
				wsheet.addCell(label);
				label = new Label(2, 0, "Date");
				wsheet.addCell(label);
				label = new Label(3, 0, "Time");
				wsheet.addCell(label);
				label = new Label(4, 0, "Description");
				wsheet.addCell(label);
				label = new Label(5, 0, "Withdrawals");
				wsheet.addCell(label);
				label = new Label(6, 0, "Credit");
				wsheet.addCell(label);
				label = new Label(7, 0, "Running_Balance");
				wsheet.addCell(label);
				int i = 0, j = 1;

				// Generating PDF
				PdfWriter.getInstance(document, new FileOutputStream(pdf));
				document.open();
				PdfPTable table = new PdfPTable(8);
				PdfPCell c1 = new PdfPCell(new Phrase("Txn_ref_number"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Account_Number"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Date"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Time"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Description"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Withdrawals"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Credit"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Running Balance"));
				table.addCell(c1);
				table.setHeaderRows(1);

				// Generating Csv
				PrintWriter pw = new PrintWriter(
						new File("C:\\\\Users\\\\Sonik\\\\Desktop\\\\Virtusa Project\\\\Generated files\\\\Records.csv"));
				StringBuilder sb = new StringBuilder();

				sb.append("Txn_ref_number");
				sb.append(",");
				sb.append("Account_number");
				sb.append(",");
				sb.append("Date");
				sb.append(",");
				sb.append("Time");
				sb.append(",");
				sb.append("Description");
				sb.append(",");
				sb.append("Withdrawals");
				sb.append(",");
				sb.append("Credit");
				sb.append(",");
				sb.append("Running_Balance");
				sb.append("\r\n");

				// Executing the mysql query
				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					i = 0;
					AccountsModel obj = new AccountsModel();

					label = new Label(i++, j, rs.getString(1));
					wsheet.addCell(label);
					sb.append(rs.getString(1));
					sb.append(",");
					table.addCell(rs.getString(1));
					obj.setTxn_ref_number(rs.getString(1));

					label = new Label(i++, j, rs.getString(2));
					wsheet.addCell(label);
					sb.append(rs.getString(2));
					sb.append(",");
					table.addCell(rs.getString(2));
					obj.setAccount_number(rs.getString(2));

					label = new Label(i++, j, rs.getString(3));
					wsheet.addCell(label);
					sb.append(rs.getString(3));
					sb.append(",");
					table.addCell(rs.getString(3));
					obj.setDate_(rs.getString(3));

					label = new Label(i++, j, rs.getString(4));
					wsheet.addCell(label);
					sb.append(rs.getString(4));
					sb.append(",");
					table.addCell(rs.getString(4));
					obj.setTime_(rs.getString(4));

					label = new Label(i++, j, rs.getString(5));
					wsheet.addCell(label);
					sb.append(rs.getString(5));
					sb.append(",");
					table.addCell(rs.getString(5));
					obj.setDesciption_(rs.getString(5));

					label = new Label(i++, j, rs.getString(6));
					wsheet.addCell(label);
					sb.append(rs.getString(6));
					sb.append(",");
					table.addCell(rs.getString(6));
					obj.setWithdrawals(rs.getString(6));

					label = new Label(i++, j, rs.getString(7));
					wsheet.addCell(label);
					sb.append(rs.getString(7));
					sb.append(",");
					table.addCell(rs.getString(7));
					obj.setCredit(rs.getString(7));

					label = new Label(i++, j, rs.getString(8));
					wsheet.addCell(label);
					sb.append(rs.getString(8));
					sb.append("\r\n");
					table.addCell(rs.getString(8));
					obj.setRunning_Balance(rs.getString(8));

					// Adding new line to pdf
					document.add(new Paragraph(" "));

					// Adding object to list
					list.add(obj);

					// Adding new line to excel
					j++;
				}
				// Adding table to pdf document
				document.add(table);
				document.close();

				// Adding contents to csv
				pw.write(sb.toString());
				pw.close();

				// Adding contents to Excel
				wb.write();
				wb.close();

			} catch (Exception e) {
				log.severe("Error:"+e);
			}
			return list;
		}
       
		// Searching with the date range
		static ArrayList<AccountsModel> searchwithrange(String daterange) {
			// Declarations for mysql
			ArrayList<AccountsModel> list = new ArrayList<AccountsModel>();
			String url = "jdbc:mysql://localhost:3306/sonik";
			String username = "root";
			String password = "sonik";
			String sql = "select * from data where Date_ between ? and ? ORDER BY Date_ ASC,Time_ ASC ";

			// Generate PDF
			String pdf = "C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Generated files\\Records.pdf";
			Document document = new Document();

			// Generate Excel file
			WritableWorkbook wb;

			try {
				//LOGGER
				FileHandler fh= new FileHandler("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Logs\\Log.txt",true);
				log.addHandler(fh);
				SimpleFormatter formatter=new SimpleFormatter();
				fh.setFormatter(formatter);
				log.info("Searching with Date range function called");
				
				//JDBC
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, username, password);

				String s1 = daterange.substring(0, 10);
				String s2 = daterange.substring(12);
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, s1);
				st.setString(2, s2);

				// Generating Excel file
				wb = Workbook.createWorkbook(
						new File("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Generated files\\Records.xls"));
				WritableSheet wsheet = wb.createSheet("First Sheet", 0);
				Label label = new Label(0, 2, "A label record");
				wsheet.addCell(label);

				// Setting column names for Excel
				label = new Label(0, 0, "Txn_ref_number");
				wsheet.addCell(label);
				label = new Label(1, 0, "Account_number");
				wsheet.addCell(label);
				label = new Label(2, 0, "Date");
				wsheet.addCell(label);
				label = new Label(3, 0, "Time");
				wsheet.addCell(label);
				label = new Label(4, 0, "Description");
				wsheet.addCell(label);
				label = new Label(5, 0, "Withdrawals");
				wsheet.addCell(label);
				label = new Label(6, 0, "Credit");
				wsheet.addCell(label);
				label = new Label(7, 0, "Running_Balance");
				wsheet.addCell(label);
				int i = 0, j = 1;

				// Generating PDF
				PdfWriter.getInstance(document, new FileOutputStream(pdf));
				document.open();
				PdfPTable table = new PdfPTable(8);
				PdfPCell c1 = new PdfPCell(new Phrase("Txn_ref_number"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Account_Number"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Date"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Time"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Description"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Withdrawals"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Credit"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Running Balance"));
				table.addCell(c1);
				table.setHeaderRows(1);

				// Generating Csv
				PrintWriter pw = new PrintWriter(
						new File("C:\\\\Users\\\\Sonik\\\\Desktop\\\\Virtusa Project\\\\Generated files\\\\Records.csv"));
				StringBuilder sb = new StringBuilder();

				sb.append("Txn_ref_number");
				sb.append(",");
				sb.append("Account_number");
				sb.append(",");
				sb.append("Date");
				sb.append(",");
				sb.append("Time");
				sb.append(",");
				sb.append("Description");
				sb.append(",");
				sb.append("Withdrawals");
				sb.append(",");
				sb.append("Credit");
				sb.append(",");
				sb.append("Running_Balance");
				sb.append("\r\n");

				// Executing mysql query
				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					i = 0;
					AccountsModel obj = new AccountsModel();

					label = new Label(i++, j, rs.getString(1));
					wsheet.addCell(label);
					sb.append(rs.getString(1));
					sb.append(",");
					table.addCell(rs.getString(1));
					obj.setTxn_ref_number(rs.getString(1));

					label = new Label(i++, j, rs.getString(2));
					wsheet.addCell(label);
					sb.append(rs.getString(2));
					sb.append(",");
					table.addCell(rs.getString(2));
					obj.setAccount_number(rs.getString(2));

					label = new Label(i++, j, rs.getString(3));
					wsheet.addCell(label);
					sb.append(rs.getString(3));
					sb.append(",");
					table.addCell(rs.getString(3));
					obj.setDate_(rs.getString(3));

					label = new Label(i++, j, rs.getString(4));
					wsheet.addCell(label);
					sb.append(rs.getString(4));
					sb.append(",");
					table.addCell(rs.getString(4));
					obj.setTime_(rs.getString(4));

					label = new Label(i++, j, rs.getString(5));
					wsheet.addCell(label);
					sb.append(rs.getString(5));
					sb.append(",");
					table.addCell(rs.getString(5));
					obj.setDesciption_(rs.getString(5));

					label = new Label(i++, j, rs.getString(6));
					wsheet.addCell(label);
					sb.append(rs.getString(6));
					sb.append(",");
					table.addCell(rs.getString(6));
					obj.setWithdrawals(rs.getString(6));

					label = new Label(i++, j, rs.getString(7));
					wsheet.addCell(label);
					sb.append(rs.getString(7));
					sb.append(",");
					table.addCell(rs.getString(7));
					obj.setCredit(rs.getString(7));

					label = new Label(i++, j, rs.getString(8));
					wsheet.addCell(label);
					sb.append(rs.getString(8));
					sb.append("\r\n");
					table.addCell(rs.getString(8));
					obj.setRunning_Balance(rs.getString(8));

					// Adding new line to pdf
					document.add(new Paragraph(" "));

					// Adding object to list
					list.add(obj);

					// Adding new line to excel
					j++;
				}
				// Adding table to pdf document
				document.add(table);
				document.close();

				// Adding contents to csv
				pw.write(sb.toString());
				pw.close();

				// Adding contents to Excel
				wb.write();
				wb.close();

			} catch (Exception e) {
				log.severe("Error:"+e);
			}
			return list;
		}
		
		// Searching with date
		static ArrayList<AccountsModel> searchwithdate(String date) {
			// Declarations for mysql
			ArrayList<AccountsModel> list = new ArrayList<AccountsModel>();
			String url = "jdbc:mysql://localhost:3306/sonik";
			String username = "root";
			String password = "sonik";
			String sql = "select * from data where Date_= ? ORDER BY Date_ ASC,Time_ ASC ";

			// Generate PDF
			String pdf = "C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Generated files\\Records.pdf";
			Document document = new Document();

			// Generate Excel file
			WritableWorkbook wb;
			try {
				//LOGGER
				FileHandler fh= new FileHandler("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Logs\\Log.txt",true);
				log.addHandler(fh);
				SimpleFormatter formatter=new SimpleFormatter();
				fh.setFormatter(formatter);
				log.info("Searching with Date function called");
				
				//JDBC
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, username, password);

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, date);

				// Generating Excel file
				wb = Workbook.createWorkbook(
						new File("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Generated files\\Records.xls"));
				WritableSheet wsheet = wb.createSheet("First Sheet", 0);
				Label label = new Label(0, 2, "A label record");
				wsheet.addCell(label);

				// Setting column names for Excel
				label = new Label(0, 0, "Txn_ref_number");
				wsheet.addCell(label);
				label = new Label(1, 0, "Account_number");
				wsheet.addCell(label);
				label = new Label(2, 0, "Date");
				wsheet.addCell(label);
				label = new Label(3, 0, "Time");
				wsheet.addCell(label);
				label = new Label(4, 0, "Description");
				wsheet.addCell(label);
				label = new Label(5, 0, "Withdrawals");
				wsheet.addCell(label);
				label = new Label(6, 0, "Credit");
				wsheet.addCell(label);
				label = new Label(7, 0, "Running_Balance");
				wsheet.addCell(label);
				int i = 0, j = 1;

				// Generating PDF
				PdfWriter.getInstance(document, new FileOutputStream(pdf));
				document.open();
				PdfPTable table = new PdfPTable(8);
				PdfPCell c1 = new PdfPCell(new Phrase("Txn_ref_number"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Account_Number"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Date"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Time"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Description"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Withdrawals"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Credit"));
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Running Balance"));
				table.addCell(c1);
				table.setHeaderRows(1);

				// Generating Csv
				PrintWriter pw = new PrintWriter(
						new File("C:\\\\Users\\\\Sonik\\\\Desktop\\\\Virtusa Project\\\\Generated files\\\\Records.csv"));
				StringBuilder sb = new StringBuilder();

				sb.append("Txn_ref_number");
				sb.append(",");
				sb.append("Account_number");
				sb.append(",");
				sb.append("Date");
				sb.append(",");
				sb.append("Time");
				sb.append(",");
				sb.append("Description");
				sb.append(",");
				sb.append("Withdrawals");
				sb.append(",");
				sb.append("Credit");
				sb.append(",");
				sb.append("Running_Balance");
				sb.append("\r\n");

				// Executing mysql query
				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					i = 0;
					AccountsModel obj = new AccountsModel();

					label = new Label(i++, j, rs.getString(1));
					wsheet.addCell(label);
					sb.append(rs.getString(1));
					sb.append(",");
					table.addCell(rs.getString(1));
					obj.setTxn_ref_number(rs.getString(1));

					label = new Label(i++, j, rs.getString(2));
					wsheet.addCell(label);
					sb.append(rs.getString(2));
					sb.append(",");
					table.addCell(rs.getString(2));
					obj.setAccount_number(rs.getString(2));

					label = new Label(i++, j, rs.getString(3));
					wsheet.addCell(label);
					sb.append(rs.getString(3));
					sb.append(",");
					table.addCell(rs.getString(3));
					obj.setDate_(rs.getString(3));

					label = new Label(i++, j, rs.getString(4));
					wsheet.addCell(label);
					sb.append(rs.getString(4));
					sb.append(",");
					table.addCell(rs.getString(4));
					obj.setTime_(rs.getString(4));

					label = new Label(i++, j, rs.getString(5));
					wsheet.addCell(label);
					sb.append(rs.getString(5));
					sb.append(",");
					table.addCell(rs.getString(5));
					obj.setDesciption_(rs.getString(5));

					label = new Label(i++, j, rs.getString(6));
					wsheet.addCell(label);
					sb.append(rs.getString(6));
					sb.append(",");
					table.addCell(rs.getString(6));
					obj.setWithdrawals(rs.getString(6));

					label = new Label(i++, j, rs.getString(7));
					wsheet.addCell(label);
					sb.append(rs.getString(7));
					sb.append(",");
					table.addCell(rs.getString(7));
					obj.setCredit(rs.getString(7));

					label = new Label(i++, j, rs.getString(8));
					wsheet.addCell(label);
					sb.append(rs.getString(8));
					sb.append("\r\n");
					table.addCell(rs.getString(8));
					obj.setRunning_Balance(rs.getString(8));

					// Adding new line to pdf
					document.add(new Paragraph(" "));

					// Adding object to list
					list.add(obj);

					// Adding new line to excel
					j++;
				}
				// Adding table to pdf document
				document.add(table);
				document.close();

				// Adding contents to csv
				pw.write(sb.toString());
				pw.close();

				// Adding contents to Excel
				wb.write();
				wb.close();

			} catch (Exception e) {
				log.severe("Error:"+e);
			}
			return list;
		}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//LOGGER
		
		try {
			//LOGGER
			FileHandler fh= new FileHandler("C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Logs\\Log.txt",true);
			log.addHandler(fh);
			SimpleFormatter formatter=new SimpleFormatter();
			fh.setFormatter(formatter);
			log.info("Download process started");
		}
		catch(Exception e){
			log.severe("Error:"+e);
		}
		
		// Getting the parameters and calling the respective functions
		int option = Integer.parseInt(request.getParameter("searchoption"));

		String searchcontent = request.getParameter("searchcontent");
		if (option == 1) {
			ArrayList<AccountsModel> list = searchwithact(searchcontent);
			HttpSession session = request.getSession();
			session.setAttribute("list", list);
			if (list.size() > 0){
				response.setStatus(200);
				log.info("Records found");
			}
			else {
				response.setStatus(404);
				log.warning("No records found");
			}
		} else if (option == 2) {
			ArrayList<AccountsModel> list = searchwithrange(searchcontent);
			HttpSession session = request.getSession();
			session.setAttribute("list", list);
			if (list.size() > 0){
				response.setStatus(200);
				log.info("Records found");
			}
			else {
				response.setStatus(404);
				log.warning("No records found");
			}
		} else {
			ArrayList<AccountsModel> list = searchwithdate(searchcontent);
			HttpSession session = request.getSession();
			session.setAttribute("list", list);
			if (list.size() > 0){
				response.setStatus(200);
				log.info("Records found");
			}
			else {
				response.setStatus(404);
				log.warning("No records found");
			}
		}
	}
}
