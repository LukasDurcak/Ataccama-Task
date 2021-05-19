package com.ataccama.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CONNECTION_DETAIL")
public class DatabaseConnection extends AbstractEntity {

	private static final long serialVersionUID = 8377376123340332030L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "seq_connection_detail_id")
	@SequenceGenerator(sequenceName = "SEQ_CONNECTION_DETAIL_ID", name = "seq_connection_detail_id", allocationSize = 1, initialValue = 1)
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "HOST_NAME", nullable = false)
	private String hostName;
	
	@Column(name = "PORT", nullable = false)
	private Long port;
	
	@Column(name = "DATABASE_NAME", nullable = false)
	private String databaseName;
	
	@Column(name = "USER_NAME", nullable = false)
	private String userName;
	
	// Hashed on client side
	@Column(name = "PASSWORD", nullable = false)
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
}
