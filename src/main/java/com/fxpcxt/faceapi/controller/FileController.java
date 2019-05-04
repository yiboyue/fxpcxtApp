package com.fxpcxt.faceapi.controller;

import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fxpcxt.faceapi.entity.FileMeta;
import com.fxpcxt.faceapi.utils.FileUtil;

@RestController
@RequestMapping(value = {"/file"})
public class FileController {
	@Value("${file.temp.path:D:/fxpcxtApp/temp/}")
	private String file_path;
	@RequestMapping(value =  "/upload")
	public FileMeta upload(@RequestParam("file") MultipartFile file) throws Exception {
	    //form表单提交的参数测试为String类型
	    if (file == null) {
	    	return null;
	    }
	    FileMeta fileMeta = new FileMeta();
	    fileMeta.setFileName(file.getOriginalFilename());
	    fileMeta.setFileSize(file.getSize()/1024+" Kb");
        fileMeta.setFileType(file.getContentType());
        fileMeta.setBytes(file.getBytes());
        fileMeta.setFullPath(file_path + file.getOriginalFilename());
        FileUtil.save(fileMeta.getBytes(), fileMeta.getFileName(), fileMeta.getFullPath());
        return fileMeta;
	}
}
