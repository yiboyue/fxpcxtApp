package com.fxpcxt.dao;

import com.fxpcxt.entity.Town;
import org.springframework.stereotype.Repository;

@Repository
public interface TownMapper {
	public Town getTownByName(String tName);
}
