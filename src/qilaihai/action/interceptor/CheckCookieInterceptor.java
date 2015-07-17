package qilaihai.action.interceptor;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import qilaihai.action.base.BaseInterceptor;

import com.opensymphony.xwork2.ActionInvocation;

public class CheckCookieInterceptor extends BaseInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 检测是否登陆
    	Cookie[] cookies = ServletActionContext.getRequest().getCookies();
    	Integer userId = null;
    	try {
        	for (Cookie c : cookies) {
        		if (c.getName().equals("qilaihai_user")) {
        			userId = Integer.parseInt(c.getValue());
        			break;
        		}
        	}	
    	} catch (Exception e) {
    		userId = null;
    		return FAILED;
    	}
    	if (userId == null) {
    		return FAILED;
    	}
    	invocation.getInvocationContext().put("userId", userId);    	
		return invocation.invoke();
	}

}
