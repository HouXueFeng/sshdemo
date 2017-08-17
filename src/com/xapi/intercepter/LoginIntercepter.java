package com.xapi.intercepter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.xapi.entity.User;

public class LoginIntercepter extends MethodFilterInterceptor {

	/**
	 * 继承MethodFilterInterceptor
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//写拦截器的逻辑
		//得到session判断session的值
		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user!=null) {
			//表示是登录状态
			//因为拦截器的执行是action创建之后，方法执行之前所以
			//要做类似于放行的操作
		return	invocation.invoke();//放行
		}else{
			//表示不是登录状态
			//不是登录状态就不执行action中的方法login，返回登陆页面
		//到result中找名称是login的值，到配置的jsp路径中去
			return "login";
		}
	}
}
