package com.estore.db;
import java.util.ArrayList;
import com.estore.model.Customer;
import com.estore.model.Product;

public interface DAO {
	
	void createConnection();
	void closeConnection();
	void createCustomer(com.estore.model.Customer customer);
	void updateCustomer();
	void deleteCustomer(int cid);
	public ArrayList<Customer> getAllCustomer();
    public void callProcedure(String name,String password)  ;
    void excuteBatch();
    void executeTrasaction();
    void showMetaData();
    
    
    Boolean addProduct(Product product);
    ArrayList<Product> getAllProducts();
 
}
