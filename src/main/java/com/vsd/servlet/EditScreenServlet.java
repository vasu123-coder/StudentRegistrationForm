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

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
	private static final String query = "SELECT STUD_NAME,REGNO,BRANCH,GRAD_YEAR,PRIMARY_SKILL FROM STUDENTREG WHERE REGNO=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw =res.getWriter();
		//set content Type
		res.setContentType("text/html");
        // get the id
		String regno = req.getParameter("regno");
		
		// load JDBC Driver
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException cnf) {
		cnf.printStackTrace();
	}
	try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##vasu8278","vasu8278");
			PreparedStatement ps = con.prepareStatement(query);
						
			){
		ps.setString(1, regno);
		ResultSet rs = ps.executeQuery();
		rs.next();
		pw.println("<form action='editurl?id="+regno+"' method='post'>");
		pw.println("<table align='center'>");
		pw.println("<tr>");
		pw.println("<td>Student Name</td>");
		pw.println("<td><input type='text' name='stud_name' value='"+rs.getString(1)+"'></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>Registration Number</td>");
		pw.println("<td><input type='text' name='regno' value='"+rs.getString(2)+"'></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>Branch</td>");
		pw.println("<td><input type='text' name='branch' value='"+rs.getString(3)+"'></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>Graduation Year</td>");
		pw.println("<td><input type='text' name='grad_year' value='"+rs.getInt(4)+"'></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>Primary Skill</td>");
		pw.println("<td><input type='text' name='primary_skill' value='"+rs.getString(5)+"'></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td><input type='submit' value= 'Edit'></td>");
		pw.println("<td><input type='reset' value= 'cancel'></td>");

		pw.println("</tr>");
		pw.println("</table>");
		pw.println("</form>");
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
