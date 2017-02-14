package edu.scau.thesis.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;

import edu.scau.thesis.model.base.BasePOJO;
import edu.scau.thesis.utils.Utils;

public class BaseDao<T extends BasePOJO> implements IBaseDao<T>{
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	private Class<T> clazz;
    private String className;
    public BaseDao(){
        this.clazz =null;
        Class c =getClass();
        Type t =c.getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p =((ParameterizedType)t).getActualTypeArguments();
            this.clazz =(Class<T>) p[0];
        }
        this.className =clazz.getSimpleName();

    }
	public Session getsesSession(){
        Session session=sessionFactory.getCurrentSession();
        return session;
    }
	@Override
	public Serializable save(T entity) throws DataAccessException {
		if(StringUtils.isEmpty(entity.getId())){
			entity.setId(Utils.newUUID());
		}
		return getsesSession().save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) throws DataAccessException {
		if(StringUtils.isEmpty(entity.getId())){
			entity.setId(Utils.newUUID());
			getsesSession().save(entity);
		}
		else{
			getsesSession().saveOrUpdate(entity);
		}
		
	}

	@Override
	public void saveOrUpdate(Collection<T> entities) throws DataAccessException {
		for (T t : entities) {
			if (null==t.getId()){
                t.setId(Utils.newUUID());
                save(t);
            }else {
                getsesSession().saveOrUpdate(t);
            }
		}
	}

	@Override
	public void delete(T entity) throws DataAccessException {
		getsesSession().delete(entity);
	}

	@Override
	public void delete(Serializable id) throws DataAccessException {
		
		
	}

	@Override
	public void delete(Collection<T> entities) throws DataAccessException {
		for (T t : entities) {
			delete(t);
		}
		
	}

	@Override
	public List<T> find(String hql) throws DataAccessException {
		return getsesSession().createQuery(hql).list();
	}

	@Override
	public List<T> find(String hql, Object[] objs) throws DataAccessException {
		Query query =getsesSession().createQuery(hql);
        if(objs!=null){
            for (int i = 0; i < objs.length; i++) {
                query.setParameter(i, objs[i]);
            }
        }
        return query.list();
	}

	@Override
	public List<T> find(String hql, Map<Object,Object> params) throws DataAccessException {
		Query query=getsesSession().createQuery(hql);
        if (null!=params){
            for(Map.Entry<Object,Object> entry:params.entrySet()){
            	Object k = entry.getKey();
            	Object v = entry.getValue();
                if (v instanceof List){
                    query.setParameterList(k.toString(),(Collection)v);
                }else if (v instanceof Object[]){
                    query.setParameterList(k.toString(),(Object[])v);
                }else {
                    query.setParameter(k.toString(),v);
                }
            };
        }
        return query.list();
	}
	@Override
	public T load(Serializable id) throws DataAccessException {
		return (T)getsesSession().load(clazz, id);
	}


}
