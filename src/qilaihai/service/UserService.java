package qilaihai.service;

import qilaihai.domain.User;

public interface UserService {
	/**
	 * 进行登录操作
	 * @param phoneNumber
	 * @param password
	 * @return 登陆成功返回数据库中user信息，否则为null
	 */
	User login(String phoneNumber, String password);
	/**
	 * 进行注册操作
	 * @param user 要进行注册的用户
	 * @return 注册成功返回数据库中的user信息，否则为null
	 */
	User regist(User user);
}
