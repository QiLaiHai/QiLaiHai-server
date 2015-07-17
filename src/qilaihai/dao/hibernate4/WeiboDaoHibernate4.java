package qilaihai.dao.hibernate4;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import qilaihai.dao.WeiboDao;
import qilaihai.domain.User;
import qilaihai.domain.Weibo;

public class WeiboDaoHibernate4 extends BaseDaoHibernate4<Weibo> implements
        WeiboDao {

	@SuppressWarnings("unchecked")
    @Override
	public List<Weibo> getByTimeInterval(User poster, Date begin, Date end,
	        boolean isDescending) {
		StringBuffer sb = new StringBuffer("select w from Weibo as w where ");
		if (poster != null) {
			sb.append("w.poster.id = :posterId and ");
		}
		if (begin != null) {
			sb.append("w.postTime >= :beginTime and ");
		}
		// 避免后置悬空的and，如果end为null则填充当前时间
		sb.append("w.postTime <= :endTime ");
		
		sb.append("order by w.postTime ");
		sb.append(isDescending ? "desc" : "asc");
		
		String hql = sb.toString();
		
		Query query = getSessionFactory().getCurrentSession()
				.createQuery(hql);
		
		if (poster != null) {
			query.setInteger("posterId", poster.getId());
		}
		if (begin != null) {
			query.setDate("benginTime", begin);
		}
		if (end != null) {
			query.setTimestamp("endTime", end);
		} else {
			query.setTimestamp("endTime", new Date());
		}
		
		return (List<Weibo>)query.list();
	}

    @Override
    public Weibo get(Integer id) {
	    return get(Weibo.class, id);
    }

}
