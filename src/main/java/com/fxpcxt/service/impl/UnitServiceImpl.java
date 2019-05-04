package com.fxpcxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.dao.UnitMapper;
import com.fxpcxt.entity.Unit;
import com.fxpcxt.service.UnitService;

@Service("UnitService")
public class UnitServiceImpl implements UnitService {
	@Autowired
	private UnitMapper unitMapper;
	@Override
	public Unit getIdByName(String name) {
		
		return unitMapper.getIdByName(name);
	}
	@Override
	public void saveUnit(Unit unit) {
		unitMapper.saveUnit(unit);
		
	}
	@Override
	public Unit getUnitById(Long id) {
		Unit unit=unitMapper.getUnitById(id);
		return unit;
	}

}
