package qilaihai.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.CookiesAware;

import qilaihai.action.base.BaseAction;
import qilaihai.domain.User;
import qilaihai.service.UserService;

public class UserLoginAction extends BaseAction {

	private User mUser;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() {
		try {
			User loginedUser = getUserService().login(mUser.getPhoneNumber(),
			        mUser.getPassword());
			if (loginedUser != null) {
				Cookie cookie = new Cookie("qilaihai_user", loginedUser.getId().toString());
				// 有效期暂时设置为3天
				cookie.setMaxAge(60 * 60 * 24 * 3);
				ServletActionContext.getResponse().addCookie(cookie);
				return SUCCESS;
			}
		} catch (Exception e) {
		}
		return FAILED;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return mUser;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		mUser = user;
	}

}
