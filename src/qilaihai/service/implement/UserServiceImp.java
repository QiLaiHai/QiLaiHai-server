package qilaihai.service.implement;

import qilaihai.dao.SchoolDao;
import qilaihai.dao.UserDao;
import qilaihai.domain.User;
import qilaihai.service.UserService;

public class UserServiceImp implements UserService {

	private UserDao mUserDao;
	private SchoolDao mSchoolDao;

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		mUserDao = userDao;
	}

	/**
	 * @param schoolDao
	 *            the schoolDao to set
	 */
	public void setSchoolDao(SchoolDao schoolDao) {
		mSchoolDao = schoolDao;
	}

	@Override
    public User regist(User user) {
		User repeatUser = mUserDao.findByPhoneNumber(user.getPhoneNumber());
		if (repeatUser != null) {
			return null;
		}
		mUserDao.save(user);
		return mUserDao.findByPhoneNumber(user.getPhoneNumber());
    }

	@Override
    public User login(String phoneNumber, String password) {
		
		User loginedUser = mUserDao.findByPhoneNumber(phoneNumber);
		if (loginedUser == null) {
			return null;
		} else {
			if (loginedUser.getPassword().equals(password)) {
				return loginedUser;	
			}
		}
	    return null;
    }
}
