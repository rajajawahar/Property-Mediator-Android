package com.silicontechnnologies.propertymediator.entities;

public class Property {
	String Name = null;
	String Type = null;
	String Phone = null;
	String Email = null;
	String Address = null;
	String Details = null;

	public String getname() {
		return Name;
	}

	public void setname(String name) {
		this.Name = name;
	}

	public String gettype() {
		return Type;
	}

	public void settype(String type) {
		this.Type = type;
	}

	public String getphone() {
		return Phone;
	}

	public void setphone(String phone) {
		this.Phone = phone;
	}

	public String getemail() {
		return Email;
	}

	public void setemail(String email) {
		this.Email = email;
	}

	public String getaddress() {
		return Address;
	}

	public void setaddress(String address) {
		this.Address = address;
	}

	public String getdetail() {
		return Details;
	}

	public void setdetail(String detail) {
		this.Details = detail;
	}

}