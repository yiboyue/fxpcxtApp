package com.fxpcxt.service;

import com.fxpcxt.entity.Hazard;

public interface HazardService {
	public Hazard getIdByName(String name);
	public void saveHazard(Hazard hazard);
	public Hazard getHazardById(Long id);
}
