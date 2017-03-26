/*
 * Copyright 2009 by Primeton Corporation.
 * Address:Caihefang Road No.8 Haidian District, Beijing, 
 * 
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Primeton Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Primeton.
 */

package com.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Page信息类
 * @author fbchen
 * @version 1.0  Date: 2009-7-7
 */
public class Page implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_PAGE_SIZE = 15;
	public static final int DEFAULT_MAX_RESULT = 2000;

	protected int totalRecs;   // 总记录数
	
	protected int totalPages;	// 总页数

	protected int pageSize = DEFAULT_PAGE_SIZE;	// 每页记录数

	protected int startIndex; //开始记录Index，从0开始

	protected int currPage;	// 当前页数，从1开始
	
	protected int currRecords;	// 当前页记录数

	protected List<Integer> pageSizeList; // 每页记录数的下拉
	
	protected Map<String,Object> parameters =  new HashMap<String,Object>(); //查询参数
	
	protected List<?> queryResult;
	
	//constructor
	public Page() {
	}
	
	//constructor
	public Page(int currPage, int pageSize) {
		this.currPage = currPage;
		this.pageSize = pageSize;
	}
	
	//constructor
	public Page(int currPage) {
		this.currPage = currPage;
	}
	
	//constructor
	public Page(int currPage, boolean selectAll) {
		this.currPage = currPage;
		if (selectAll) {
			this.pageSize = DEFAULT_MAX_RESULT;
		}
	}

	/**
	 * 刷新总记录数、当前页数
	 */
	public void refreshPageNum() {
		totalPages = (totalPages <= 1) ? 1 : totalPages;
		currPage = (currPage <= 1) ? 1 : currPage;
	}
	
	/**
	 * 重置为第一页参数
	 */
	public Page reset() {
		this.startIndex = 0;
		this.currPage = 1;
		this.totalRecs = this.totalPages = 0;
		return this;
	}
	
	/**
	 * 重置为下页参数
	 */
	public Page next() {
		this.startIndex = this.startIndex + this.pageSize;
		this.currPage = this.currPage + 1;
		return this;
	}
	
	
	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Integer> getPageSizeList() {
		return pageSizeList;
	}

	public void setPageSizeList(List<Integer> pageSizeList) {
		this.pageSizeList = pageSizeList;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRecs() {
		return totalRecs;
	}

	public void setTotalRecs(int totalRecs) {
		this.totalRecs = totalRecs;
	}

	public int getCurrRecords() {
		return currRecords;
	}

	public void setCurrRecords(int currRecords) {
		this.currRecords = currRecords;
	}
	
	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public Map<String,Object> getParameters() {
		return parameters;
	}
	
	public Page setParameters(Map<String,Object> parameters) {
		this.parameters = parameters;
		return this;
	}

	public Object getParameter(String key) {
		return parameters.get(key);
	}

	public Page setParameter(String key, Object param) {
		parameters.put(key, param);
		return this;
	}

	public Page setParameters(Object[] parameters) {
		this.getParameters().clear();
		for (int i = 0; i < parameters.length; i++) {
			this.setParameter(String.valueOf(i), parameters[i]);
		}
		return this;
	}
	
	public Object removeParameter(String key) {
		return parameters.remove(key);
	}
	
	public String getParamStr(String key) {
		return (String)parameters.get(key);
	}
	
	public Integer getParamInt(String key) {
		return (Integer)parameters.get(key);
	}
	
	public Long getParamLong(String key) {
		return (Long)parameters.get(key);
	}
	
	/** 
	 * 给参数增加模糊查询的匹配符：%，前后匹配。<br>
	 * names可以以逗号分割，一次性指定多个参数名称。若某参数值为空，系统将忽略该参数。
	 */
//	public Page addLike(String names) {
//		BeanUtil.addHsLike(getParameters(), names);
//		return this;
//	}
	
	/** 
	 * 给参数增加模糊查询的匹配符：%，同时将内容转换成为大写。<br>
	 * names可以以逗号分割，一次性指定多个参数名称。若某参数值为空，系统将忽略该参数。
	 */
//	public Page addLikeUppercase(String names) {
//		BeanUtil.addHsLike(getParameters(), names, true);
//		return this;
//	}

	public List<?> getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(List<?> queryResult) {
		this.queryResult = queryResult;
	}
}
