package com.fxpcxt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fxpcxt.entity.Enterprise;

@Repository
public interface EnterpriseMapper {
	public void saveEnterprise(Enterprise enterprise);
	public void deleteEnterpeiseById(Long id);
	public Enterprise getEnterpriseById(Long id);
	public void updateEnterprise(Enterprise enterPrise);
	public Enterprise getEnterpriseIdByName(String name);
	public List<Enterprise> getAllEnterpriseByAddress(String address);
	public List<Enterprise> selectAllEnterpriseInfo();
}
