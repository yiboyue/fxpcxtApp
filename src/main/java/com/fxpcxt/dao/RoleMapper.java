package com.fxpcxt.dao;

import java.util.List;

import com.fxpcxt.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
	public void saveRole(Role role);
	public void deleteRoleById(Long id);
	public void updateRole(Role role);
	public Role getRoleByName(String name);
	public List<Role> getAllRole();
	public Role getRoleById(Long id);
}
