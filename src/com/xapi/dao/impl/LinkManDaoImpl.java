package com.xapi.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.xapi.dao.LinkManDao;
import com.xapi.entity.Customer;
import com.xapi.entity.LinkMan;
import com.xapi.utils.PageBean;

@Component
public class LinkManDaoImpl implements LinkManDao{
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 查询联系人列表
	 */
	@Override
	public PageBean<List<LinkMan>> getListForPage(int currentPage, int pagesize) {
		PageBean<List<LinkMan>> pageBean = new PageBean<>();
		@SuppressWarnings("unchecked")
		List<LinkMan> list = (List<LinkMan>) hibernateTemplate.execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				List<Customer> list = session.createQuery("from LinkMan").setFirstResult((currentPage - 1) * pagesize)
						.setMaxResults(pagesize).list();
				return list;
			}
		});
		pageBean.setBean(list);
		pageBean.setTotalCount(getcount());
		pageBean.setCurrentPage(currentPage);
		int currentPage2 = pageBean.getCurrentPage();
		System.out.println(currentPage2+"--------------------------");
		pageBean.setPageSize(pagesize);
		return pageBean;
	}

	/**
	 * 查询总条数
	 */
	@Override
	public Integer getcount() {
		Integer integer = 0;
		final String hql = "select count(*) from LinkMan";
		integer = (Integer) hibernateTemplate.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				Object uniqueResult = query.uniqueResult();
				long a = (long) uniqueResult;
				int b = (int) a;
				
				return b;
			}
		});
		return integer;
	}

}
