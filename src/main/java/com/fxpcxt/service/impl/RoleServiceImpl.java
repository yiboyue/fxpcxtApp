package com.fxpcxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.dao.RoleAndFunctionMapper;
import com.fxpcxt.dao.RoleMapper;
import com.fxpcxt.entity.Role;
import com.fxpcxt.service.RoleService;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	private RoleAndFunctionMapper roleAndFunction;
	@Autowired
	private RoleAndFunctionMapper roleAndFunctionMapper;
	public Boolean deleteRoleById(Long id) {
		roleMapper.deleteRoleById(id);
		Role role=roleMapper.getRoleById(id);
		if(role!=null){
			return true;
		}else{
			return false;
		}
	}

	public void updateRole(Role role) {
		roleMapper.updateRole(role);
	}

	public List<Role> getAllRole() {
		/*List<Role> list=roleMapper.getAllRole();
		Role role=new Role();
		for(int i=0;i<list.size();i++){
			//role.setFunctions(roleAndFunctionMapper.selectRoleFunction(list.get(i).getId()));
		}*/
		
		return roleMapper.getAllRole();
		
	}
	public void saveRole(Role role) {

		roleMapper.saveRole(role);
	}
	public Role getRoleByName(String name) {
		return roleMapper.getRoleByName(name);
	}

	@Override
	public Role getRoleById(Long id) {
		
		return roleMapper.getRoleById(id);
		
	}

}
