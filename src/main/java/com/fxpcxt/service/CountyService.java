package com.fxpcxt.service;

import java.util.List;

import com.fxpcxt.entity.County;

public interface CountyService {
	public List<County> selectCountyByCityId(Long cityId);
}
