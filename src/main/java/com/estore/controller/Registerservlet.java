package com.estore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Servlet implementation class Registerservlet
 */
public class Registerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registerservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("txtName");

		String email = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");
        response.setContentType("text/html");
        String LoginTime = new Date().toString();
        System.out.println("User details:"+name+" "+email+" "+LoginTime);

        String htmlRes = "Welcome!you are registed succssfully "+name+" "+"You logged in at:"+LoginTime;
		String htmlResponse = "<center><h2>Welcome to Hello Servlet</h3></center>";
		
		//printwriter
		PrintWriter out = response.getWriter();
		out.print(htmlRes);;
	}
	/*
	 * 
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub String name = request.getParameter("txtName");
	 * 
	 * String email = request.getParameter("txtEmail"); String password =
	 * request.getParameter("txtPassword"); response.setContentType("text/html");
	 * String LoginTime = new Date().toString();
	 * System.out.println("User details:"+name+" "+email+" "+LoginTime);
	 * 
	 * String htmlRes =
	 * "Welcome!you are registed succssfully "+name+" "+"You logged in at:"
	 * +LoginTime; String htmlResponse =
	 * "<center><h2>Welcome to Hello Servlet</h3></center>";
	 * 
	 * //printwriter PrintWriter out = response.getWriter(); out.print(htmlRes);; }
	 */


}
