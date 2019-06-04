package com.fxpcxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.dao.EnterpriseMapper;
import com.fxpcxt.dao.IndustryMapper;
import com.fxpcxt.dao.ZoneMapper;
import com.fxpcxt.entity.Enterprise;
import com.fxpcxt.entity.Industry;
import com.fxpcxt.entity.Zone;
import com.fxpcxt.service.EnterpriseService;

@Service("EnterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService{
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private ZoneMapper zoneMapper;
	@Autowired
	private IndustryMapper industryMapper;
	@Override
	public void saveEnterprise(Enterprise enterprise) {
		/*Zone zone=zoneMapper.getZone(enterprise.getZoneName());
		if(zone != null){
			enterprise.setZoneId(zone.getId());
		}else{
			zone = new Zone();
			zone.setName(enterprise.getZoneName());
			zoneMapper.saveZone(zone);
			enterprise.setZoneId(zone.getId());
		}*/
		Industry industry=industryMapper.getIndustry(enterprise.getIndustryName());
		if(industry != null){
			enterprise.setIndustryId(industry.getId());
		}else{
			industry=new Industry();
			industry.setName(enterprise.getIndustryName());
			industryMapper.saveIndustry(industry);
			enterprise.setIndustryId(industry.getId());
	
		}
		enterpriseMapper.saveEnterprise(enterprise);
		
	}
	@Override
	public Boolean deleteEnterpeiseById(Long id) {
		enterpriseMapper.deleteEnterpeiseById(id);
		if(enterpriseMapper.getEnterpriseById(id)==null){
			return true;
		}else{
			
			return false;
		}
		
	}
	@Override
	public void updateEnterprise(Enterprise enterprise) {
		/*Zone zone=zoneMapper.getZone(enterprise.getZoneName());
		if(zone != null){
			enterprise.setZoneId(zone.getId());
		}else{
			zone = new Zone();
			zone.setName(enterprise.getZoneName());
			zoneMapper.saveZone(zone);
			enterprise.setZoneId(zone.getId());
		}*/
		enterpriseMapper.updateEnterprise(enterprise);
	}
	@Override
	public Enterprise getEnterpriseIdByName(String name) {
		Enterprise enterprise=enterpriseMapper.getEnterpriseIdByName(name);
		return enterprise;
	}
	@Override
	public List<Enterprise> getAllEnterpriseByAddress(String address) {
		//Zone zone=zoneMapper.getZone(zoneName);
		List<Enterprise> list=enterpriseMapper.getAllEnterpriseByAddress(address);
		Enterprise enterprise=new Enterprise();
		Industry industry=new Industry();
		for(int i=0;i<list.size();i++){
			/*zone=zoneMapper.getZoneById(enterprise.getZoneId());
			System.out.println(enterprise.getZoneId());
			enterprise=list.get(i);
			enterprise.setZoneName(zoneName);
			System.out.println(enterprise.getZoneName());*/
			industry=industryMapper.getIndustryById(enterprise.getIndustryId());
			if(industry!=null){
				enterprise.setIndustryName(industry.getName());
			}
			
		}
		
		return list;
	}
	@Override
	public Enterprise getEnterpriseById(Long id) {
		return enterpriseMapper.getEnterpriseById(id);
		
	}
	@Override
	public List<Enterprise> selectAllEnterpriseInfo() {
		
		return enterpriseMapper.selectAllEnterpriseInfo();
	}

}
