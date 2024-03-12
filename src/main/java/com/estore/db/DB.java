package com.estore.db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.estore.model.Customer;
import com.estore.model.Product;
import com.mysql.cj.jdbc.CallableStatement;

public class DB implements DAO {
	
	// Connection
	
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	
	public DB() {
		
		//mysql -> driver, oracle db -> driver will be different
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void createConnection() {
		
		//username, pwd, url
		String username = "root";
		String password = "Kalol@123";
		String url = "jdbc:mysql://localhost:3306/estore";
		
		try {
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("Connected successfully");
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
		try {
			connection.close();
			System.out.println("Connection closed. Close Status : "+connection.isClosed());
		} catch (Exception e) {
		  System.out.println(e);
		}
		
		
	}

	@Override
	public void createCustomer(Customer customer) {
		
		try {
			
			/*String sql = "insert into customer values (null,'"+customer.getName()+"','"+customer.getPhone()+"','"+customer.getEmail()+"','"+
					customer.getBirthDate()+"',"+customer.getAge()+",'"+customer.getInDateTime()+"','"+customer.getOutDateTime()+"',"+
					customer.getAmount()+")";*/
			
			String sql = "insert into customer values(null,?,?,?,?,?,?,?,?)" ;//placeholders
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,customer.getName());
			preparedStatement.setString(2,customer.getPhone());
			preparedStatement.setString(3,customer.getEmail());
			preparedStatement.setString(4,customer.getBirthDate());
			preparedStatement.setInt(5,customer.getAge());
			preparedStatement.setString(6,customer.getIndateTime());
			preparedStatement.setString(7,customer.getOutDatetime());
			preparedStatement.setFloat(8,customer.getAmount());
			
			
			//System.out.println(sql);
			
			//coonection -> create statement, executeUpdate()
			
			//statement = connection.createStatement();
			//int result = statement.executeUpdate(sql); //result > 0,created, not created
			
			int result = preparedStatement.executeUpdate(); 
			//if else
			//ternary op
			String message = result>0 ? "Customer created successfully" : "Customer not created. Try again.";
			System.out.println(message);
			
			
		}catch (Exception e) {
			  System.out.println(e);
			}
		
		
		
		
	}

	@Override
	public void updateCustomer() {
		// bufferedReader->
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter customer id : ");
			String value = br.readLine();
			int customer_id = Integer.parseInt(value);
			System.out.println("Enter updated phone number");
			String updated_pn = br.readLine();
			
			String sql = "update customer set phone='"+updated_pn+"' where cid="+customer_id;
			System.out.println(sql);
			
			statement = connection.createStatement();
			
            int result = statement.executeUpdate(sql); //result > 0,created, not created
			
			String message = result>0 ? "Customer updated successfully" : "Customer not updated. Try again.";
			System.out.println(message);
			
			
			
		}catch (Exception e) {
			  System.out.println(e);
			}
		
	}
	
	@Override
	public void deleteCustomer(int cid) {
		// TODO Auto-generated method stub
		
		String sql = "delete from customer where cid=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, cid);
			int result = preparedStatement.executeUpdate();
			
			String message = result>0 ? "Customer deleted successfully" : "Customer not deleted. Try again.";
			System.out.println(message);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<Customer> getAllCustomer() {
		
		ArrayList<Customer> customers = new ArrayList<Customer>();
		// TODO Auto-generated method stub
		String sql = "select * from customer";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet set = preparedStatement.executeQuery();
			while(set.next()){
				
				Customer customer = new Customer();

				customer.setCid(set.getInt(1));
				customer.setName(set.getString(2));
				customer.setPhone(set.getString(3));
				customer.setEmail(set.getString(4));

				customer.setBirthDate(set.getString(5));
				customer.setAge(set.getInt(6));
				customer.setIndateTime(set.getString(7));
				customer.setOutDatetime(set.getString(8));

				customer.setAmount(set.getFloat(9));
				customers.add(customer);
				
				
			}
			
		}catch(Exception e) {
			
		}	
	
		return customers;
	}

