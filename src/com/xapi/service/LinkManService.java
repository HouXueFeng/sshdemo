package com.xapi.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xapi.dao.LinkManDao;
import com.xapi.entity.LinkMan;
import com.xapi.utils.PageBean;

@Service
public class LinkManService {

	@Autowired
	private LinkManDao linkManDao;

	public PageBean<List<LinkMan>> getPageFindAllLinkMan(int currentPage, int pagesize) {
		return linkManDao.getListForPage(currentPage, pagesize);
	}
	
	public Integer getCount(){
		return linkManDao.getcount();
	}
}
