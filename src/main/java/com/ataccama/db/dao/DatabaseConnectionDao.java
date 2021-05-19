package com.ataccama.db.dao;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.ataccama.db.entity.DatabaseConnection;

@Component
public class DatabaseConnectionDao extends GenericDao<DatabaseConnection, Long> {
	
	/**
	 * Get database connection by connection name
	 * 
	 * @param name
	 * @return
	 */
	@Transactional
	public DatabaseConnection getDbConnectionByName(String name) {
		TypedQuery<DatabaseConnection> query = em.createQuery("from " + DatabaseConnection.class.getName() + " d where d.name=:name", DatabaseConnection.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}
}
