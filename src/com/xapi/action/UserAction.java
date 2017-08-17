package com.xapi.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.xapi.entity.User;
import com.xapi.service.UserService;


@Controller
@Scope("prototype")
public class UserAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	
	//表达式封装获取表单数据
	private User user;
	//向值栈中放入错误信息提示
	private String error;

	public String getError() {
		return error;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 登录
	 * 
	 */
	public String login() {
		long login2 = userService.login(user);
		System.out.println(login2+"111");
		System.out.println(user.getUser_name());
		System.out.println(user.getUser_password());
		System.out.println(login2);
		if (login2 == 1) {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return "loginsuccess";
		} else {
			error="账号或者密码错误";
			return "login";
		}
	}
}
