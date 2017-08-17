package com.xapi.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xapi.dao.UserDao;
import com.xapi.entity.User;

@Transactional
@Component
public class UserDaoImpl implements UserDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Override
	public long login(User user) {
		long a =0;
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("user_name", user.getUser_name()));
		criteria.add(Restrictions.eq("user_password", user.getUser_password()));
		criteria.setProjection(Projections.rowCount());
			a	= (long) criteria.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return a;
	}
}
