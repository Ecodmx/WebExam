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
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

//import com.primeton.dgs.dsm.core.util.BeanUtil;



/**
 * Command Action类辅助，例如帮助输出、获取输入等
 * @author fbchen
 * @version 2009-02-04
 */
public class ActionHelper {
	static Logger log = Logger.getLogger(ActionHelper.class);
	public static final String XML_CONTENTTYPE  = "text/xml; charset=UTF-8";
	public static final String HTML_CONTENTTYPE = "text/html; charset=UTF-8";

	public ActionHelper() {
		super();
	}

	
	/**
	 * 输出内容到response。可以自行指定输出的CONTENT-TYPE。
	 * @param response HttpServletResponse
	 * @param content 内容，如Json,XML数据
	 * @throws IOException 网络异常
	 */
	public static void output(HttpServletResponse response, String content) throws IOException {
		response.getWriter().print(content);
		response.getWriter().flush();
	}
	
	/**
	 * 输出XML数据到response。
	 * @param xmlTree XMLTree
	 * @throws IOException 网络异常
	 */
//	public static void output(HttpServletResponse response, XMLTree xmlTree) throws IOException {
//		response.setHeader("ContentType", XML_CONTENTTYPE);
//		response.setContentType(XML_CONTENTTYPE);
//		xmlTree.output(response.getOutputStream());
//	}
	
	/**
	 * 输出Result内容到response，转换成Json格式
	 * @param result ActionResult
	 * @throws IOException 网络异常
	 */
	public static void output(HttpServletResponse response, ActionResult result) throws IOException {
		outputJsonObject(response, result.toJSON());
	}
	
	/**
	 * 输出Json内容到response
	 * @param response HttpServletResponse
	 * @param json JSON格式数据
	 * @throws IOException 网络异常
	 */
	public static void outputJsonObject(HttpServletResponse response,
			JSONObject json) throws IOException {
		response.setContentType(HTML_CONTENTTYPE);
		output(response, json.toString());
	}
	
	/**
	 * 输出Json内容到response
	 * @param response HttpServletResponse
	 * @param json JSON格式数据
	 * @throws IOException 网络异常
	 */
	public static void outputJsonArray(HttpServletResponse response,
			JSONArray json) throws IOException {
		response.setContentType(HTML_CONTENTTYPE);
		output(response, json.toString());
	}
	
	/**
	 * 输出XML数据。将JSONArray的数据转换成XML数据，并输出。
	 * @param response HttpServletResponse
	 * @param array JSON格式数据
	 * @param count 总记录数
	 * @throws IOException 网络异常
	 */
//	public static void outputXmlList(HttpServletResponse response,
//			JSONArray array, int count) throws IOException {
//		outputXmlList(response, array, count, null);
//	}
	
	/**
	 * 输出XML数据。将JSONArray的数据转换成XML数据，并输出。
	 * @param response HttpServletResponse
	 * @param array JSON格式数据
	 * @param count 总记录数
	 * @param result 其他数据，将作为独立的xml标签
	 * @throws IOException 网络异常
	 */
//	public static void outputXmlList(HttpServletResponse response,
//			JSONArray array, int count, ActionResult result) throws IOException {
//		JSONObject json = new JSONObject();
//		json.put("list", array);
//		json.put("totalCount", count);
//		if (result != null) {
//			result.accumulateTo(json);
//		}
//		
//		// 转成XML，并输出
//		String xml = new XMLSerializer().write(json, "UTF-8");
//		XMLTree xmlTree = null;
//		try {
//			xmlTree = new XMLTree(xml);
//		} catch (Exception e) {
//			throw (IOException)(new IOException(e.toString()).initCause(e));
//		}
//		xmlTree.renameNodes("row", xmlTree.getRootElement(), "list/e");
//		output(response, xmlTree);
//	}
//	
	
