package com.xapi.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xapi.dao.UserDao;
import com.xapi.entity.User;
@Transactional
@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	public void add(){
		System.out.println("service");
		userDao.add();
		//	UserDao dao=new UserDaoImp();	
	}
	public User get() {
		return userDao.get();
	}
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}
}
