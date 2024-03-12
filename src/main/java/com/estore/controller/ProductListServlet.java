package com.estore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.estore.db.DB;
import com.estore.model.Product;

/**
 * Servlet implementation class ProductListServlet
 */
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// TODO Auto-generated method stub
		
		String productName= request.getParameter("product_name");
		DB db = new DB();
		db.createConnection();
		
		ArrayList<Product> products = db.getAllProducts();
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Product List</title></head><body>");
		out.print("<center>");
		if(productName!=null && !productName.equals(""))
		   out.print(productName+" added successfully") ;

        out.println("<h2>Product List</h2>");
        
        if(!products.isEmpty()) {
        out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Price</th></tr>");
		for(Product product:products)
		{
			 out.println("<tr><td>" + product.getCode() + "</td><td>" + product.getName() + "</td><td>"
	                    + product.getPrice() + "</td></tr>");
		}
		out.print("</center>");

		 out.println("</table>");
        }else
        {	 out.print("<h3>Oops! Product list is empty</h3>");
             out.print("<center>");
        }

        out.println("</body></html>");
	}

}
