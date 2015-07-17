package qilaihai.dao.hibernate4;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import qilaihai.dao.BaseDao;

public class BaseDaoHibernate4<T> implements BaseDao<T> {

	// DAO组件进行持久化操作底层依赖的SessionFactory组件
	private SessionFactory mSessionFactory;
	
	// 依赖注入SessionFactory所需的setter方法
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.mSessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory()
	{
		return this.mSessionFactory;
	}
	// 根据ID加载实体
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public T get(Class<T> entityClazz , Serializable id)
	{
		return (T)getSessionFactory().getCurrentSession()
			.get(entityClazz , id);
	}
	
	// 保存实体
	@Override
	@Transactional
	public Serializable save(T entity)
	{
		return getSessionFactory().getCurrentSession()
			.save(entity);
	}
	
	// 更新实体
	@Override
	@Transactional
	public void update(T entity)
	{
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}
	
	// 删除实体
	@Override
	@Transactional
	public void delete(T entity)
	{
		getSessionFactory().getCurrentSession().delete(entity);
	}
	
	// 根据ID删除实体
	@Override
	@Transactional
	public void delete(Class<T> entityClazz , Serializable id)
	{
		getSessionFactory().getCurrentSession()
			.createQuery("delete " + entityClazz.getSimpleName()
				+ " en where en.id = ?0")
			.setParameter("0" , id)
			.executeUpdate();
	}
	
	// 获取所有实体
	@Override
	@Transactional
	public List<T> findAll(Class<T> entityClazz)
	{
		return find("select en from "
			+ entityClazz.getSimpleName() + " en");
	}
	
	// 获取实体总数
	@Override
	@Transactional
	public long findCount(Class<T> entityClazz)
	{
		List<?> l = find("select count(*) from "
			+ entityClazz.getSimpleName());
		// 返回查询得到的实体总数
		if (l != null && l.size() == 1 )
		{
			return (Long)l.get(0);
		}
		return 0;
	}

	// 根据HQL语句查询实体
	@Transactional
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql)
	{
		return (List<T>)getSessionFactory().getCurrentSession()
			.createQuery(hql)
			.list();
	}
	
	// 根据带占位符参数HQL语句查询实体
	@Transactional
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql , Object... params)
	{
		// 创建查询
		Query query = getSessionFactory().getCurrentSession()
			.createQuery(hql);
		// 为包含占位符的HQL语句设置参数
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter( Integer.toString(i), params[i]);
		}
		return (List<T>)query.list();
	}
	
}
