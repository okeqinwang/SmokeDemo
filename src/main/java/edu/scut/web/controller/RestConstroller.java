package edu.scut.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RestConstroller {
	public RestConstroller() {}
	@RequestMapping(value = "/login/{user}", method = RequestMethod.GET)
	public ModelAndView myMethod(HttpServletRequest request,HttpServletResponse response, 
			@PathVariable("user") String user, ModelMap modelMap) throws Exception {
		modelMap.put("loginUser", user);
		System.out.println("login page");
		return new ModelAndView("/hello", modelMap);
	}
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String registPost() {
		System.out.println("hello welcome");
		return "/welcome";
	}
}