	@Override
	public void callProcedure(String name,String password) {
		// TODO Auto-generated method stub
		String sql = "{call addUser(?,?)}";
		try {
			CallableStatement callableStatement = (CallableStatement) connection.prepareCall(sql);
		     callableStatement.setString(1, name);
		     callableStatement.setString(2, password);
		     callableStatement.execute();
		     System.out.println("call done");

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void excuteBatch() {
		// TODO Auto-generated method stub
		
		// statement ->addBatch->create a batch,excuteBatch()
		String sql1 = "insert into employees values(null,'rani','rani@gmailcom',4000)";
		String sql2 = "insert into employees values(null,'karuna','karuna@gmailcom',4000)";

		String sql3 = "delete from customer where cid=10";
		String sql4 = "update customer set phone='9000000' where cid=7";
		
        try {
			statement.addBatch(sql1);
		
        statement.addBatch(sql2); 

        statement.addBatch(sql2); 

        statement.addBatch(sql2); 
        int[] res = statement.executeBatch();
        System.out.println("batch executed successfully"+res);

        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	@Override
	public void executeTrasaction() {
		// TODO Auto-generated method stub
		
		try {
			
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			String sql = "insert into orders values(null,3,'2024-03-02',9000)";
			String sql1 = "update orders set amount=4000 where orderId=1";
			statement.addBatch(sql);
			statement.addBatch(sql1);
			int[] res=statement.executeBatch();
			
			for(int i=0;i<res.length;i++)
			{
				System.out.println(i+" query :"+res[i]);

			}
			connection.commit();
			System.out.println("Traction commited");
			


			
			
			
		}catch(Exception e)
		{
			System.out.println("not rollback the trasaction"+e);

			
			/*
			 * try { System.out.println("Rolling back the trasaction");
			 * connection.rollback(); }catch (SQLException ex) { // TODO: handle exception
			 * System.out.println(); }
			 */
			 
		}
		
		
		
	}

	@Override
	public void showMetaData() {
		// TODO Auto-generated method stub
try {
			
			DatabaseMetaData dbMetadata = connection.getMetaData();
			System.out.println("Product Name : "+dbMetadata.getDatabaseProductName());
			System.out.println("Product version : "+dbMetadata.getDatabaseProductVersion());
			System.out.println("Driver Name : "+dbMetadata.getDriverName());
			System.out.println("Driver Version : "+dbMetadata.getDriverVersion());
			System.out.println("User Name : "+dbMetadata.getUserName());
			System.out.println("Database name : "+connection.getMetaData().getURL());

			
			System.out.println("Table Present :");
			String tables[] = {"TABLE"};
			ResultSet set = dbMetadata.getTables(null,null,null,tables);
			while(set.next()) {
				System.out.println(set.getString("TABLE_NAME"));
			}
					
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}

	@Override
	public Boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		Boolean flag = false;
		
		try {
			
			/*String sql = "insert into customer values (null,'"+customer.getName()+"','"+customer.getPhone()+"','"+customer.getEmail()+"','"+
					customer.getBirthDate()+"',"+customer.getAge()+",'"+customer.getInDateTime()+"','"+customer.getOutDateTime()+"',"+
					customer.getAmount()+")";*/
			
			String sql = "insert into product values(null,?,?,?)" ;//placeholders
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,product.getCode());
			preparedStatement.setString(2,product.getName());
			preparedStatement.setInt(3,product.getPrice());
			
			
			
			//System.out.println(sql);
			
			//coonection -> create statement, executeUpdate()
			
			//statement = connection.createStatement();
			//int result = statement.executeUpdate(sql); //result > 0,created, not created
			
			int result = preparedStatement.executeUpdate(); 
			//if else
			//ternary op
            if(result>0)
            	flag = true;
            else flag= false;
			
		}catch (Exception e) {
			  System.out.println(e);
			  flag = false;
			}
		return flag;
		
	}

	@Override
	public ArrayList<Product> getAllProducts() {
		// TODO Auto-generated method stub

		ArrayList<Product> products = new ArrayList<Product>();
		// TODO Auto-generated method stub
		String sql = "select * from product";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet set = preparedStatement.executeQuery();
			while(set.next()){
				
				Product product = new Product();

				product.setId(set.getInt(1));
				product.setCode(set.getInt(2));
				product.setName(set.getString(3));
				product.setPrice(set.getInt(4));;

				products.add(product);
				
				
			}
			
		}catch(Exception e) {
			
		}	
	
		return products;
		
	}
	

}
