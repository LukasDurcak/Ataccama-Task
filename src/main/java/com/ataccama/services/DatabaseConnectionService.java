package com.ataccama.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ataccama.db.dao.DatabaseConnectionDao;
import com.ataccama.db.entity.DatabaseConnection;
import com.ataccama.dto.DatabaseConnectionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for working with Database connections
 * 
 * @author Lukas
 *
 */
@Component
public class DatabaseConnectionService {
	
	//TO DO: try/catch - logging successful or error
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DatabaseConnectionDao databaseConnectionDao;
	
	@Transactional
	public DatabaseConnectionDto getDbConnectionById(Long id) {
		logger.info("Getting databaseConnection by id " + id);
		
		DatabaseConnection databaseConnectionDomain = databaseConnectionDao.get(id);
		return transform(databaseConnectionDomain);
	}
	
	@Transactional
	public Long createNewDbConnection(DatabaseConnectionDto dbConnection) {
		logger.info("Creating new db conncetion with name " + dbConnection.getName());
		
		DatabaseConnection databaseConnectionDomain = transform(dbConnection);
		databaseConnectionDomain = databaseConnectionDao.create(databaseConnectionDomain);
		
		return databaseConnectionDomain.getId();
	}
	
	@Transactional
	public Long updateDbConnection(DatabaseConnectionDto dbConnection) {
		logger.info("Updating database connection with id " + dbConnection.getId());
		
		DatabaseConnection databaseConnectionDomain = transform(dbConnection);
		databaseConnectionDomain = databaseConnectionDao.update(databaseConnectionDomain);
		
		return databaseConnectionDomain.getId();
	}
	
	@Transactional
	public void deleteDatabaseConnection(DatabaseConnectionDto dbConnection) {
		logger.info("Deleting connection with id " + dbConnection.getId());
		
		DatabaseConnection connection = databaseConnectionDao.get(dbConnection.getId());
		databaseConnectionDao.delete(connection);
	}
	
	@Transactional
	public List<DatabaseConnectionDto> getAllDbConnections() {
		logger.info("Getting all connections from db");
		
		List<DatabaseConnection> domains = databaseConnectionDao.getAll();
		List<DatabaseConnectionDto> dtos = new ArrayList<DatabaseConnectionDto>();
		
		if(!CollectionUtils.isEmpty(domains)) {
			dtos.addAll(domains.stream().map(this::transform).collect(Collectors.toList()));
		}
		
		return dtos;
	}
	
	@Transactional
	public DatabaseConnectionDto getDbConnectionByName(String name) {
		logger.info("Getting connections from db by name " + name);
		
		DatabaseConnection databaseConnectionDomain = databaseConnectionDao.getDbConnectionByName(name);
		return transform(databaseConnectionDomain);
	}
	
	private DatabaseConnection transform(DatabaseConnectionDto from) {
		if (from == null) {
			return null;
		}
		
		DatabaseConnection to = new DatabaseConnection();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setHostName(from.getHostName());
		to.setPort(from.getPort());
		to.setDatabaseName(from.getDatabaseName());
		to.setUserName(from.getUserName());
		to.setPassword(from.getPassword());
		
		return to;
	}
	
	private DatabaseConnectionDto transform(DatabaseConnection from) {
		if (from == null) {
			return null;
		}
		
		DatabaseConnectionDto to = new DatabaseConnectionDto();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setHostName(from.getHostName());
		to.setPort(from.getPort());
		to.setDatabaseName(from.getDatabaseName());
		to.setUserName(from.getUserName());
		to.setPassword(from.getPassword());
		
		return to;
	}
}
