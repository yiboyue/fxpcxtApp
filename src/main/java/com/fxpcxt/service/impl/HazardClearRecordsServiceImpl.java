package com.fxpcxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.context.AppContext;
import com.fxpcxt.context.CoreContext;
import com.fxpcxt.dao.CheckerMapper;
import com.fxpcxt.dao.EnterpriseMapper;
import com.fxpcxt.dao.HazardClearRecordsMapper;
import com.fxpcxt.dao.HazardMapper;
import com.fxpcxt.dao.UserMapper;
import com.fxpcxt.entity.Checker;
import com.fxpcxt.entity.Enterprise;
import com.fxpcxt.entity.Hazard;
import com.fxpcxt.entity.HazardClearRecords;
import com.fxpcxt.entity.User;
import com.fxpcxt.service.HazardClearRecordsService;

@Service("HazardClearRecordsService")
public class HazardClearRecordsServiceImpl implements HazardClearRecordsService {
	@Autowired
	private HazardClearRecordsMapper hazardClearRecordsMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CheckerMapper checkerMapper;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private HazardMapper hazardMapper; 
	@Override
	public void saveHazardClearRecords(HazardClearRecords hazardClearRecords) {
		//找到对应的id
		Hazard hazard=hazardMapper.getIdByName(hazardClearRecords.getHazardName());
		if(hazard !=null){
			hazardClearRecords.setHazardId(hazard.getId());
		}else{
			hazard=new Hazard();
			hazard.setName(hazardClearRecords.getHazardName());
			hazardMapper.saveHazard(hazard);
			hazardClearRecords.setHazardId(hazard.getId());
		}
		Checker checker=checkerMapper.getIdByName(hazardClearRecords.getCheckerName());
		if(checker!=null){
			hazardClearRecords.setCheckerId(checker.getId());
		}else{
			checker=new Checker();
			checker.setName(hazardClearRecords.getCheckerName());
			checkerMapper.saveChecker(checker);
			hazardClearRecords.setCheckerId(checker.getId());
		}
		Enterprise enterprise=enterpriseMapper.getEnterpriseIdByName(hazardClearRecords.getEnterpriseName());
		if(enterprise !=null){
			hazardClearRecords.setEnterpriseId(enterprise.getId());
		}else{
			enterprise=new Enterprise();
			enterprise.setName(hazardClearRecords.getEnterpriseName());
			enterpriseMapper.saveEnterprise(enterprise);
			hazardClearRecords.setEnterpriseId(enterprise.getId());
		}
		User user=userMapper.getIdByName(hazardClearRecords.getChangerName());
		if(user !=null){
			hazardClearRecords.setChangerId(user.getId());
		}else{
			user=new User();
			user.setName(hazardClearRecords.getChangerName());
			userMapper.getIdByName(user.getName());
			hazardClearRecords.setChangerId(user.getId());
		}
		
		hazardClearRecordsMapper.saveHazardClearRecords(hazardClearRecords);
	}

