package com.fxpcxt.dao;

import com.fxpcxt.entity.Zone;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneMapper {
	public Zone getZone(String name);
	
	public void saveZone(Zone zone);
	public Zone getZoneById(Long id);
}
