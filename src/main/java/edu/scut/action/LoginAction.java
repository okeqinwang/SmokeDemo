package edu.scut.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
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

	@Action(value = "ask", results = {
			@Result(name = "success", location = "/index.html"),
			@Result(name = "error", location = "/error.jsp") })
	public String test() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) ctx
				.get(ServletActionContext.HTTP_RESPONSE);
		System.out.println("CometAction.longPolling.start");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread longThread = new Thread() {
			public void run() {
				try {
					// 这里模拟全局事件监听，如果其他模块有需要发送消息就发送一个事件，然后休眠停止，发送消息。
					sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		};
		longThread.run();
		writer.println("your hava a new message\n");
		writer.flush();
		writer.close();
		System.out.println("-----CometAction.longPolling.end");
		// return null;
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
