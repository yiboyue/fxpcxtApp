package com.fxpcxt.faceapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 百度APi返回的用户信息
 * @author lujianfen
 *
 */
public class ApiUser {
	
	private int score;
	
	@JsonProperty("group_id")
	private String groupId;
	
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("user_info")
	private String userInfo;
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
}
