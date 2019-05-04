package com.fxpcxt.service;

import java.util.List;

import com.fxpcxt.entity.Role;
import com.fxpcxt.entity.RoleAndFunction;


public interface RoleAndFunctionService {
	public Role selectRoleFunction(Long roleId);
	public void deleteById(Long roleId);
	public void saveRoleAndFunction(List<RoleAndFunction> list);
}
