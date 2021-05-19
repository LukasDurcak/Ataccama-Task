package com.ataccama.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ataccama.connectionsManagers.ConnectionManager;

@RestController
public class DatabaseInfoController {
	
	@Autowired
	private ConnectionManager connectionManager;
	
	@GetMapping("/info/getTableNames")
	public List<String> getTableNames(@RequestParam String databaseName) {
		return connectionManager.getTablesForDb(databaseName);
	}
	
	@GetMapping("/info/getTableSchemas")
	public List<String> getTableSchemas(@RequestParam String databaseName) {
		return connectionManager.getSchemasForDb(databaseName);
	}
	
	@GetMapping("/info/getColumnNames")
	public List<String> getColumnNames(@RequestParam String databaseName) {
		return connectionManager.getColumnsForDb(databaseName);
	}
	
	//TODO - another info (prim. keys et.)...
}
