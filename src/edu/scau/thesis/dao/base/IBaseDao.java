package edu.scau.thesis.dao.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface IBaseDao<T> {

	public Serializable save(T entity) throws DataAccessException;
	public void saveOrUpdate(T entity) throws DataAccessException;
	public void saveOrUpdate(Collection<T> entities) throws DataAccessException;
	public void delete(T entity) throws DataAccessException;
	public void delete(Serializable id) throws DataAccessException;
	public void delete(Collection<T> entities) throws DataAccessException;
	public List<T> find(String hql) throws DataAccessException;
    public List<T> find(String hql,Object[] objs) throws DataAccessException;
    public List<T> find(String hql,Map<Object,Object> params) throws DataAccessException;
}
