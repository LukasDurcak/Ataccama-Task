package com.ataccama.db.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

/**
 * Generic class for DAO
 *
 * @param <T> - class to work with
 * @param <PK> - primary key type
 */
@Transactional
public class AbstractDao<T extends Serializable, PK extends Serializable> {

	@PersistenceContext
	protected EntityManager em;
	
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao() {
		ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
	}
}

