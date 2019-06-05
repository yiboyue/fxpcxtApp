package com.fxpcxt.service;

import java.util.List;

import com.fxpcxt.entity.IndustryAndHazardType;

public interface IndustryAndHazardTypeService {
	public void saveAll(List<IndustryAndHazardType> industryAndHazardTypes);
	
	public List<IndustryAndHazardType> selectAllByIndustryName(String industryName);
}
