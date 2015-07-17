package qilaihai.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import qilaihai.domain.User;
import qilaihai.domain.Weibo;

public interface WeiboService {
	
	int POST_FALSE = 0;
	int POST_SUCCESS = 1;
	
	int post(Integer posterId, String text, String picture, File pictureFile);
	List<Weibo> get(User poster, Date begin, Date end, boolean isDescending);
}
