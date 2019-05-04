package com.fxpcxt.utils;

import java.util.Optional;

import org.springframework.http.HttpHeaders;

import com.fxpcxt.common.CommonResponse;

public class ResponseUtil {

	private ResponseUtil() {

	}

	public static CommonResponse success() {
		return success(null);
	}

	public static CommonResponse error(Exception e){
		return CommonResponse.error(e.getMessage());
	}

	public static <T> CommonResponse success(T data) {
		return CommonResponse.success(data);
	}

	private static <T> CommonResponse wrapResponse(int statusCode, T data, String msg) {
		return new CommonResponse(data, statusCode, msg);
	}

	public static <T> CommonResponse wrapOrNotFound(Optional<T> maybeResponse) {
		return wrapResponse(maybeResponse);
	}

	private static <T> CommonResponse wrapResponse(Optional<T> maybeResponse){
		return maybeResponse.map((response) -> {
			CommonResponse commonResponse = CommonResponse.success(response);
			return commonResponse;
		}).get();
	}

	private static <T> CommonResponse wrapResponse(Optional<T> maybeResponse, HttpHeaders headers, int statusCode, String msg) {
		return maybeResponse.map((response) -> {
			CommonResponse commonResponse = new CommonResponse<>(response, statusCode, msg);
			return commonResponse;
		}).get();
	}
}