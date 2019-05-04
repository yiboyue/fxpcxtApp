package com.fxpcxt.entity;
/**
 * 排查员信息表
 * @author 1917满眼绿意
 *
 */
public class Checker {
	private Long id;
	private Long thirdPartyId;
	private String account;
	private String password;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getThirdPartyId() {
		return thirdPartyId;
	}
	public void setThirdPartyId(Long thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
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
	private String name;
	
}
