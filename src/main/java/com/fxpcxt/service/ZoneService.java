package com.fxpcxt.service;

import com.fxpcxt.entity.Zone;

public interface ZoneService {
	public Zone getZone(String name);
	public void saveZone(Zone zone);
	public Zone getZoneById(Long id);
}
