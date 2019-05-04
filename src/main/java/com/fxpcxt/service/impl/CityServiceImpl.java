package com.fxpcxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fxpcxt.dao.CityMapper;
import com.fxpcxt.dao.CountyMapper;
import com.fxpcxt.entity.City;
import com.fxpcxt.entity.County;
import com.fxpcxt.service.CityService;

public class CityServiceImpl implements CityService {
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private CountyMapper countyMapper;
	@Override
	public List<City> selectCityByProvinceId(Long provinceId) {
		List<City> list=cityMapper.selectCityByProvinceId(provinceId);
		City city=new City();
		//County county=new County();
		for(int i=0;i<list.size();i++){
			city=list.get(i);
			city.setCountys(countyMapper.selectCountyByCityId(city.getId()));
		}
		return list;
	}

}
