package com.fxpcxt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fxpcxt.entity.User;
import com.fxpcxt.service.UserService;

@RestController
@RequestMapping(value = "/userInfo")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value ="/login")
	public Boolean login(@RequestBody User user){
		if(user.getAccount()==null||user.getPassword()==null){
			return false;
		}else{
			return userService.login(user);
		}
	}
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

