package com.estore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String htmlResponse = "<center><h2>Welcome to Home</h3></center>";
		
		//printwriter
		PrintWriter out = response.getWriter();
		out.print(htmlResponse);
		
		/*
		 * Cookie[] cookieArray = request.getCookies(); for(Cookie cookie: cookieArray)
		 * { if(cookie.getName().equals("KEY_NAME"))
		 * out.print(cookie.getName()+" - "+cookie.getValue());
		 * 
		 * out.print(cookie.getName()+" - "+cookie.getValue());
		 * 
		 * }
		 */

	}

}
