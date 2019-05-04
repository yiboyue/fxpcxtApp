package com.fxpcxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.dao.OrgnazationMapper;
import com.fxpcxt.entity.Orgnazation;
import com.fxpcxt.service.OrgnazationService;

@Service("OrgnazationService")
public class OranazationServiceImpl implements OrgnazationService {
	@Autowired
	private OrgnazationMapper orgnazationMapper;
	@Override
	public Orgnazation getIdByName(String name) {
		
		return orgnazationMapper.getIdByName(name);
	}
	@Override
	public void saveOrgnazation(Orgnazation orgnazation) {
		
		orgnazationMapper.saveOrgnazation(orgnazation);
	}
	@Override
	public Orgnazation getOrgnazationById(Long id) {
		
		return orgnazationMapper.getOrgnazationById(id);
	}

}
