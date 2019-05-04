package com.fxpcxt.dao;

import com.fxpcxt.entity.Unit;

public interface UnitMapper {
	public Unit getIdByName(String name);
	public void saveUnit(Unit unit);
	public Unit getUnitById(Long id);
}
