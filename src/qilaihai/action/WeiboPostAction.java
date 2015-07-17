package qilaihai.action;

import java.io.File;
import java.util.List;

import qilaihai.action.base.BaseAction;
import qilaihai.domain.Weibo;
import qilaihai.service.WeiboService;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class WeiboPostAction extends BaseAction {

	private Weibo mWeibo;
	private List<File> mImage;
	private List<String> mImageName; 
	
	@Override
    public String execute() throws Exception {
    	// 空微博
    	if (mWeibo == null) {
    		return FAILED;
    	}
    	
    	File imageFile = null;
    	String imageName = null;
    	
    	if (mImageName != null && getImage() != null) {
    		if (mImageName.size() != mImage.size()) {
        		return FAILED;
        	} else {
        		if (mImage.size() > 0) {
        			// 暂时只保存一张图片
        			imageFile = mImage.get(0);
        			imageName = mImageName.get(0);
        		}
        	}
    	} 
//    	else if ((mImageName == null) ^ (mImage == null)) {
//    		return FAILED;
//    	}
    	
    	if ((mWeibo.getText() == null) && (mImageName == null)) {
    		return FAILED;
    	}
    	
    	Integer userId = (Integer) ActionContext.getContext().get("userId");
    	
    	
    	if(getWeiboService().post(userId, mWeibo.getText(), imageName, imageFile) 
    			== WeiboService.POST_FALSE) {
    		return FAILED;
    	} else {
    		return SUCCESS;
    	}
    }

	/**
	 * @return the weibo
	 */
	public Weibo getWeibo() {
		return mWeibo;
	}

	/**
	 * @param weibo
	 *            the weibo to set
	 */
	public void setWeibo(Weibo weibo) {
		mWeibo = weibo;
	}

	/**
	 * @return the image
	 */
	public List<File> getImage() {
		return mImage;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(List<File> image) {
		mImage = image;
	}

	/**
	 * @return the imageName
	 */
	public List<String> getImageName() {
		return mImageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(List<String> imageName) {
		mImageName = imageName;
	}	
}