	@Override
	public Boolean deleteHazardClearRecordsById(Long id) {
		
		hazardClearRecordsMapper.deleteHazardClearRecordsById(id);
		if(hazardClearRecordsMapper.getHazardClearRecordsById(id)==null)
		{
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void updateHazardClearRecords(HazardClearRecords hazardClearRecords) {
		Hazard hazard=hazardMapper.getIdByName(hazardClearRecords.getHazardName());
		if(hazard !=null){
			hazardClearRecords.setHazardId(hazard.getId());
		}else{
			hazard=new Hazard();
			hazard.setName(hazardClearRecords.getHazardName());
			hazardMapper.saveHazard(hazard);
			hazardClearRecords.setHazardId(hazard.getId());
		}
		Checker checker=checkerMapper.getIdByName(hazardClearRecords.getCheckerName());
		if(checker!=null){
			hazardClearRecords.setCheckerId(checker.getId());
		}else{
			checker=new Checker();
			checker.setName(hazardClearRecords.getCheckerName());
			checkerMapper.saveChecker(checker);
			hazardClearRecords.setCheckerId(checker.getId());
		}
		Enterprise enterprise=enterpriseMapper.getEnterpriseIdByName(hazardClearRecords.getEnterpriseName());
		if(enterprise !=null){
			hazardClearRecords.setEnterpriseId(enterprise.getId());
		}else{
			enterprise=new Enterprise();
			enterprise.setName(hazardClearRecords.getEnterpriseName());
			enterpriseMapper.saveEnterprise(enterprise);
			hazardClearRecords.setEnterpriseId(enterprise.getId());
		}
		User user=userMapper.getIdByName(hazardClearRecords.getChangerName());
		if(user !=null){
			hazardClearRecords.setChangerId(user.getId());
		}else{
			user=new User();
			user.setName(hazardClearRecords.getChangerName());
			userMapper.getIdByName(user.getName());
			hazardClearRecords.setChangerId(user.getId());
		}
		hazardClearRecordsMapper.updateHazardClearRecords(hazardClearRecords);
	}
	/**
	 * 将用户自身所属企业的排查信息筛选出来
	 */
	@Override
	public List<HazardClearRecords> getHazardClearRecordsAll() {
		CoreContext coreContext=AppContext.getBizContext();
		Long enterpriseId=coreContext.getUser().getEnterpriseId();
		List<HazardClearRecords> list=hazardClearRecordsMapper.getHazardClearRecordsAll();
//		List<HazardClearRecords> list=hazardClearRecordsMapper.getEnterPriseHazardClearRecords(enterpriseId);
		HazardClearRecords hazardClearRecords=new HazardClearRecords();
		Hazard hazard=new Hazard();
		Enterprise enterprise=enterpriseMapper.getEnterpriseById(enterpriseId);
		Checker checker=new Checker();
		User user=new User();
		for(int i=0;i<list.size();i++){
			hazardClearRecords=list.get(i);
			hazardClearRecords.setEnterpriseId(enterpriseId);
			hazardClearRecords.setEnterpriseName(enterprise.getName());
			hazard=hazardMapper.getHazardById(hazardClearRecords.getHazardId());
			if(hazard!=null){
				hazardClearRecords.setHazardName(hazard.getName());
			}
			
			checker=checkerMapper.getCheckerById(hazardClearRecords.getCheckerId());
			if(checker!=null){
				hazardClearRecords.setCheckerName(checker.getName());
			}
			user=userMapper.getUserById(hazardClearRecords.getChangerId());
			if(user!=null){
				hazardClearRecords.setChangerName(user.getName());
			}
			
		}
		return list;
		/*
		 * CoreContext coreContext=AppContext.getBizContext();
		Long enterpriseId=coreContext.getUser().getEnterpriseId();
		List<HazardClearRecords> list=hazardClearRecordsMapper.getHazardClearRecordsAll();
		HazardClearRecords hazardClearRecords=new HazardClearRecords();
		Hazard hazard=new Hazard();
		Enterprise enterprise=new Enterprise();
		Checker checker=new Checker();
		User user=new User();
		for(int i=0;i<list.size();i++){
			hazardClearRecords=list.get(i);
			hazard=hazardMapper.getHazardById(hazardClearRecords.getHazardId());
			if(hazard!=null){
				hazardClearRecords.setHazardName(hazard.getName());
			}
			enterprise=enterpriseMapper.getEnterpriseById(hazardClearRecords.getEnterpriseId());
			if(enterprise!=null){
				hazardClearRecords.setEnterpriseName(enterprise.getName());
			}
			checker=checkerMapper.getCheckerById(hazardClearRecords.getCheckerId());
			if(checker!=null){
				hazardClearRecords.setCheckerName(checker.getName());
			}
			user=userMapper.getUserById(hazardClearRecords.getChangerId());
			if(user!=null){
				hazardClearRecords.setChangerName(user.getName());
			}
			
		}
		return list;*/
	}

	@Override
	public List<HazardClearRecords> getEnterPriseHazardClearRecords(String enterpriseName,String hazardType) {
		Enterprise enterprise=enterpriseMapper.getEnterpriseIdByName(enterpriseName);
		if(enterprise == null){
			return new ArrayList<>();
		}
		List<HazardClearRecords> list=hazardClearRecordsMapper.getEnterPriseHazardClearRecords(enterprise.getId(),hazardType);
		HazardClearRecords hazardClearRecords=new HazardClearRecords();
		Hazard hazard=new Hazard();
		Checker checker=new Checker();
		User user=new User();
		for(int i=0;i<list.size();i++){
			hazardClearRecords=list.get(i);
			hazard=hazardMapper.getHazardById(hazardClearRecords.getHazardId());
			if(hazard!=null){
				hazardClearRecords.setHazardName(hazard.getName());
			}
			hazardClearRecords.setEnterpriseName(enterpriseName);
			checker=checkerMapper.getCheckerById(hazardClearRecords.getCheckerId());
			if(checker!=null){
				hazardClearRecords.setCheckerName(checker.getName());
			}
			user=userMapper.getUserById(hazardClearRecords.getChangerId());
			if(user!=null){
				hazardClearRecords.setChangerName(user.getName());
			}
			
		}
		return list;
	}

	@Override
	public HazardClearRecords getHazardClearRecordsById(Long id) {
		return hazardClearRecordsMapper.getHazardClearRecordsById(id);
	}

	@Override
	public List<HazardClearRecords> getChangeRecordsByType(String enterpriseName,String hazardType) {
		// TODO Auto-generated method stub
		Enterprise enterprise=enterpriseMapper.getEnterpriseIdByName(enterpriseName);
		if(enterprise == null){
			return new ArrayList<>();
		}
		List<HazardClearRecords> list=hazardClearRecordsMapper.getChangeRecordsByType(enterprise.getId(),hazardType);
		HazardClearRecords hazardClearRecords=new HazardClearRecords();
		Hazard hazard=new Hazard();
		Checker checker=new Checker();
		User user=new User();
		for(int i=0;i<list.size();i++){
			hazardClearRecords=list.get(i);
			hazard=hazardMapper.getHazardById(hazardClearRecords.getHazardId());
			if(hazard!=null){
				hazardClearRecords.setHazardName(hazard.getName());
			}
			hazardClearRecords.setEnterpriseName(enterpriseName);
			checker=checkerMapper.getCheckerById(hazardClearRecords.getCheckerId());
			if(checker!=null){
				hazardClearRecords.setCheckerName(checker.getName());
			}
			user=userMapper.getUserById(hazardClearRecords.getChangerId());
			if(user!=null){
				hazardClearRecords.setChangerName(user.getName());
			}
			
		}
		return list;
		
		
		//return hazardClearRecordsMapper.getChangeRecordsByType(hazardType);
	}

	@Override
	public List<HazardClearRecords> hazardRecordsRecheck(String enterpriseName,String hazardType) {
		// TODO Auto-generated method stub
		Enterprise enterprise=enterpriseMapper.getEnterpriseIdByName(enterpriseName);
		if(enterprise == null){
			return new ArrayList<>();
		}
		List<HazardClearRecords> list=hazardClearRecordsMapper.hazardRecordsRecheck(enterprise.getId(),hazardType);
		HazardClearRecords hazardClearRecords=new HazardClearRecords();
		Hazard hazard=new Hazard();
		Checker checker=new Checker();
		User user=new User();
		for(int i=0;i<list.size();i++){
			hazardClearRecords=list.get(i);
			hazard=hazardMapper.getHazardById(hazardClearRecords.getHazardId());
			if(hazard!=null){
				hazardClearRecords.setHazardName(hazard.getName());
			}
			hazardClearRecords.setEnterpriseName(enterpriseName);
			checker=checkerMapper.getCheckerById(hazardClearRecords.getCheckerId());
			if(checker!=null){
				hazardClearRecords.setCheckerName(checker.getName());
			}
			user=userMapper.getUserById(hazardClearRecords.getChangerId());
			if(user!=null){
				hazardClearRecords.setChangerName(user.getName());
			}
			
		}
		return list;
		
		
		//return hazardClearRecordsMapper.hazardRecordsRecheck(hazardType);
	}
}
