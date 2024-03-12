package com.estore.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.estore.db.DB;
import com.estore.model.Product;

/**
 * Servlet implementation class AddProductServlet
 */
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter  out = response.getWriter();
		response.setContentType("text/html");

		String code = request.getParameter("txtCode");
		String name = request.getParameter("txtName");
		String price = request.getParameter("txtPrice");
		
		DB db = new DB();
		db.createConnection();
		try {
		Product product = new Product();
		product.setCode(Integer.parseInt(code));
		product.setName(name);
		product.setPrice(Integer.parseInt(price));
		
		Boolean isAdded = db.addProduct(product);
		db.closeConnection();

		if(isAdded)
		{
			out.print("<center style={color:blue}>"+name+" added successfully</center><br><br>") ;
			out.print("<center><button><a href=ProductList>See All Products</a></center>");
			
			//RequestDispatcher requestDispatcher = request.getRequestDispatcher("ProductList");
			request.setAttribute("product_name", name);
	        response.sendRedirect(request.getContextPath() + "/ProductList?product_name="+name);
		}
		else
			out.print("<center>Something went wrong! Product not added</center>") ;
		}catch(Exception e)
		{
			out.print("<center>Something went wrong! Product not added</center>") ;
	
		}
		

	}

}
