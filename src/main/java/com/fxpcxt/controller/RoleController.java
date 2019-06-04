package com.fxpcxt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fxpcxt.entity.Role;
import com.fxpcxt.entity.RoleAndFunction;
import com.fxpcxt.service.RoleService;
import com.fxpcxt.service.RoleAndFunctionService;

@RestController
@RequestMapping(value = "/role",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleAndFunctionService roleAndFunctionservice;
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Boolean saveRole(@RequestBody Role role){
		//return roleService.saveRole(role);
		roleService.saveRole(role);
		//查询刚才插入的role的id
		Role temp=roleService.getRoleByName(role.getName());
		role.setId(temp.getId());
		List<RoleAndFunction> list=new ArrayList<>();
		RoleAndFunction roleAndFunction=new RoleAndFunction();
		for(int i=0;i<role.getFunctions().size();i++){
			roleAndFunction.setRoleId(role.getId());
			roleAndFunction.setFunctionId(role.getFunctions().get(i).getId());
			list.add(roleAndFunction);
		}
		roleAndFunctionservice.saveRoleAndFunction(list);
		return true;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Boolean deleteRoleById(@RequestParam Long id){
		roleService.deleteRoleById(id);
		roleAndFunctionservice.deleteById(id);
		return true;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Boolean update(@RequestBody Role role){
		roleService.updateRole(role);
		roleAndFunctionservice.deleteById(role.getId());
		List<RoleAndFunction> list=new ArrayList<>();
		RoleAndFunction roleAndFunction=new RoleAndFunction();
		for(int i=0;i<role.getFunctions().size();i++){
			roleAndFunction.setRoleId(role.getId());
			roleAndFunction.setFunctionId(role.getFunctions().get(i).getId());
			list.add(roleAndFunction);
		}
		roleAndFunctionservice.saveRoleAndFunction(list);
		return true;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Role> getAllRole(){
		//把每个role的List加上
		//return roleService.getAllRole();
		List<Role> list=roleService.getAllRole();
		Role role=new Role();
		for(int i=0;i<list.size();i++){
			role=roleAndFunctionservice.selectRoleFunction(list.get(i).getId());
			list.get(i).setFunctions(role.getFunctions());
		}
		return list;
	}
	
}
