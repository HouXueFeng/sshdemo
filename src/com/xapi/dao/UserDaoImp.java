package com.xapi.dao;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import com.xapi.entity.User;

@Component
public class UserDaoImp implements UserDao {
//	@Autowired
	/**
	 * 这里的bean.xml中name=bean的id值
	 */
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	// 添加操作
	@Override
	public void add() {
		User user = new User();
		user.setAddress("咸阳市");
		user.setUsername("侯雪枫");
		// 实现添加操作
		hibernateTemplate.save(user);
	}

	@Override
	public User get() {
		return hibernateTemplate.get(User.class, 1);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) hibernateTemplate.find("from User");
	}
}
