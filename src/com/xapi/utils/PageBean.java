package com.xapi.utils;

import java.util.List;

public class PageBean<E> {
	private List<?> bean; // 存放实体类集合
	private int currentPage=1; // 当前页
	private int pageSize; // 每页显示的条数
	private int totalPage; // 总页数
	private int totalCount; // 总条数
	private int nextPage;
	private int lastPage;
	
	
	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getlastPage() {
		if (currentPage <= 1) {
			return 1;
		}

		return currentPage - 1;
	}

	/**
	 * 下一页
	 * 
	 * @return
	 */
	public int getnextPage() {
		if (currentPage >= getTotalPage()) {
			return getTotalPage();
		}
		return currentPage + 1;
	}

	public int getTotalCount() {
		return totalCount;
	}

	

	public List<?> getBean() {
		return bean;
	}

	public void setBean(List<?> bean) {
		this.bean = bean;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return (totalCount + pageSize - 1) / pageSize;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}