package qilaihai.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import qilaihai.action.base.BaseAction;
import qilaihai.domain.User;
import qilaihai.domain.Weibo;

import com.opensymphony.xwork2.ActionContext;

public class WeiboGetAction extends BaseAction {
	
	private User mPoster = null;
	private Long mBeginTime = null;
	private Long mEndTime = null;
	private boolean mDescending = true;
	
    public String execute() throws Exception {
    	Date begin = null, end = null;
    	if (mBeginTime != null) {
    		begin = new Date(mBeginTime);
    	}
    	if (mEndTime != null) {
    		end = new Date(mEndTime);
    	}
    	List<Weibo> weiboList = getWeiboService().get(mPoster, begin, end, isDescending());
    	if (weiboList == null) {
    		weiboList = new ArrayList<Weibo>();
    	}
    	ActionContext.getContext().put("weiboList", weiboList);
    	
    	return SUCCESS;
    }

	/**
	 * @return the beginTime
	 */
	public Long getBeginTime() {
		return mBeginTime;
	}

	/**
	 * @return the endTime
	 */
	public Long getEndTime() {
		return mEndTime;
	}

	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(Long beginTime) {
		mBeginTime = beginTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Long endTime) {
		mEndTime = endTime;
	}

	/**
	 * @return the descending
	 */
	public boolean isDescending() {
		return mDescending;
	}

	/**
	 * @param descending the descending to set
	 */
	public void setDescending(boolean descending) {
		mDescending = descending;
	}

	/**
	 * @return the poster
	 */
	public User getPoster() {
		return mPoster;
	}

	/**
	 * @param poster the poster to set
	 */
	public void setPoster(User poster) {
		mPoster = poster;
	}
	
}
