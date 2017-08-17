package com.xapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xapi.dao.CustomerDao;
import com.xapi.entity.Customer;
import com.xapi.utils.PageBean;
@Service
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	public void addCustomer(Customer customer){
		customerDao.addCustomer(customer);
	}
	public List<Customer> selectByLike(String username) {
		return  (List<Customer>) customerDao.selectByLike(username);
	}

	public Customer selectByCustomerId(Integer cid) {
	return	customerDao.selectByCustomerId(cid);
	}

	public void updateOk(Customer customer) {
		 customerDao.updateOk(customer);
		
		
	}
	public void deleteById(Integer id) {
		customerDao.deleteById(id);
	}
	public Integer getcount() {
		
		return customerDao.getcount();
	}
	
	public PageBean<List<Customer>> getListForPage( int currentPage, int pagesize) {
		
		return customerDao.getListForPage(currentPage, pagesize);
		
	}
}
