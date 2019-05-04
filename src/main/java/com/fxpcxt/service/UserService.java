package com.fxpcxt.service;

import java.util.List;

import com.fxpcxt.entity.User;

public interface UserService {
	public Boolean login(User user);
	public Boolean saveUser(User user);
	public Boolean deleteUserById(Long id);
	public Boolean updateUser(User user);
	public List<User> getAllUser();
}
