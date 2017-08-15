package com.xapi.dao;

import java.util.List;

import com.xapi.entity.User;

public interface UserDao {

	void add();
	User get();
	List<User>findAll();

}