	/**
	 * 直接取所有的request参数
	 * @param request HTTP请求
	 */
	public static Page buildPage(HttpServletRequest request) {
		Page pag = page(request);
		Enumeration<?> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			pag.setParameter(name, request.getParameter(name));
		}
		return pag;
	}

	/**
	 * 获取request参数中的分页参数。<br>
	 * limit-每页的最大记录数；start-开始的记录位置
	 * @param request HTTP请求
	 * @return Page 包含分页参数
	 */
	public static Page page(HttpServletRequest request) {
		//添加if条件nui分页功能分支，pageIndex是nui中的第几页，pageSize是nui中的每页显示条数，
		//if条件中，只有在nui中可以获取到pageIndex参数，在ext中无pageIndex参数
		Page pag = new Page();
		String pageIndex = request.getParameter("pageIndex"); 
		
		
		/*
		 * 这两个参数是为了datatable.js插件而准备的分页参数
		 */
		String iDisplayStart = request.getParameter("start");
		String iDisplayLength = request.getParameter("length");
		String ismetadata = request.getParameter("ismetadata"); //判断元数据
		
		if (null != pageIndex && !pageIndex.equals("")) {
			try {
				pag.setPageSize(NumberUtils.toInt(request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE));
			} catch (NumberFormatException ex) {
				log.error("[pageSize] to number error", ex);
			}
			try {
				int start = NumberUtils.toInt(request.getParameter("pageIndex"));
				pag.setStartIndex(start * pag.getPageSize()); // 使用startIndex就够了，currPage可不用
				pag.setCurrPage(start+1);
			} catch (NumberFormatException ex) {
				log.error("[pageIndex] to number error", ex);
			}
		} 
		else if (null != iDisplayLength && !iDisplayLength.equals("")) {// for
																			// ace
																			// datatable.js
			try {
				pag.setPageSize(NumberUtils.toInt(iDisplayLength, Page.DEFAULT_PAGE_SIZE));
			} catch (NumberFormatException ex) {
				log.error("[pageSize] to number error", ex);
			}
			try {
				int start = NumberUtils.toInt(iDisplayStart);
				pag.setStartIndex(start); // 使用startIndex就够了，currPage可不用
				pag.setCurrPage(start / pag.getPageSize()+1);
			} catch (NumberFormatException ex) {
				log.error("[pageIndex] to number error", ex);
			}

		}else {
			try {
				pag.setPageSize(NumberUtils.toInt(request.getParameter("limit"), Page.DEFAULT_PAGE_SIZE));
			} catch (NumberFormatException ex) {
				log.error("[limit] to number error", ex);
			}
			try {
				int start = NumberUtils.toInt(request.getParameter("start"), 0);
				pag.setStartIndex(start); // 使用startIndex就够了，currPage可不用
				int currentPage = (start / pag.getPageSize() + 1);
				pag.setCurrPage(currentPage);
			} catch (NumberFormatException ex) {
				log.error("[start] to number error", ex);
			}

		}
		
		return pag;
	}

	/**
	 * 将request中的参数绑定到指定的pojo的bean实例中。<br>
	 * 只支持一些基本的类型，对于复杂的类型如Date可能无法绑定。<br>
	 * @param request HTTP请求
	 * @param pojo 对象
	 * @throws InvocationTargetException 无法读取对象
	 * @throws IllegalAccessException 无法读取对象
	 */
