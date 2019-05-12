package com.fxpcxt.faceapi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.UUID;

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

@RestController
@RequestMapping(value = {"/file"})
public class FileController {
	@Value("${file.temp.path:D:/fxpcxtApp/temp/}")
	private String file_path;
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
	    url = file_path+url.replace("\"", "");
	    File file = new File(url);
	    if(file == null && !file.exists()){
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
	
	private String getImageName(){
		UUID uid = UUID.randomUUID();
		return uid.toString();
	}
	
	public static String getEncoding(String str) 
		{    
	        String encode;
			
		encode = "UTF-16";   		
	        try 
		{    
	            if("IMG_20190414_123459R.jpg".equals(new String(str.getBytes(), encode))) 
	            {   
	                return encode;    
	            }    
	        } 
		catch(Exception ex) {} 
			
//		encode = "ASCII";    
//	        try 
//		{    
//	            if("IMG_20190414_123459R.jpg".equals(new String(str.getBytes(), encode)))
//		{    
//	                return "字符串<< " + str + " >>中仅由数字和英文字母组成，无法识别其编码格式";    
//	            }    
//	        } 
//		catch(Exception ex) {}    
			
		encode = "ISO-8859-1";    
	        try 
		{    
	            if("IMG_20190414_123459R.jpg".equals(new String(str.getBytes(), encode))) 
			{    
	                return encode;    
	            }    
	        } 
		catch(Exception ex) {}    
			
		encode = "GB2312";    
	        try 
		{    
	            if("IMG_20190414_123459R.jpg".equals(new String(str.getBytes(), encode))) 
			{    
	                return encode;    
	            }    
	        } 
		catch(Exception ex) {} 
	        
	    encode = "GBK";    
	        try 
		{    
	            if("IMG_20190414_123459R.jpg".equals(new String(str.getBytes(), encode))) 
			{    
	                return encode;    
	            }    
	        } 
		catch(Exception ex) {} 
			
		encode = "UTF-8";    
	        try 
		{    
	            if("IMG_20190414_123459R.jpg".equals(new String(str.getBytes(), encode))) 
	            {    
	                return encode;    
	            }    
	        } 
		catch(Exception ex) {}    
	        
	        /*
		 *......待完善
		 */
			
	        return "未识别编码格式";    
	     
	}
}
