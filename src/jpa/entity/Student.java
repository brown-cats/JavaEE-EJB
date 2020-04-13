package jpa.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity

public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	int id;
	
	String name;
	
	String gender;
	
	String major;
	
	@Column(name="address_id")
	String addressId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getAddress_id() {
		return addressId;
	}
	public void setAddress_id(String address_id) {
		this.addressId = address_id;
	}

}
