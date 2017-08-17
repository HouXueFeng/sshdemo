package com.xapi.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.xapi.entity.LinkMan;
import com.xapi.service.LinkManService;
import com.xapi.utils.PageBean;

@Controller
@Scope("prototype")
public class LinkManAction extends ActionSupport {

	@Autowired
	private LinkManService linkManService;

	private List<?> linkMans;

	public List<?> getLinkMans() {
		return linkMans;
	}

	public String list() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String string = request.getParameter("cc");
		System.out.println(string);
		Integer pageNum = Integer.valueOf(string);
		linkMans=linkManService.getPageFindAllLinkMan(pageNum, 3).getBean();
		request.setAttribute("page", linkManService.getPageFindAllLinkMan(pageNum, 3));
		return "list";
	}
}
