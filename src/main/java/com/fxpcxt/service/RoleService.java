package com.fxpcxt.service;

import java.util.List;

import com.fxpcxt.entity.Role;

public interface RoleService {
	/*public Role saveRole(Role role);
	public int deleteRoleById(Long id);
	public int updateRole(Role role); 
	public Role getRoleByName(String name);
	public List<Role> getAllRole();*/
	public void saveRole(Role role);
	public Boolean deleteRoleById(Long id);
	public void updateRole(Role role);
	public Role getRoleByName(String name);
	public List<Role> getAllRole();
	public Role getRoleById(Long id);
}
