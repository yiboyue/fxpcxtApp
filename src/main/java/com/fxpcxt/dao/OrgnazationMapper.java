package com.fxpcxt.dao;

import com.fxpcxt.entity.Orgnazation;

public interface OrgnazationMapper {
	public Orgnazation getIdByName(String name);
	public void saveOrgnazation(Orgnazation orgnazation);
	public Orgnazation getOrgnazationById(Long id);
}
