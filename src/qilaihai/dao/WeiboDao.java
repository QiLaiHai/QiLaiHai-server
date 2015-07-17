package qilaihai.dao;

import java.util.Date;
import java.util.List;

import qilaihai.domain.User;
import qilaihai.domain.Weibo;

public interface WeiboDao extends BaseDao<Weibo> {
	/**
	 * 查找某一用户（或所有用户）在某一间隔内的所有Weibo
	 * @param poster 
	 * 		发出这些微博的用户，必须包含id属性。如果为null，则查找所有用户发出的微博
	 * @param begin
	 * 		发出这些微博的开始时间，可以为null
	 * @param end
	 * 		发出这些微博的结束时间，可以为null
	 * @param isDescending
	 * 		是否按照按照时间倒序排序，true为时间倒叙排列，false为时间正序排列
	 * @return
	 * 		user在[begin, end]之间发出的所有微博，按照isDescending中指定顺序排序。
	 * 		不会为null
	 */
	List<Weibo> getByTimeInterval(User poster, Date begin, Date end, boolean isDescending);
	Weibo get(Integer id);
}
