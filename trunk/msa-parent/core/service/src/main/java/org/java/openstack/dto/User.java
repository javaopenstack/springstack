package org.java.openstack.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table( name = "USERS" ) 
public class User implements Serializable {
	 
	private static final long serialVersionUID = 1L; 
	
	@Id 
	@Column(name="USERNAME",nullable=false,length=128)
	private String username;
	@Column(name="PASSWORD",nullable=false,length=4000)
	private String password;
	@Column(name="COMPANY",nullable=false,length=10)
	private String company;
	@Column(name="TOKEN",nullable=true,length=50)
	private String token;	
	@Column(name="LAST_UPDATE",nullable=true)
	private Date lastUpdate;
	@Column(name="ROLE",nullable=false)
	private String role;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}