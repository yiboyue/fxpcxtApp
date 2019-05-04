package com.fxpcxt.dao;

import java.util.List;

import com.fxpcxt.entity.City;

public interface CityMapper {
	public List<City> selectCityByProvinceId(Long provinceId);
}
