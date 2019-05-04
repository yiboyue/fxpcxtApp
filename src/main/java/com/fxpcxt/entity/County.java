package com.fxpcxt.entity;
/**
 * 县
 * @author 1917满眼绿意
 *
 */
public class County {
	private Long id;
	private String name;
	private Long cityId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
}
