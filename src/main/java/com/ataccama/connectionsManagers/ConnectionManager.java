package com.ataccama.connectionsManagers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ataccama.dto.DatabaseConnectionDto;
import com.ataccama.services.DatabaseConnectionService;

/**
 * Class for connect to database ant get data (tables, schemas....)
 * Support just postgresql for now
 * 
 * TODO: Logging for every method, parametrized connecitons
 * 
 * @author Lukas
 *
 */
@Component
public class ConnectionManager {
	
	// Supported table type for getting table names 
	private static String[] supportedTableTypes = {"TABLE"};
	
	@Autowired
	private DatabaseConnectionService databaseConnectionService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * Get tables for stored db connection
	 * 
	 * @param connectionName
	 * @return
	 */
	public List<String> getTablesForDb(String connectionName) {
		List<String> tablesNames = new ArrayList<String>();
		
		Connection connection = openConnectionForDatabase(connectionName);
		
		if (connection != null) {
			try {
				logger.info("Getting tables for db " + connectionName);
				
				DatabaseMetaData md = connection.getMetaData();	
				ResultSet rs = md.getTables(null, null, "%", supportedTableTypes);
			
				while (rs.next()) {
					tablesNames.add(rs.getString(3));
				}
			
				connection.close();
			} catch (SQLException e) {
				logger.error("Error closing connection", e);
			}
		}
		
		return tablesNames;
	}
		
	/**
	 * Get all schemas names from stored db connection
	 * 
	 * @param connectionName
	 * @return
	 */
	public List<String> getSchemasForDb(String connectionName) {
		List<String> schemas = new ArrayList<String>();
		
		Connection connection = openConnectionForDatabase(connectionName);
		
		if (connection != null) {
			try {
				logger.info("Getting schemas for db " + connectionName);
				
				DatabaseMetaData md = connection.getMetaData();	
				ResultSet rs = md.getSchemas();
			
				while (rs.next()) {
					schemas.add(rs.getString("TABLE_SCHEM"));
				}
			
				connection.close();
			} catch (SQLException e) {
				logger.error("Error closing connection", e);
			}
		}
		
		return schemas;
	}
	
	/**
	 * Get columns for stored Db connection
	 * 
	 * @param connectionName
	 * @return
	 */
	public List<String> getColumnsForDb(String connectionName) {
		List<String> columns = new ArrayList<String>();
		
		Connection connection = openConnectionForDatabase(connectionName);
		
		if (connection != null) {
			try {
				logger.info("Getting columns for db " + connectionName);
				
				DatabaseMetaData md = connection.getMetaData();	
				ResultSet rs = md.getTables(null, null, "%", supportedTableTypes);
				
				// Get user tables
				List<String> tables = new ArrayList<String>();
				while (rs.next()) {
					tables.add(rs.getString(3));
				}
				
				//TODO: create own methods
				for(String table: tables) {
					Statement statement = connection.createStatement();
				    ResultSet tableResultSet = statement.executeQuery("select * from " + table);
				    ResultSetMetaData rsMetaData = tableResultSet.getMetaData();
				    
				    int columnsCount = rsMetaData.getColumnCount();
			        
				    for(int i = 1; i <= columnsCount; i++) {
				    	columns.add(rsMetaData.getColumnName(i));
				    }
				}
				
				connection.close();
			} catch (SQLException e) {
				logger.error("Error closing connection", e);
			}
		}
		
		return columns;
	}
	
	/**
	 * Check for database connection info in our db and open connection for it
	 * 
	 * 
	 * @param connectionName
	 * @return
	 */
	private Connection openConnectionForDatabase(String connectionName) {
		DatabaseConnectionDto connectionInfo = getExistingConnectionInfo(connectionName);
		String connectionUrl = resolveConnectionString(connectionInfo);
		
		Connection con = null;
		
		 try {
			 con = DriverManager.getConnection(connectionUrl, connectionInfo.getUserName(), connectionInfo.getPassword());
			 
			 logger.info("Connected to database " + connectionUrl);
	     } catch (SQLException e) {
	         logger.error("Cannot find driver", e);
	     }
		 
		 return con;
	};
	
	private DatabaseConnectionDto getExistingConnectionInfo(String connectionName) {
		DatabaseConnectionDto existingConnection = databaseConnectionService.getDbConnectionByName(connectionName);
		
		if (existingConnection == null) {
			throw new IllegalStateException("Connection does not exist");
		}
		
		return existingConnection;
	}
	
	/**
	 * Create connection url based on connection stored in db
	 * 
	 * TODO: Make parametrized, check null....
	 * 
	 * @param dbConnectionDto
	 * @return
	 */
	private String resolveConnectionString(DatabaseConnectionDto dbConnectionDto) {
		if (dbConnectionDto == null) {
			return null;
		}

		String connectionUrl = "jdbc:postgresql://" + dbConnectionDto.getHostName() + ":";
		connectionUrl += + dbConnectionDto.getPort() + "/" + dbConnectionDto.getDatabaseName();
		
		return connectionUrl;
	}
}
