package com.fxpcxt.entity;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class IndustryAndHazardType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long industyId;
	
	@Excel(name = "行业名称")
	private String industyName;
	
	@Excel(name = "隐患类别")
	private String hazardType;
	
	public String getHazardType() {
		return hazardType;
	}
	public void setHazardType(String hazardType) {
		this.hazardType = hazardType;
	}
	public Long getIndustyId() {
		return industyId;
	}
	public void setIndustyId(Long industyId) {
		this.industyId = industyId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIndustyName() {
		return industyName;
	}
	public void setIndustyName(String industyName) {
		this.industyName = industyName;
	}
}
