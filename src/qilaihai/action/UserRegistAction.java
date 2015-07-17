package qilaihai.action;

import qilaihai.action.base.BaseAction;
import qilaihai.domain.User;
import qilaihai.service.UserService;

public class UserRegistAction extends BaseAction {
    private static final long serialVersionUID = 1L;
	
    private User mUser;

	/**
	 * @return the user
	 */
	public User getUser() {
		return mUser;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		mUser = user;
	}

	/**
	 * 用户进行注册
	 */
    @Override
    public String execute() throws Exception {
    	User registedUser = getUserService().regist(getUser());
    	if (registedUser != null) {
    		return SUCCESS;
    	} 
    	return FAILED;
    }
    
}
