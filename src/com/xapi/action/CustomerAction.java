package com.xapi.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import com.xapi.entity.Customer;
import com.xapi.service.CustomerService;
import com.xapi.utils.PageBean;

@Controller
@Scope("prototype")
public class CustomerAction extends ActionSupport {
	@Autowired
	private CustomerService customerService;

	// 把查询出的列表放到值栈中去
	private List<?> customers;

	public List<?> getCustomers() {
		return customers;
	}

	// 把查询出的列表放到值栈中去（byUpdateCutomer-->根据id查客户）
	private Customer byUpdateCutomer;

	public Customer getByUpdateCutomer() {
		return byUpdateCutomer;
	}

	// 表达式封装获取表单数据（获取插入的信息）
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	// 表达式封装获取表单数据（模糊查询用户名）
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// 获取表单（修改时不同的id）
	private Integer cid;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	// 获取表单中提交的修改信息
	private Customer customer2;

	public Customer getCustomer2() {
		return customer2;
	}

	public void setCustomer2(Customer customer2) {
		this.customer2 = customer2;
	}

	/**
	 * 查询所有客户
	 * 
	 * @return
	 */
	public String list() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String string = request.getParameter("cc");
		System.out.println(string);
		Integer pageNum = Integer.valueOf(string);
		customers = customerService.getListForPage(pageNum,3).getBean();
		request.setAttribute("page", customerService.getListForPage(pageNum, 3));

		return "list";
	}

	/**
	 * 跳转添加页面
	 */
	public String toAddPage() {
		return "toAddPage";
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String addCustomer() {
		customerService.addCustomer(customer);
		return "addCustomer";
	}

	/**
	 * 模糊查询byusername
	 * 
	 * @return String
	 */
	
	public String like() {
		customers = customerService.selectByLike(username);
		System.out.println(customerService.getcount() + "sdfdsfsdfsdfdsfsdf");
		return "like";
	}

	/**
	 * 跳转到修改页面查询出要修改的人信息
	 */
	public String updateCustomer() {
		byUpdateCutomer = customerService.selectByCustomerId(cid);
		return "updateCustomer";
	}

	/**
	 * 提交修改客户信息
	 */

	public String okupdate() {
		customerService.updateOk(customer2);
		return "okupdate";
	}

	/**
	 * 删除客户
	 */
	public String deleteCustomer() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String parameter = request.getParameter("cid");
		Integer id = Integer.valueOf(parameter);
		customerService.deleteById(id);
		return "deleteCustomer";
	}
	/**
	 * 测试分页数据
	 */
	
	  public String findpageList(){
		  PageBean<?> listForPage =
	  customerService.getListForPage(6, 3); List<?> bean =
	  listForPage.getBean(); for (Object object : bean) {
	  System.out.println(object); }
	  
	  return NONE; }
	 

}
