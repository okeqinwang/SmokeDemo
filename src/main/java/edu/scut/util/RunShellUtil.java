package edu.scut.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.struts2.dispatcher.SessionMap;

public class RunShellUtil {

	public static void main(String[] args) {
		RunShellUtil obj = new RunShellUtil();
//		String domainName = "baidu.com";
//		String command = "ping -c 5 " + domainName;
		List<String> cmds = new ArrayList<String>();
		cmds.add("sh");
		cmds.add("-c");
		cmds.add("ping -c 500 baidu.com  ");
		String []  cmd = cmds.toArray(new String [cmds.size()]);
		
		obj.executeCommand(cmd);
		
	}

	public String executeCommand(String [ ] command) {
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				System.out.println(line);
				output.append(line + "\n");
			}
			p.waitFor();
			p.destroy();
			System.err.println(p.waitFor());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
	
	public String executeCommand(String  command) {
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
//			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				System.out.println(line);
				output.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
	
	

}