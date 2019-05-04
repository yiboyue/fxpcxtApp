package com.fxpcxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.dao.CityMapper;
import com.fxpcxt.dao.ProvinceMapper;
import com.fxpcxt.entity.Province;
import com.fxpcxt.service.ProvinceService;
@Service("ProvinceService")
public class ProvinceServiceImpl implements ProvinceService {
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private CityMapper cityMapper;
	@Override
	public Province selectIdByName(String name) {
		Province province=provinceMapper.selectIdByName(name);
		
//		province.setCitys(province.getId());
		return null;
	}

}
