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

@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
	private static final String query = "DELETE FROM STUDENTREG WHERE REGNO=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw =res.getWriter();
		//set content Type
		res.setContentType("text/html");
        // get the id
		String regno = req.getParameter("regno");
		//get the edit data we want to edit
		
		// load JDBC Driver
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException cnf) {
		cnf.printStackTrace();
	}
	try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##vasu8278","vasu8278");
			PreparedStatement ps = con.prepareStatement(query);	){
		
		
		ps.setString(1, regno);
		int count = ps.executeUpdate();
		if(count == 1) {
			pw.print("<h2>Record is Deleted successfully</h2>");
		}else {
			pw.print("<h2>Record is Not deleted successfully</h2>");

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
