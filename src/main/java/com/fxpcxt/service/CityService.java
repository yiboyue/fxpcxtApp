package com.fxpcxt.service;

import java.util.List;

import com.fxpcxt.entity.City;

public interface CityService {
	public List<City> selectCityByProvinceId(Long provinceId);
}
