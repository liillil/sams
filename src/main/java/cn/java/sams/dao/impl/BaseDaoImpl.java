package cn.java.sams.dao.impl;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cn.java.sams.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {

	@PersistenceContext
	protected EntityManager em;
	
	public Class<T> getTClass(){
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>)((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return clazz;
	}
	
	@Override
	public T find(int id) {
		return em.find(getTClass(), id);
	}

	@Override
	public void add(T t) {
		em.persist(t);
	}

	@Override
	public void delete(int id) {
		em.remove(find(id));
	}

	@Override
	public void merge(T t) {
		em.merge(t);
	}

}
