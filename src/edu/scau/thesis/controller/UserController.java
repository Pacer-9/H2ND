package edu.scau.thesis.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.scau.thesis.model.User;
import edu.scau.thesis.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("/register")
	public String register(User user){
		Map<String,Object> map = new HashMap<String,Object>();
		String userAccount = user.getUserAccount();
		user.setUserName(userAccount);
		user.setAvatar("avatar-01.jpg");
		user.setProfile(null);
		user.setSocialAccount(null);
		user.setRegister_date(new Date());
		user.setItems(null);
		userService.addUser(user);
		return "result/register-success";
	}
	@RequestMapping("/login")
	@ResponseBody
	public Map login(HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		User user = userService.getUserById("402881e759b70ac20159b70d0ab60001");
		session.setAttribute("currentUser", user);
		return map;
	}
	@RequestMapping("/isExisted")
	@ResponseBody
	public Map isUserExisted(String userAccount){
		Map<String,Object> map = new HashMap<String,Object>();
		Boolean isExist = userService.isExisted(userAccount);
		map.put("valid", isExist);
		return map;
	}

}
