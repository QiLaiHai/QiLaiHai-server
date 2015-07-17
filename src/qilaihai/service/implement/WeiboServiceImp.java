package qilaihai.service.implement;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import qilaihai.dao.FileDao;
import qilaihai.dao.SchoolDao;
import qilaihai.dao.UserDao;
import qilaihai.dao.WeiboDao;
import qilaihai.domain.FileMap;
import qilaihai.domain.User;
import qilaihai.domain.Weibo;
import qilaihai.service.WeiboService;

public class WeiboServiceImp implements WeiboService {

	private UserDao mUserDao;
	private SchoolDao mSchoolDao;
	private WeiboDao mWeiboDao;
	private FileDao mFileDao;

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

	/**
	 * @param weiboDao
	 *            the weiboDao to set
	 */
	public void setWeiboDao(WeiboDao weiboDao) {
		mWeiboDao = weiboDao;
	}

	@Override
	public int post(Integer posterId, String text, String picture,
	        File pictureFile) {
		Weibo toStore = new Weibo();

		User poster = null;
		// 确认这个poster在数据库中是否存在
		if ((poster = mUserDao.get(posterId)) == null) {
			return POST_FALSE;
		}

		if (picture != null && pictureFile != null) {

			Serializable storedFileId = mFileDao.save(pictureFile,
			        getFileName(picture), getFileSuffix(picture));
			if (storedFileId == null) {
				return POST_FALSE;
			} else {
				picture = mFileDao.get((Integer) storedFileId).getHashCode();
			}
		}

		// 刷新为数据库中的poster
		toStore.setPoster(poster);
		toStore.setPicture(picture);
		toStore.setText(text);
		toStore.setPostTime(new Date());
		toStore.setAgreement(0);
		toStore.setDisagreement(0);

		mWeiboDao.save(toStore);
		return POST_SUCCESS;
	}

	@Override
	public List<Weibo> get(User poster, Date begin, Date end,
	        boolean isDescending) {
		if (poster != null && poster.getId() == null) {
			if (poster.getPhoneNumber() == null) {
				// 啥都没有，get个毛
				return null;
			} else {
				poster = mUserDao.findByPhoneNumber(poster.getPhoneNumber());
			}
		}
		return mWeiboDao.getByTimeInterval(poster, begin, end, isDescending);
	}

	/**
	 * @return the fileDao
	 */
	public FileDao getFileDao() {
		return mFileDao;
	}

	/**
	 * @param fileDao
	 *            the fileDao to set
	 */
	public void setFileDao(FileDao fileDao) {
		mFileDao = fileDao;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filePath
	 *            含有文件名和扩展名的一个文件，如 text.txt
	 * @return null为不存在
	 */
	public String getFileSuffix(String filePath) {
		int lastDot = filePath.lastIndexOf(".");
		String suffix = lastDot >= 0 && lastDot < filePath.length() - 1 ? filePath
		        .substring(lastDot + 1, filePath.length()) : null;
		return suffix;
	}

	/**
	 * 获取文件名，不包含扩展名
	 * 
	 * @param fileName
	 *            文件名，包含扩展名，但是不包含路径
	 * @return null 为不存在
	 */
	public String getFileName(String fileName) {
		int lastDot = fileName.lastIndexOf(".");
		String name = lastDot != -1 ? fileName.substring(0, lastDot) : fileName;
		return name;
	}

}
