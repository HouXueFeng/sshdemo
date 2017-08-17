package com.xapi.dao;

import java.util.List;

import com.xapi.entity.Customer;
import com.xapi.entity.LinkMan;
import com.xapi.utils.PageBean;

public interface LinkManDao {

	PageBean<List<LinkMan>> getListForPage(int currentPage, int pagesize);// 查询联系人列表
	
	Integer getcount();// 获取总条数
}
