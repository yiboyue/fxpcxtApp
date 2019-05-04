package com.fxpcxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.dao.TownMapper;
import com.fxpcxt.entity.Town;
import com.fxpcxt.service.TownService;

@Service("TownService")
public class TownServiceImpl implements TownService {
	@Autowired
	private TownMapper townMapper;
	public Town getTownByName(String tName) {
		Town town = townMapper.getTownByName(tName);
		return town;
	}
}
