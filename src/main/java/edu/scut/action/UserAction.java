package edu.scut.action;

import com.opensymphony.xwork2.ActionSupport;



public class UserAction  extends ActionSupport{

	private String flag;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String test(){
		System.out.println("it is a test aciton with xml");
		System.out.println("flag==="+flag);
		if(flag.equals("0")){
			return SUCCESS;
		}
		return ERROR;
	}
}
