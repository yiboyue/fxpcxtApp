package com.fxpcxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.dao.OrgnazationMapper;
import com.fxpcxt.dao.RoleMapper;
import com.fxpcxt.dao.UnitMapper;
import com.fxpcxt.dao.UserMapper;
import com.fxpcxt.entity.Orgnazation;
import com.fxpcxt.entity.Role;
import com.fxpcxt.entity.Unit;
import com.fxpcxt.entity.User;
import com.fxpcxt.service.UserService;


@Service("UserService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UnitMapper unitMapper;
	@Autowired
	private OrgnazationMapper orgnazationMapper;
	@Override
	public Boolean login(User user) {
		User userInfo=userMapper.login(user);
		if(userInfo.getPassword().equals(user.getPassword())){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Boolean saveUser(User user) {
		System.out.println("user------------------->"+user.getId()+user.getAccount());
		//查找roleId，unitID,orgnazationId
		Role role=roleMapper.getRoleByName(user.getRoleName());
		if(role !=null){
			user.setRoleId(role.getId());
		}else{
			role=new Role();
			role.setName(user.getRoleName());
			roleMapper.saveRole(role);
			user.setRoleId(role.getId());
		}
		Unit unit=unitMapper.getIdByName(user.getUnitName());
		if(unit !=null){
			user.setUnitId(unit.getId());	
		}else{
			unit=new Unit();
			unit.setName(user.getUnitName());
			unitMapper.saveUnit(unit);
			user.setUnitId(unit.getId());
		}
		Orgnazation orgnazation=orgnazationMapper.getIdByName(user.getOrgnazationName());
		if(orgnazation !=null){
			user.setOrgnazationId(orgnazation.getId());
		}else{
			orgnazation=new Orgnazation();
			orgnazation.setName(user.getOrgnazationName());
			orgnazationMapper.saveOrgnazation(orgnazation);
			user.setOrgnazationId(orgnazation.getId());
		}
		userMapper.saveUser(user);
		return true;
	}

	@Override
	public Boolean deleteUserById(Long id) {
		userMapper.deleteUserById(id);
		if(userMapper.getUserById(id)==null){
			return true;
		}else{
			return false;
		}	
	}

	@Override
	public Boolean updateUser(User user) {
		
		//查找roleId，unitID,orgnazationId
		Role role=roleMapper.getRoleByName(user.getRoleName());
		if(role !=null){
			user.setRoleId(role.getId());
		}else{
			role=new Role();
			role.setName(user.getRoleName());
			roleMapper.saveRole(role);
			user.setRoleId(role.getId());
		}
		Unit unit=unitMapper.getIdByName(user.getUnitName());
		if(unit !=null){
					user.setUnitId(unit.getId());	
		}else{
			unit=new Unit();
			unit.setName(user.getUnitName());
			unitMapper.saveUnit(unit);
			user.setUnitId(unit.getId());
		}
		Orgnazation orgnazation=orgnazationMapper.getIdByName(user.getOrgnazationName());
		if(orgnazation !=null){
			user.setOrgnazationId(orgnazation.getId());
		}else{
			orgnazation=new Orgnazation();
			orgnazation.setName(user.getOrgnazationName());
			orgnazationMapper.saveOrgnazation(orgnazation);
			user.setOrgnazationId(orgnazation.getId());
		}
		user.setOrgnazationId(orgnazation.getId());
		return true;
	}

	@Override
	public List<User> getAllUser() {
		List<User> list=new ArrayList<>();
		list=userMapper.getAllUser();
		User user=new User();
		Role role=new Role();
		Unit unit=new Unit();
		Orgnazation orgnazation=new Orgnazation();
		//有id,根据id补全name
		for(int i=0;i<list.size();i++){
			user=list.get(i);
			role=roleMapper.getRoleById(user.getRoleId());
			if(role!=null){
				user.setRoleName(role.getName());
			}
			unit=unitMapper.getUnitById(user.getUnitId());
			if(unit!=null){
				user.setUnitName(unit.getName());
			}
			orgnazation=orgnazationMapper.getOrgnazationById(user.getOrgnazationId());
			if(orgnazation!=null){
				user.setOrgnazationName(orgnazation.getName());
			}
			
		}
		return list;
	}

}
