package com.xapi.dao.impl;

import java.util.List;
import com.xapi.utils.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xapi.dao.CustomerDao;
import com.xapi.entity.Customer;

@Transactional
@Component
public class CustomerImp implements CustomerDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	/**
	 * 查询所有客户
	 */

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) hibernateTemplate.find("from Customer");
	}

	/**
	 * 键入客户
	 */
	@Override
	public void addCustomer(Customer customer) {
		hibernateTemplate.save(customer);
	}

	/**
	 * 模糊查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> selectByLike(String username) {
		return (List<Customer>) hibernateTemplate.find("from Customer where custName like '%" + username + "%'");
	}

	/**
	 * 根据id查询
	 */
	@Override
	public Customer selectByCustomerId(Integer cid) {
		Customer customer = hibernateTemplate.get(Customer.class, cid);
		return customer;
	}

	/**
	 * 修改功能
	 */
	@Override
	public void updateOk(Customer byUpdateCutomer) {
		hibernateTemplate.update(byUpdateCutomer);
	}

	/**
	 * 删除
	 */
	@Override
	public void deleteById(Integer id) {
		Customer customer = hibernateTemplate.get(Customer.class, id);
		hibernateTemplate.delete(customer);
	}

	@Override
	public Integer getcount() {
		Integer integer = 0;
		final String hql = "select count(*) from Customer";
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

	@Override
	public PageBean<List<Customer>> getListForPage(int currentPage, int pagesize) {
		PageBean<List<Customer>> pageBean = new PageBean<>();
		@SuppressWarnings("unchecked")
		List<Customer> list = (List<Customer>) hibernateTemplate.execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				List<Customer> list = session.createQuery("from Customer").setFirstResult((currentPage - 1) * pagesize)
						.setMaxResults(pagesize).list();
				return list;
			}
		});
		pageBean.setBean(list);
		pageBean.setTotalCount(getcount());
		pageBean.setCurrentPage(currentPage);
		int currentPage2 = pageBean.getCurrentPage();
		System.out.println(currentPage2 + "--------------------------");
		pageBean.setPageSize(pagesize);
		return pageBean;
	}

	/**
	 * 批量删除
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object deletes(String str) {
		return hibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {

				String[] split = str.split(",");
				for (int i = 0; i < split.length; i++) {
					/**
					 * 级联删除
					 */
					Customer customer = hibernateTemplate.get(Customer.class, Integer.valueOf(split[i].trim()));
					hibernateTemplate.delete(customer);

				}
				/*这里可以使用in来进行删除但是要注意的是：要删除两个表中的数据
				 * Query query =
				 * session.createQuery("delete from Customer c where c.cid in ("
				 * +str+")"); return query.executeUpdate();
				 */
				return null;
			}
		});

	}

}
