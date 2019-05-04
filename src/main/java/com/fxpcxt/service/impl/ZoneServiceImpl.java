package com.fxpcxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.dao.ZoneMapper;
import com.fxpcxt.entity.Zone;
import com.fxpcxt.service.ZoneService;

@Service("ZoneService")
public class ZoneServiceImpl implements ZoneService {
	private ZoneMapper zoneMapper;
	public Zone getZone(String name) {
		zoneMapper.getZone(name);
		return null;
	}
	@Autowired
	public void setZoneMapper(ZoneMapper zoneMapper) {
		this.zoneMapper = zoneMapper;
	}
	public void saveZone(Zone zone) {
		zoneMapper.saveZone(zone);
		
	}
	@Override
	public Zone getZoneById(Long id) {
		
		return zoneMapper.getZoneById(id);
	}

}
