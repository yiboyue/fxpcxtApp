package com.fxpcxt.service;

import com.fxpcxt.entity.Unit;

public interface UnitService {
	public Unit getIdByName(String name);
	public void saveUnit(Unit unit);
	public Unit getUnitById(Long id);
}
