package com.util;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import net.sf.json.xml.XMLSerializer;

/**
 * 页面与服务器交换数据的处理结果<br>
 * 由于以BasicForm提交的数据需要返回Json格式数据，且数据中必要包含success表示是否成功；<br>
 * 同时，我们将结果描述放在msg里面，如果还要其他信息，可以通过put方法设置到Json中，以pop取出。
 * @author fbchen
 * @version 1.0  Date: 2009-7-7
 */
public class ActionResult implements Serializable, JSONString {
	private static final long serialVersionUID = 1L;
	public static final String KEY_SUCC = "success";
	public static final String KEY_MSG = "msg";
	
	
	protected Map<String,Object> result;

	public ActionResult() {
		result = new HashMap<String,Object>();
		result.put(KEY_SUCC, Boolean.TRUE);
		result.put(KEY_MSG, "");
	}
	
	public ActionResult(boolean succ, String msg) {
		result = new HashMap<String,Object>();
		result.put(KEY_SUCC, Boolean.valueOf(succ));
		result.put(KEY_MSG, msg);
	}
	
	public boolean isSuccess() {
		Boolean success = (Boolean)result.get(KEY_SUCC);
		return (success!=null && success.booleanValue());
	}
	
	public void success() {
		result.put(KEY_SUCC, Boolean.TRUE);
	}
	
	public void fail() {
		result.put(KEY_SUCC, Boolean.FALSE);
	}
	
	public void success(String msg) {
		result.put(KEY_SUCC, Boolean.TRUE);
		result.put(KEY_MSG, msg);
	}
	
	public void fail(String msg) {
		result.put(KEY_SUCC, Boolean.FALSE);
		result.put(KEY_MSG, msg);
	}

	public void setMsg(String msg) {
		result.put(KEY_MSG, msg);
	}
	
	public String getMsg() {
		String msg = (String)result.get(KEY_MSG);
		return (msg==null) ? "" : msg;
	}
	
	public void put(String key, Object value) {
		result.put(key, value);
	}
	
	public Object pop(String key) {
		return result.get(key);
	}
	
	public Map<String,Object> getResult() {
		return result;
	}

	/**
	 * 将Result中的值复制到另外一个JSONObject中
	 * @param other JSONObject
	 */
	public void accumulateTo(JSONObject other) {
		for (Iterator<String> it=result.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			other.put(key, result.get(key));
		}
	}
	
	public String toString() {
		return toJSON().toString();
	}
	
	public JSONObject toJSON() {
		return JSONObject.fromObject(result);
	}
	
	public String toXML() {
		return new XMLSerializer().write(toJSON(), "UTF-8");
	}
	
	public byte[] toBytes() {
		return toString().getBytes();
	}

	/* (non-Javadoc)
	 * @see net.sf.json.JSONString#toJSONString()
	 */
	public String toJSONString() {
		return this.toString();
	}
}
