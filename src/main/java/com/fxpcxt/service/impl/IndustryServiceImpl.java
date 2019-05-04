package com.fxpcxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.dao.IndustryMapper;
import com.fxpcxt.entity.Industry;
import com.fxpcxt.service.IndustryService;

@Service("IndustryService")
public class IndustryServiceImpl implements IndustryService {
	@Autowired
	private IndustryMapper industryMapper;
	
	public Industry getIndustry(String name) {
		return industryMapper.getIndustry(name);
	}
	public void saveIndustry(Industry industry) {
		industryMapper.saveIndustry(industry);
	}
	@Override
	public Industry getIndustryById(Long id) {
		
		return industryMapper.getIndustryById(id);
	}

}
