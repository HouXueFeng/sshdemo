package com.xapi.action;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import com.xapi.entity.User;
import com.xapi.service.UserService;
@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {
	@Autowired
	private UserService userService;
	
	@Override
	public String execute() throws Exception {
		
		System.out.println("action");
//		添加操作
//		userService.add();
//		按照id查询
//		User user = userService.get();
		
//		System.out.println(user.getUsername());
		
		List<User> findAll = userService.findAll();
		for (User user : findAll) {
			System.out.println(user.getUsername()+":"+user.getAddress());
		}
		return NONE;
	}
}
