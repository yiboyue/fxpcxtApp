package com.fxpcxt.dao;

import java.util.List;

import com.fxpcxt.entity.RoleAndFunction;


public interface RoleAndFunctionMapper {
	public List<RoleAndFunction> selectRoleFunction(Long roleId);
	public void deleteById(Long roleId);
	public void saveRoleAndFunction(RoleAndFunction roleAndFunction);
}
