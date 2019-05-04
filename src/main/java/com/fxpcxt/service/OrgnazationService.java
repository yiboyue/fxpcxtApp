package com.fxpcxt.service;

import com.fxpcxt.entity.Orgnazation;

public interface OrgnazationService {
	public Orgnazation getIdByName(String name);
	public void saveOrgnazation(Orgnazation orgnazation);
	public Orgnazation getOrgnazationById(Long id);
}
