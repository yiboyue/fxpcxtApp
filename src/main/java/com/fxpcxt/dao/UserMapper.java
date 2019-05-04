package com.fxpcxt.dao;

import java.util.List;

import com.fxpcxt.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
	public User login(User user);
	public void saveUser(User user);
	public void deleteUserById(Long id);
	public void updateUser(User user);
	public User getIdByName(String name);
	public User getUserById(Long id);
	public List<User> getAllUser();
}
