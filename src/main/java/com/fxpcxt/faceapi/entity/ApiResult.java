package com.fxpcxt.faceapi.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 百度APi返回的结果集
 * @author lujianfen
 *
 */
public class ApiResult {
	
	@JsonProperty("face_token")
	private String faceToken;
	
	@JsonProperty("user_list")
	private List<ApiUser> users;
	
	public String getFaceToken() {
		return faceToken;
	}

	public void setFaceToken(String faceToken) {
		this.faceToken = faceToken;
	}

	public List<ApiUser> getUsers() {
		return users;
	}

	public void setUsers(List<ApiUser> users) {
		this.users = users;
	}

}
