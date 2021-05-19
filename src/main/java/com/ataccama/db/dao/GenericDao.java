package com.ataccama.db.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.springframework.transaction.annotation.Transactional;

import com.ataccama.db.entity.AbstractEntity;

/**
 * Generic dao class for basic operations with entities
 * @author Lukas
 *
 * @param <T>
 * @param <PK>
 */
@Transactional
public abstract class GenericDao<T extends AbstractEntity, PK extends Serializable> extends AbstractDao<T, PK> {
	
	/**
	 * Create record in db
	 * 
	 * @param t
	 * @return
	 */
	@Transactional
	public T create(T t) {
		this.em.persist(t);
		return t;
	}

	/**
	 * Update record in db
	 * 
	 * @param t
	 * @return
	 */
	@Transactional
	public T update(T t) {
		return this.em.merge(t);
	}
	
	/**
	 * Delete record in db
	 * 
	 * @param t
	 */
	@Transactional
	public void delete(T t) {
		this.em.remove(t);
	}
	
	/**
	 * Get record by key
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public T get(PK id) {
		return this.em.find(entityClass, id);
	}
	
	/**
	 * Get all records
	 * 
	 * @return
	 */
	@Transactional
	public List<T> getAll() {
		CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(entityClass);
		criteriaQuery.select(criteriaQuery.from(entityClass));
		return em.createQuery(criteriaQuery).getResultList();
	}
}
