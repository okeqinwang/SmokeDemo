package edu.scut.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;


@ParentPackage("base")
@Namespace("/")
@Results({ @Result(name = "json", type = "json", params = { "root", "msg" ,"excludeProperties","flag"}) })

public class JsonAction  extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private Map<String, Object> msg;
	private String key= "this is a key";
	
	@Action(value = "testjson", results = {
			@Result(name = "success", location = "/index.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public String json() {
		msg = new HashMap<String, Object>();
		msg.put("flag", "success");
		Map<String, String> user = new HashMap<String, String>();
		user.put("name", "张三");
		user.put("age", "34");
		msg.put("user", user);
		if("0".equals(key)){
			return "json";
		}
		return "success";
	}

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	

}