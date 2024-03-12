package com.estore.model;

public class Customer {

	int cid;
	public int getCid() {
		return cid;
	}





	public void setCid(int cid) {
		this.cid = cid;
	}
	String name;
	String phone;
	String email;
	String birthDate; 
	int age; 
	String indateTime;
	String outDatetime;
	float amount;
	
	
	public Customer(String name, String phone, String email, String birthDate, int age, String indateTime,
			String outDatetime, float amount) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.birthDate = birthDate;
		this.age = age;
		this.indateTime = indateTime;
		this.outDatetime = outDatetime;
		this.amount = amount;
	}
	
	
	
	
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getIndateTime() {
		return indateTime;
	}
	public void setIndateTime(String indateTime) {
		this.indateTime = indateTime;
	}
	public String getOutDatetime() {
		return outDatetime;
	}
	public void setOutDatetime(String outDatetime) {
		this.outDatetime = outDatetime;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	

}
