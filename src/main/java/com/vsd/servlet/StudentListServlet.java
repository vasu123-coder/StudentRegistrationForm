package com.vsd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/studentlist")
public class StudentListServlet extends HttpServlet {
	private static final String query = "SELECT STUD_NAME,REGNO,BRANCH,GRAD_YEAR,PRIMARY_SKILL FROM STUDENTREG";
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			//get PrintWriter
			PrintWriter pw =res.getWriter();
			//set content Type
			res.setContentType("text/html");
	
			
			// load JDBC Driver
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##vasu8278","vasu8278");
				PreparedStatement ps = con.prepareStatement(query);
							
				){
			
			ResultSet rs = ps.executeQuery();
			
			pw.println("<table border='1' align='center'>");
			pw.println("<tr>");
			pw.println("<th>Student Name</th>");
			pw.println("<th>Registration Number</th>");
			pw.println("<th>Branch</th>");
			pw.println("<th>Graduation Year</th>");
			pw.println("<th>Primary Skill</th>");
			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			 while(rs.next()) {
				 pw.println("<tr>");
					pw.println("<td>"+rs.getString(1)+"</td>");
					pw.println("<td>"+rs.getString(2)+"</td>");
					pw.println("<td>"+rs.getString(3)+"</td>");
					pw.println("<td>"+rs.getInt(4)+"</td>");
					pw.println("<td>"+rs.getString(5)+"</td>");
					pw.println("<td><a href='editScreen?regno="+rs.getString(2)+"'>Edit</a></td>");
					pw.println("<td><a href='deleteurl?regno="+rs.getString(2)+"'>Delete</a></td>");
					pw.println("</tr>");
			 }
			pw.println("</table>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}catch (Exception e1) {
			e1.printStackTrace();
			pw.println("<h1>"+e1.getMessage()+"</h1>");
		}
		pw.println("<a href='home.html'>Home</a>");

		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doGet(req, res);
		}
}
