package com.fxpcxt.faceapi.utils;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.util.Assert;

import com.baidu.aip.face.AipFace;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fxpcxt.faceapi.entity.ApiResponse;

public class ApiFaceUtil {
	//设置APPID/AK/SK
    public static final String APP_ID = "16164715";
    public static final String API_KEY = "rjVTLKbOFOqzpiuzNp7IQgMn";
    public static final String SECRET_KEY = "bUo8HsjmTWQ6PEF8Am4M6utIbpQblG48";
    public static final String IMAGETYPE = "BASE64";
    public static final String GROUP_ID = "fxpcxt";
    public static final String USER_TOP_NUM = "3";
    

    public static ApiResponse getUserByFace(String image) throws JsonParseException, JsonMappingException, IOException{
    	// 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_top_num", USER_TOP_NUM);
        
        // 参数为本地图片二进制数组
        String file = ImageUtil.getImageStr(image);
        JSONObject res = client.search(file, IMAGETYPE, GROUP_ID, options);
        ApiResponse response = (ApiResponse) JsonUtil.jsonToObj(res.toString(), ApiResponse.class);
       return response;
    }
    
    public static ApiResponse getUserByFace(byte[] image) throws JsonParseException, JsonMappingException, IOException{
    	// 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_top_num", USER_TOP_NUM);
        
        // 参数为本地图片二进制数组
        String file = ImageUtil.getImageStr(image);
        JSONObject res = client.search(file, IMAGETYPE, GROUP_ID, options);
        ApiResponse response = (ApiResponse) JsonUtil.jsonToObj(res.toString(), ApiResponse.class);
       return response;
    }
    
    public static String addUser(String image,Long userId){
    	Assert.notNull(userId, "上传人脸信息失败，用户Id不能为空");
    	// 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_top_num", USER_TOP_NUM);
        
        // 参数为本地图片二进制数组
        String file = ImageUtil.getImageStr(image);
        JSONObject res = client.addUser(file, IMAGETYPE, GROUP_ID, userId.toString(), options) ;
        return res.toString();
    }
    
    public static String addUser(byte[] image,Long userId){
    	Assert.notNull(userId, "上传人脸信息失败，用户Id不能为空");
    	// 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_top_num", USER_TOP_NUM);
        
        // 参数为本地图片二进制数组
        String file = ImageUtil.getImageStr(image);
        JSONObject res = client.addUser(file, IMAGETYPE, GROUP_ID, userId.toString(), options) ;
        return res.toString();
    }
}
