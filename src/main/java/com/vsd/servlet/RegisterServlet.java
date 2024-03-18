package com.vsd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final String query = "INSERT INTO STUDENTREG"
			+ "(STUD_NAME,REGNO,BRANCH,GRAD_YEAR,PRIMARY_SKILL) VALUES(?,?,?,?,?)";
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			//get PrintWriter
			PrintWriter pw =res.getWriter();
			//set content Type
			res.setContentType("text/html");
			// GET THE STUDENT INFO
			String stud_Name = req.getParameter("std_name");
			String regno = req.getParameter("regno");
			String branch = req.getParameter("branch");
			int grad_year = Integer.parseInt(req.getParameter("grad_year"));
			String primary_skill = req.getParameter("primary_skill");
			// load JDBC Driver
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##vasu8278","vasu8278");
				PreparedStatement ps = con.prepareStatement(query);
							
				){
			ps.setString(1, stud_Name);
			ps.setString(2, regno);
			ps.setString(3, branch);
			ps.setInt(4, grad_year);
			ps.setString(5, primary_skill);
			int count = ps.executeUpdate();
			if(count == 1) {
				pw.println("<h2>Student is registered Successfully");
			}else {
				pw.println("<h2>Student is not registered Successfully");
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

