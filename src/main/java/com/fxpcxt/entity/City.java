package com.fxpcxt.entity;

import java.util.List;

/**
 * 市
 * @author 1917满眼绿意
 *
 */
public class City {
	private Long id;
	private String name;
	private Long provinceId;
	private List<County> countys;
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
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	public List<County> getCountys() {
		return countys;
	}
	public void setCountys(List<County> countys) {
		this.countys = countys;
	}
	
}
