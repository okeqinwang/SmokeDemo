package edu.scut.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("struts-default")
@Namespace("/")
@Results({ @Result(name = "success", location = "/main.jsp"),
		@Result(name = "error", location = "/login.jsp") })
public class LoginAction extends ActionSupport {

	private String uname;
	private String pwd;

	@Action(value = "login", results = {
			@Result(name = "success", location = "/index.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public String login() {
		System.out.println("uname==" + uname);
		System.out.println("pwd===" + pwd);
		System.out.println("this is a annotation way for action");
		return SUCCESS;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
