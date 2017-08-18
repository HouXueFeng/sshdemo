package com.xapi.dao;

import java.util.List;

import com.xapi.entity.Customer;
import com.xapi.utils.PageBean;

public interface CustomerDao {

	List<Customer> findAll();// 查询客户列表

	void addCustomer(Customer customer);// 插入客户

	List<Customer> selectByLike(String username);// 模糊查询

	Customer selectByCustomerId(Integer cid);// 根据id查询客户信息

	void updateOk(Customer byUpdateCutomer);// 确认修改

	void deleteById(Integer id);// 删除

	Integer getcount();// 获取总条数

	PageBean<List<Customer>> getListForPage(int offset, int length);

	Object deletes(String parameterValues);
	

}
