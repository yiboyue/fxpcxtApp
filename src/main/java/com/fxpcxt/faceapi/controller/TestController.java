package com.fxpcxt.faceapi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fxpcxt.common.CommonResponse;
import com.fxpcxt.context.AppContext;
import com.fxpcxt.faceapi.entity.ApiResponse;
import com.fxpcxt.faceapi.entity.FileMeta;
import com.fxpcxt.faceapi.utils.ApiFaceUtil;

@RestController
@RequestMapping(value = {"/face"})
public class TestController {
	@Value("${file.temp.path:D:/fxpcxtApp/temp/}")
	private String file_path;
	@RequestMapping(value =  "/addUser")
	public CommonResponse<String> addUser(@RequestParam("file") MultipartFile file,@RequestParam Long userId){
		 //form表单提交的参数测试为String类型
	    if (file == null) {
	    	return null;
	    }
	    FileMeta fileMeta = new FileMeta();
	    fileMeta.setFileName(file.getOriginalFilename());
	    fileMeta.setFileSize(file.getSize()/1024+" Kb");
        fileMeta.setFileType(file.getContentType());
        try {
			fileMeta.setBytes(file.getBytes());
		} catch (IOException e) {
			return null;
		}
        if(userId == null){
        	userId = AppContext.getBizContext().getUser().getId();
        }
        return CommonResponse.success(ApiFaceUtil.addUser(fileMeta.getBytes(), userId));
	}
	
	@RequestMapping(value =  "/getUserByFace")
	public ApiResponse getUserByFace(@RequestParam("file") MultipartFile file) throws JsonParseException, JsonMappingException, IOException{
		 //form表单提交的参数测试为String类型
	    if (file == null) {
	    	return null;
	    }
	    FileMeta fileMeta = new FileMeta();
	    fileMeta.setFileName(file.getOriginalFilename());
	    fileMeta.setFileSize(file.getSize()/1024+" Kb");
        fileMeta.setFileType(file.getContentType());
        try {
			fileMeta.setBytes(file.getBytes());
		} catch (IOException e) {
			return null;
		}
        ApiResponse apiResponse = ApiFaceUtil.getUserByFace(fileMeta.getBytes());
        if (!"SUCCESS".equals(apiResponse.getErrorMsg())) {
			throw new RuntimeException(apiResponse.getErrorMsg());
		}else if (apiResponse.getResult() == null || CollectionUtils.isEmpty(apiResponse.getResult().getUsers())){
			throw new RuntimeException("未找到用户信息");
		}else if(apiResponse.getResult().getUsers().get(0).getScore()<80){
			throw new RuntimeException("用户相似度较低");
		}
        return apiResponse;
	}
}
