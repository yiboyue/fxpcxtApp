package com.fxpcxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.dao.RoleAndFunctionMapper;
import com.fxpcxt.entity.Function;
import com.fxpcxt.entity.Role;
import com.fxpcxt.entity.RoleAndFunction;
import com.fxpcxt.service.RoleAndFunctionService;

@Service("RoleAndFunctionService")
public class RoleAndFunctionServiceImpl implements RoleAndFunctionService {
	@Autowired
	private RoleAndFunctionMapper roleAndFunctionMapper;
	@Override
	public Role selectRoleFunction(Long roleId) {
		List<RoleAndFunction> list= roleAndFunctionMapper.selectRoleFunction(roleId);
		Role role=new Role();
		List<Function> functions=new ArrayList<>();
		Function function=new Function();
		for(int i=0;i<list.size();i++){
			function.setId(list.get(i).getFunctionId());
			function.setName(list.get(i).getFunctionName());
			functions.add(function);
		}
		role.setFunctions(functions);
		return role;
	}

	@Override
	public void deleteById(Long roleId) {
		System.out.println(roleId);
		roleAndFunctionMapper.deleteById(roleId);
		System.out.println(roleId);
	}

	@Override
	public void saveRoleAndFunction(List<RoleAndFunction> list) {
		for(int i=0;i<list.size();i++)
		{	
			roleAndFunctionMapper.saveRoleAndFunction(list.get(i));
		}
	}


}