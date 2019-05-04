package com.fxpcxt.faceapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 文件元数据描述
 * @author 
 *
 */
public class FileMeta {
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String path;
	/**
	 * 文件全路径
	 */
	private String fullPath;
	/**
	 * 文件大小
	 */
    private String fileSize;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件二进制数据
     */
    @JsonIgnore
    private byte[] bytes;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
