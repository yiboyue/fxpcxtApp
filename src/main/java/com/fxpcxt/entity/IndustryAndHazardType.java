package com.fxpcxt.entity;

public class IndustryAndHazardType {
	private Long id;
	private Long industyId;
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
}
