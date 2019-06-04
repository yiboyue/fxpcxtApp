package com.fxpcxt.faceapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 百度Api返回的响应信息
 * @author lujianfen
 *
 */
public class ApiResponse {
	private ApiResult result;
	
	@JsonProperty("log_id")
	private long logId;
	
	@JsonProperty("error_msg")
	private String errorMsg;
	
	private int cached;
	
	@JsonProperty("error_code")
	private int errorCode;
	
	@JsonProperty("timestamp")
	private long timeStamp;

	public ApiResult getResult() {
		return result;
	}

	public void setResult(ApiResult result) {
		this.result = result;
	}

	public long getLogId() {
		return logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getCached() {
		return cached;
	}

	public void setCached(int cached) {
		this.cached = cached;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
