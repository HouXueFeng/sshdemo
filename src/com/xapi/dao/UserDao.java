package com.xapi.dao;

import com.xapi.entity.User;

public interface UserDao {
	//登录
	long login(User user);
}
