package com.fxpcxt.faceapi.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

public class ImageUtil {
	/**
	 * @Description: 根据图片地址转换为base64编码字符串
	 * @Author: 
	 * @CreateTime: 
	 * @return
	 */
	public static String getImageStr(String imgFile) {
	    InputStream inputStream = null;
	    byte[] data = null;
	    try {
	        inputStream = new FileInputStream(imgFile);
	        data = new byte[inputStream.available()];
	        inputStream.read(data);
	        inputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // 加密
	    return Base64.getEncoder().encodeToString(data);
	}
	
	public static String getImageStr(byte[] imgFile) {
	    // 加密
	    return Base64.getEncoder().encodeToString(imgFile);
	}
	
	/**
	 * @Description: 根据图片地址转换为base64编码字符串
	 * @Author: 
	 * @CreateTime: 
	 * @return
	 */
	public static byte[] getImageByte(String imgFile) {
	    InputStream inputStream = null;
	    byte[] data = null;
	    try {
	        inputStream = new FileInputStream(imgFile);
	        data = new byte[inputStream.available()];
	        inputStream.read(data);
	        inputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // 加密
	    return data;
	}
	
	/**
	 * @Description: 将base64编码字符串转换为图片
	 * @Author: 
	 * @CreateTime: 
	 * @param imgStr base64编码字符串
	 * @param path 图片路径-具体到文件
	 * @return
	 * @throws IOException 
	*/
	public static boolean generateImage(String imgStr, String path) throws IOException {
		if(imgStr == null)
			return false;
		Decoder decoder = Base64.getDecoder();
		OutputStream out = null;
		try {
			//解密
			byte[] b = decoder.decode(imgStr);
			//处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			return true;
		} catch (Exception e) {
			return false;
		}finally {
			if(out != null){
				out.close();
			}
		}
	}
}
