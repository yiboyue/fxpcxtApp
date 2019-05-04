package com.fxpcxt.service;

import com.fxpcxt.entity.Industry;

public interface IndustryService {
	public Industry getIndustry(String name);
	public void saveIndustry(Industry industry);
	public Industry getIndustryById(Long id);
}
