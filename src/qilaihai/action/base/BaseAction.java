package qilaihai.action.base;

import qilaihai.service.UserService;
import qilaihai.service.WeiboService;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	
	public static final String FAILED = "failed";
	
	private static final long serialVersionUID = 1L;
	
	private UserService mUserService;
	private WeiboService mWeiboService;
	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return mUserService;
	}
	/**
	 * @return the weiboService
	 */
	public WeiboService getWeiboService() {
		return mWeiboService;
	}
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		mUserService = userService;
	}
	/**
	 * @param weiboService the weiboService to set
	 */
	public void setWeiboService(WeiboService weiboService) {
		mWeiboService = weiboService;
	}

}
