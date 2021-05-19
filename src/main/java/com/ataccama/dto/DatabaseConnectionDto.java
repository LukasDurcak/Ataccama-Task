package com.ataccama.dto;

import java.util.Objects;

import com.ataccama.db.entity.AbstractEntity;

public class DatabaseConnectionDto extends AbstractEntity {

	private static final long serialVersionUID = 2915948890979771462L;
	
	private Long id;
	private String name;
	private String hostName;
	private Long port;
	private String databaseName;
	private String userName;
	private String password;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getHostName() {
		return hostName;
	}
	
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public Long getPort() {
		return port;
	}
	
	public void setPort(Long port) {
		this.port = port;
	}
	
	public String getDatabaseName() {
		return databaseName;
	}
	
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(databaseName, hostName, id, name, password, port, userName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatabaseConnectionDto other = (DatabaseConnectionDto) obj;
		return Objects.equals(databaseName, other.databaseName) && Objects.equals(hostName, other.hostName)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(port, other.port)
				&& Objects.equals(userName, other.userName);
	}
	
	@Override
	public String toString() {
		return "DatabaseConnectionDto [id=" + id + ", name=" + name + ", hostName=" + hostName + ", port=" + port
				+ ", databaseName=" + databaseName + ", userName=" + userName + ", password=" + password + "]";
	}
}
