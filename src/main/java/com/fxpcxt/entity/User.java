package com.fxpcxt.entity;

public class User {
	private Long id;
	private String account;
	private String password;
	private String name;
	private Long roleId;
	private String roleName;
	private Long unitId;
	private String unitName;
	private String phone;
	private Boolean status;
	private Long orgnazationId;
	private String orgnazationName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Long getOrgnazationId() {
		return orgnazationId;
	}
	public void setOrgnazationId(Long orgnazationId) {
		this.orgnazationId = orgnazationId;
	}
	public String getOrgnazationName() {
		return orgnazationName;
	}
	public void setOrgnazationName(String orgnazationName) {
		this.orgnazationName = orgnazationName;
	}
	
}
