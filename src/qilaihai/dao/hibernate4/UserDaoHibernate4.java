package qilaihai.dao.hibernate4;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import qilaihai.dao.UserDao;
import qilaihai.domain.User;

public class UserDaoHibernate4 extends BaseDaoHibernate4<User> implements
		UserDao {

	@Transactional
	@Override
	public User findByPhoneNumber(String phoneNumber) {
		User result = null;
		List<User> users = find(
				"select u from User as u where u.phoneNumber = ?0", phoneNumber);
		if (users != null && users.size() > 0) {
			result = users.get(0);
		}
		return result;
	}

	@Override
    public User get(Integer id) {
	    return get(User.class, id);
    }

}
