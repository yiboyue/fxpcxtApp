package com.fxpcxt.faceapi.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	
	 public static void save(byte[] bytes, String fileName, String path) throws IOException {
	        createDirectory(path);
	        FileOutputStream os = new FileOutputStream(path + fileName);
	        os.write(bytes);
	        os.close();
	 }

    private static boolean isExistPath(String path) {
        File file = new File(path);
        //判断文件目录的存在
        if (file.exists()) {
            return true;
        }else{
            return false;
        }
    }
    
    private static void createDirectory(String path){
        if(!isExistPath(path)){
            File file=new File(path);
            file.mkdirs();
        }
    }
    
    /**
	 * 获取文件扩展名
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}
	}
}
