package com.estore.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import com.estore.db.DB;
import com.estore.model.Customer;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// admin login
	private final String email_a = "admin@store.com";
	private final String password_a = "admin@123";

	// cookie
	private final String name = "Karuna";
	private final int totalSales = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * protected void service(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub
	 * 
	 * String email = request.getParameter("txtEmail"); String password =
	 * request.getParameter("txtPassword");
	 * System.out.println("User details:"+email+" "+password);
	 * response.setContentType("text/html"); String LoginTime = new
	 * Date().toString(); String htmlRes =
	 * "Welcome! "+email+" "+"You logged in at:"+LoginTime; String htmlResponse =
	 * "<center><h2>Welcome to Hello Servlet</h3></center>";
	 * 
	 * //printwriter PrintWriter out = response.getWriter(); out.print(htmlRes);; }
	 */

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String email = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");
		System.out.println("User details:" + email + " " + password);
				String message = " ";
		if (email.equalsIgnoreCase(email_a) && password.equalsIgnoreCase(password_a)) {
			// cookies
			Cookie cokkie1 = new Cookie("KEY_NAME", name);
			Cookie cokkie2 = new Cookie("KEY_SALES", String.valueOf(totalSales));
			response.addCookie(cokkie1);
			response.addCookie(cokkie2);

			message = "<p>Welcome to Home Page <br><a href='HOME'>Click to navigate to home page</>";
			RequestDispatcher rs = request.getRequestDispatcher("home.html");
			rs.forward(request, response);
		} else {
			message = "User Login";
			RequestDispatcher rs = request.getRequestDispatcher("ProductList");
			rs.forward(request, response);
		}

		//response.setContentType("text/html");
		String LoginTime = new Date().toString();
		String htmlRes = "Welcome! " + email + " " + "You logged in at:" + LoginTime + " " + message;
		//String htmlResponse = "<center><h2>Welcome to Hello Servlet</h3></center>";
		
		 //PrintWriter  out = response.getWriter(); 
		// out.print(htmlRes);;

	}
}
