package com.fxpcxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.fxpcxt.dao.HazardMapper;
import com.fxpcxt.entity.Hazard;
import com.fxpcxt.service.HazardService;

public class HazardServiceImpl implements HazardService {
	@Autowired
	private HazardMapper hazardMapper;

	@Override
	public void saveHazard(Hazard hazard) {
		// TODO Auto-generated method stub
		hazardMapper.saveHazard(hazard);
		
	}
	@Override
	public Hazard getIdByName(String name) {
		
		return hazardMapper.getIdByName(name);
	}
	@Override
	public Hazard getHazardById(Long id) {
		
		return hazardMapper.getHazardById(id);
	}

}
