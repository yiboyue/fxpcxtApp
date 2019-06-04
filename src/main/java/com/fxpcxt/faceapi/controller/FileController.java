package com.fxpcxt.faceapi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fxpcxt.common.CommonResponse;
import com.fxpcxt.faceapi.entity.FileMeta;
import com.fxpcxt.faceapi.utils.FileUtil;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import utils.FileKit;

@RestController
@RequestMapping(value = {"/file"})
public class FileController {
	@Value("${file.temp.path:D:/fxpcxtApp/temp/}")
	private String file_path;
	
	private static final String EXCELPATTERN = "^(XLSX|xlsx|csv|CSV)$";
	
	@RequestMapping(value =  "/upload")
	public CommonResponse<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
	    //form表单提交的参数测试为String类型
	    if (file == null) {
	    	return null;
	    }
	    FileMeta fileMeta = new FileMeta();
	    fileMeta.setFileName(file.getOriginalFilename());
	    fileMeta.setFileSize(file.getSize()/1024+" Kb");
        fileMeta.setFileType(file.getContentType());
        fileMeta.setBytes(file.getBytes());
        fileMeta.setFullPath(file_path + fileMeta.getFileName());
        FileUtil.save(fileMeta.getBytes(), fileMeta.getFileName(), file_path);
        return CommonResponse.success(fileMeta.getFileName());
	}
	
	@RequestMapping(value =  "/download")
	public void download(@RequestParam("url") String url,HttpServletRequest resquest,HttpServletResponse response) throws Exception {
	    //form表单提交的参数测试为String类型
	    if (url == null) {
	    	return ;
	    }
	    File file = new File(file_path+(url));
	    if(file == null ){
	    	return ;
	    }
	    FileInputStream inputStream = null;
	    try {
			inputStream = new FileInputStream(file);
			byte[] data = new byte[(int)file.length()];
			int length = inputStream.read(data);
			inputStream.close();
			response.setContentType("image/jpg;charset=utf-8");
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value =  "/import")
	public CommonResponse<String> importData(@RequestParam("file") MultipartFile file,@RequestParam("type") String type) throws Exception {
	    //form表单提交的参数测试为String类型
	    if (file == null) {
	    	return null;
	    }
	    FileMeta fileMeta = new FileMeta();
	    fileMeta.setFileName(file.getOriginalFilename());
	    fileMeta.setFileSize(file.getSize()/1024+" Kb");
        fileMeta.setFileType(file.getContentType());
        fileMeta.setBytes(file.getBytes());
        fileMeta.setFullPath(file_path + fileMeta.getFileName());
        FileUtil.save(fileMeta.getBytes(), fileMeta.getFileName(), file_path);
        File excelFile = new File(file_path + fileMeta.getFileName());
        if (excelFile == null || !excelFile.exists()) {
			return CommonResponse.error("系统忙, 请重新上传导入文件");
		}
        Pattern excelpattern = Pattern.compile(EXCELPATTERN);
        if (!excelpattern.matcher(FileUtil.getFileExtension(excelFile)).matches()) {
        	return CommonResponse.error("不支持该文件格式");
		}
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<E>  = ExcelImportUtil.importExcel(excelFile, pojoClass, params);
        return CommonResponse.success(fileMeta.getFileName());
	}
}
