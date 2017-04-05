package org.java.openstack.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "CONFIGURATIONS" )
public class Configuration implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column( name = "CONFIG_KEY", nullable = false, length = 512 )
	private String key;
	
	@Column( name = "CONFIG_VALUE", nullable = false, length = 4096 )
	private String value;
	
	@Column( name = "DESCRIPTION", nullable = true, length = 4096 )
	private String description;
	
	public String getKey()
	{
		return key;
	}
	
	public void setKey( String key )
	{
		this.key = key;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue( String value )
	{
		this.value = value;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription( String description )
	{
		this.description = description;
	}
	
}
