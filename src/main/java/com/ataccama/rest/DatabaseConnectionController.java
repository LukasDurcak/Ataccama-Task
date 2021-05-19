package com.ataccama.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ataccama.dto.DatabaseConnectionDto;
import com.ataccama.services.DatabaseConnectionService;

@RestController
public class DatabaseConnectionController {
	
	//TODO add logging for http requests (HttpServletRequestWrapper)
	
	@Autowired
	private DatabaseConnectionService dataConnectionService;
	
	@GetMapping("/connections/getallDbConnections")
	public List<DatabaseConnectionDto> getallDbConnections() {
		return dataConnectionService.getAllDbConnections();
	}
	
	@PostMapping("/connections/saveNewDbConnection")
	public Long saveNewDbConnection(@RequestBody DatabaseConnectionDto dbConnectionToAdd) {
		return dataConnectionService.createNewDbConnection(dbConnectionToAdd);
	}
	
	@PutMapping("/connections/updateDbConnection") 
	public Long updateDbConnection(@RequestBody DatabaseConnectionDto dbConnectionToUpdate) {
		return dataConnectionService.updateDbConnection(dbConnectionToUpdate);
	}
	
	//TODO delete just by ID
	@DeleteMapping("connections/deleteDbConneciton")
	public void deleteDbConnection(@RequestBody DatabaseConnectionDto dbConnectionToDelete) {
		dataConnectionService.deleteDatabaseConnection(dbConnectionToDelete);
	}
	
	
}
