package com.fxpcxt.dao;

import com.fxpcxt.entity.Industry;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryMapper {
	public Industry getIndustry(String name);
	public void saveIndustry(Industry industry);
	public Industry getIndustryById(Long id);
}
