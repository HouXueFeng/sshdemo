package com.xapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xapi.dao.UserDao;
import com.xapi.entity.User;
@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public long login(User user) {
		return userDao.login(user);
	}

}
