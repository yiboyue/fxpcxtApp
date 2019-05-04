package com.fxpcxt.service;

import java.util.List;

import com.fxpcxt.entity.Enterprise;

public interface EnterpriseService {
	public void saveEnterprise(Enterprise enterprise);
	public Boolean deleteEnterpeiseById(Long id);
	public Enterprise getEnterpriseById(Long id);
	public void updateEnterprise(Enterprise enterPrise);
	public Enterprise getEnterpriseIdByName(String name);
	public List<Enterprise> getAllEnterpriseByZoneId(String zoneName);
	public List<Enterprise> selectAllEnterpriseInfo();
}
