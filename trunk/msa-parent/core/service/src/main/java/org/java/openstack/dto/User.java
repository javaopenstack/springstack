package org.java.openstack.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "ACCOUNTS" )
public class User {

	private String name;

	private int age;
	
	@Id
	private Integer id;
	
	private Date date;
	
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User() {

	}
	
	
 
	public User(String name, int age, Integer id, Date date) {
		super();
		this.name = name;
		this.age = age;
		this.id = id;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
