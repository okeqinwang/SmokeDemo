package edu.scut.action;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import edu.scut.util.RunShellUtil;


@ParentPackage("base")
@Namespace("/")
@Results({ @Result(name = "json", type = "json", params = { "root", "msg" }) })

public class TasKAction  extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private Map<String, Object> msg;
	private String parms;
	
	private String filename = "mylog.log";
	private long  lastTimeFileSize =0;
	private String result = "no result";
	private String stop ="0";
	
	
	 private void realtimeShowLog(File logFile) throws IOException{   
		 	
	        //指定文件可读可写   
	        final RandomAccessFile randomFile = new RandomAccessFile(logFile,"r");   
	        System.out.println("lastTimeFileSize==="+lastTimeFileSize);
	        System.out.println("randomFile.length()==="+randomFile.length());
	        
	        if( lastTimeFileSize>0&&(lastTimeFileSize == randomFile.length())){
	        	result = "finished";
	        	stop = "1";
	        	return ;
	        }
	    	StringBuffer  sb = new StringBuffer();
	        //获得变化部分的   
	        randomFile.seek(lastTimeFileSize);   
	        String tmp = "";   
	        while( (tmp = randomFile.readLine())!= null) {   
//	            System.out.println(new String(tmp.getBytes("utf-8")));   
	            sb.append(tmp);
	        }   
	     
	        lastTimeFileSize = randomFile.length();   
	        result = sb.toString();
	        randomFile.close();
	    }   
	
	
	@Action(value = "commitTask")
	public String commitTask() {
		System.out.println("commit task");
		
		Thread thread = new Thread(){
			public  void run(){
				System.out.println(Thread.currentThread().getName());
				RunShellUtil obj = new RunShellUtil();
//				String domainName = "baidu.com";
//				String command = "ping -c 5 " + domainName;
				List<String> cmds = new ArrayList<String>();
				cmds.add("sh");
				cmds.add("-c");
				cmds.add("ping -c 5 baidu.com  > test.txt");
				String []  cmd = cmds.toArray(new String [cmds.size()]);
				obj.executeCommand(cmd);
			}
		};
		
		thread.run();
		
		msg = new HashMap<String, Object>();
		msg.put("flag", "success");
		msg.put("data",result);
		return "json";
	}
	
	
	@Action(value = "getLog")
	public String getLog() {
		HttpServletRequest request =ServletActionContext.getRequest();
		String filepath = request.getRealPath("/logfile"+File.separator+filename);
			File logfile = new File(filepath);
			try {
				realtimeShowLog(logfile);
				Thread.sleep(5000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println("action lastTimeFileSize" +lastTimeFileSize);
		msg = new HashMap<String, Object>();
		msg.put("flag", "success");
		msg.put("data",result);
		msg.put("lastTimeFileSize",lastTimeFileSize);
		msg.put("stop", stop);
		return "json";
	}
	
	
	
   
	
	public String getParms() {
		return parms;
	}

	public void setParms(String parms) {
		this.parms = parms;
	}

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public long getLastTimeFileSize() {
		return lastTimeFileSize;
	}


	public void setLastTimeFileSize(long lastTimeFileSize) {
		this.lastTimeFileSize = lastTimeFileSize;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public String getStop() {
		return stop;
	}


	public void setStop(String stop) {
		this.stop = stop;
	}
	
	
	
	
}