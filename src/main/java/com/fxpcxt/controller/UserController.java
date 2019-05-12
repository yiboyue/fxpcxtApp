package com.fxpcxt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fxpcxt.context.LoginContext;
import com.fxpcxt.entity.User;
import com.fxpcxt.jwt.utils.JwtTokenUtil;
import com.fxpcxt.jwt.utils.NoToken;
import com.fxpcxt.service.UserService;

@RestController
@RequestMapping(value = "/userInfo",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
	@Autowired
	private UserService userService;
	
	@NoToken
	@RequestMapping(value ="/login")
	public LoginContext login(@RequestBody User user){
		Assert.notNull(user, "登陆异常");
		Assert.notNull(user.getAccount(), "账号不能为空");
		Assert.notNull(user.getPassword(), "密码不能为空");
		boolean success = userService.login(user);
		Assert.isTrue(success, "账号或密码不存在");
		LoginContext loginContext = new LoginContext();
		loginContext.setToken(JwtTokenUtil.getInstance().generateToken(user));
		return loginContext;
	}
	
	@NoToken
	@RequestMapping(value="/save")
	public Boolean saveUser(@RequestBody User user){
		return userService.saveUser(user);
		
		
	}
	@RequestMapping(value="/delete")
	public Boolean deleteUserInfo(@RequestParam Long id){
		userService.deleteUserById(id);
		return true;
	}
	@RequestMapping(value="/update")
	public Boolean updateUserInfo(@RequestBody User user){
		
		return userService.updateUser(user);
	}
	@RequestMapping(value="/selectAll")
	public List<User> getAllUserInfo(){
		List<User> userList=userService.getAllUser();
		return userList;
	}
}

