package com.fxpcxt.common;

import java.util.concurrent.ConcurrentHashMap;

public enum UserStatus {
	/**
	 * 在线
	 */
	ONLINE("在线",(short)1),
	/**
	 * 离线
	 */
	OFFLINE("离线",(short)2),
	/**
	 * 过期
	 */
	EXPIRE("过期",(short)3);

	private String name;
	private short value;

	UserStatus(String name, short value) {
			this.name = name;
			this.value = value;
		}

	public String getName() {
		return name;
	}

	public short getValue() {
		return value;
	}

	private static final ConcurrentHashMap<Short, UserStatus> USER_STATUS_MAP = new ConcurrentHashMap<>();

	private static void initMap() {
		if (USER_STATUS_MAP.isEmpty()){
			UserStatus[] items = UserStatus.values();
			for (UserStatus item : items) {
				USER_STATUS_MAP.put(item.getValue(), item);
			}
		}
	}

	public static UserStatus find(Short value) {
		if (value == null) {
			return null;
		}
		if (USER_STATUS_MAP.isEmpty()){
			initMap();
		}
		return USER_STATUS_MAP.get(value);
	}
}