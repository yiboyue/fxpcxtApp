package com.fxpcxt.dao;

import com.fxpcxt.entity.Hazard;

public interface HazardMapper {
	public Hazard getIdByName(String name);
	public void saveHazard(Hazard hazard);
	public Hazard  getHazardById(Long id);
}