//	public static void bind(HttpServletRequest request, Object pojo)
//			throws IllegalAccessException, InvocationTargetException {
//		Map<String,Object> map = bindmap(request);
//		Map<?,?> props = BeanUtil.describeClass(pojo.getClass());
//		for (Iterator<?> it = props.keySet().iterator(); it.hasNext();) {
//			String pname = (String) it.next();
//			PropertyDescriptor desc = (PropertyDescriptor) props.get(pname);
//			if (map.get(pname) == null) {
//				continue;
//			}
//			if (!desc.getPropertyType().isInstance(map.get(pname))) {
//				String v = (String) map.get(pname);
//				Object o = BeanUtil.convert(desc.getPropertyType(), v);
//				map.put(pname, o);
//			}
//		}
//		BeanUtils.populate(pojo, map);
//	}

	/**
	 * 将request中的参数获取到一个Map中并返回。<br>
	 * @param request HTTP请求
	 * @return 所有参数
	 */
	public static Map<String,Object> bindmap(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		Enumeration<?> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			map.put(name, request.getParameter(name));
		}
		return map;
	}
	
	/**
	 * 将request中的参数获取到一个Map中并返回。<br>
	 * @param request
	 * @param casts 类型转换说明，如：{"pname:Double", "qname:BigDecimal"}
	 * @return Map&lt;String,Object&gt;
	 */
//	public static Map<String,Object> bindmap(HttpServletRequest request, String[] casts) {
//		Map<String,String> cast = new HashMap<String,String>();
//		for (int i=0; casts!=null && i<casts.length; i++) {
//			String[] type = casts[i].split(":");
//			cast.put(type[0].trim(), type[1].trim());
//		}
//		
//		Map<String,Object> map = new HashMap<String,Object>();
//		Enumeration<?> names = request.getParameterNames();
//		while (names.hasMoreElements()) {
//			String name = (String) names.nextElement();
//			String value = request.getParameter(name);
//			Object castValue = value;
//			if (cast.containsKey(name)) {
//				castValue = BeanUtil.convert(cast.get(name), value);
//			}
//			map.put(name, castValue);
//		}
//		return map;
//	}
	
	/**
	 * 获取当前登陆用户
	 * @param request HTTP请求
	 * @return 用户登录信息
	 */
//	public static UserProfile getCurrUser(HttpServletRequest request) {
//		return (UserProfile)request.getSession().getAttribute(UserProfile.KEY);
//	}
	
	/**
	 * 下载数据/文件
	 * @param response HTTP输出
	 * @param file 文件
	 * @throws IOException IO异常
	 */
	public static void download(HttpServletResponse response, File file) throws IOException {
		String name = file.getName();
		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(name.getBytes("GBK"),"ISO8859_1"));
		FileInputStream in = new FileInputStream(file);
		BufferedInputStream reader = new BufferedInputStream(in);
		int b = -1;
		int buff = 0;
		while ((b=reader.read()) != -1) {
			response.getOutputStream().write(b);
			buff++;
			if (buff > 1024) {
				buff = 0;
				response.getOutputStream().flush();
			}
		}
        response.getOutputStream().flush();
	}


	/*     */   public static void download(HttpServletResponse response, InputStream inputStream, String contentType, String fileName)
			/*     */     throws IOException
			/*     */   {
			/* 386 */     response.setContentType(StringUtils.isEmpty(contentType) ? "application/octet-stream" : contentType);
			/*     */     
			/* 388 */     response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"), "ISO8859_1"));
			/*     */     
			/* 390 */     response.setStatus(200);
			/* 391 */     BufferedInputStream reader = null;
			/*     */     try {
			/* 393 */       reader = new BufferedInputStream(inputStream);
			/* 394 */       OutputStream output = response.getOutputStream();
			/* 395 */       int b = -1;
			/* 396 */       int buff = 0;
			/* 397 */       while ((b = reader.read()) != -1) {
			/* 398 */         output.write(b);
			/* 399 */         buff++;
			/* 400 */         if (buff > 10240) {
			/* 401 */           buff = 0;
			/* 402 */           output.flush();
			/*     */         }
			/*     */       }
			/* 405 */       output.flush();
			/*     */     } finally {
			/* 407 */       if (reader != null) {
			/* 408 */         reader.close();
			/*     */       }
			/* 410 */       if (inputStream != null) {
			/* 411 */         inputStream.close();
			/*     */       }
			/*     */     }
			/*     */   }
			/*     */ }
	

