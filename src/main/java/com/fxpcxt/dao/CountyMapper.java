package com.fxpcxt.dao;

import java.util.List;

import com.fxpcxt.entity.County;

public interface CountyMapper {
	public List<County> selectCountyByCityId(Long cityId);
}
