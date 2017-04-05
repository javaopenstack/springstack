package org.java.openstack.flyway;

import org.kohsuke.args4j.Option;

public class ArgParams {
	
	@Option(name="-url",required=true, usage="set url")
	private String url;
	
	@Option(name="-driver",required=true, usage="set driver")
	private String driver;
	
	
	@Option(name="-username",required=true, usage="set username")
	private String user;
	
	@Option(name="-password",required=false, usage="set password")
	private String password;
	 
	
	@Override
	public String toString() {
		return "ArgParams [url=" + url + ", user=" + user + ", password=" + password + "]";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
 
}
