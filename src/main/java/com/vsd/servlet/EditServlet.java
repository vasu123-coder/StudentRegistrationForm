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

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	private static final String query = "UPDATE STUDENTREG SET STUD_NAME=?,REGNO=?,BRANCH=?,GRAD_YEAR=?,PRIMARY_SKILL=? WHERE REGNO=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw =res.getWriter();
		//set content Type
		res.setContentType("text/html");
        // get the id
		String regno = req.getParameter("regno");
		//get the edit data we want to edit
		String studentname = req.getParameter("stud_name");
		String regNo = req.getParameter("regno");
		String Br = req.getParameter("branch");
		int gradYear = Integer.parseInt(req.getParameter("grad_year"));
		String pskill = req.getParameter("primary_skill");
		
		// load JDBC Driver
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException cnf) {
		cnf.printStackTrace();
	}
	try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##vasu8278","vasu8278");
			PreparedStatement ps = con.prepareStatement(query);	){
		
		ps.setString(1, studentname);
		ps.setString(2, regNo);
		ps.setNString(3, Br);
		ps.setInt(4, gradYear);
		ps.setString(5, pskill);
		ps.setString(6, regno);
		int count = ps.executeUpdate();
		if(count == 1) {
			pw.print("<h2>Record is updated successfully</h2>");
		}else {
			pw.print("<h2>Record is Not updated successfully</h2>");

		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		pw.println("<h1>"+e.getMessage()+"</h1>");
	}catch (Exception e1) {
		e1.printStackTrace();
		pw.println("<h1>"+e1.getMessage()+"</h1>");
	}
	pw.println("<a href='home.html'>Home</a>");
	pw.println("<br>");
	pw.println("<a href='studentlist'>Student List</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
