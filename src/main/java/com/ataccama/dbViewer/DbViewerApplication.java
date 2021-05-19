package com.ataccama.dbViewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
	"com.ataccama.rest", 
	"com.ataccama.db.dao", 
	"com.ataccama.services",
	"com.ataccama.connectionsManagers"})
@EntityScan(basePackages = {"com.ataccama.db.entity"})
public class DbViewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbViewerApplication.class, args);
	}

}
