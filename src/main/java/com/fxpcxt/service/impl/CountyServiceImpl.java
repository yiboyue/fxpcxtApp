package com.fxpcxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.dao.CountyMapper;
import com.fxpcxt.entity.County;
import com.fxpcxt.service.CountyService;
@Service("CountyService")
public class CountyServiceImpl implements CountyService {
	@Autowired
	private CountyMapper countyMapper;

	@Override
	public List<County> selectCountyByCityId(Long cityId) {
		
		return countyMapper.selectCountyByCityId(cityId);
	}

}
