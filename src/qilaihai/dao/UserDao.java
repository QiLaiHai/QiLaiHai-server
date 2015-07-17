package qilaihai.dao;

import qilaihai.domain.User;

public interface UserDao extends BaseDao<User> {
	User findByPhoneNumber(String phoneNumber);
	User get(Integer id);
}
