package com.fxpcxt.dao;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.fxpcxt.entity.HazardClearRecords;

@Repository
public interface HazardClearRecordsMapper {
	public void saveHazardClearRecords(HazardClearRecords hazardClearRecords);
	public void deleteHazardClearRecordsById(Long id);
	public void updateHazardClearRecords(HazardClearRecords hazardClearRecords);
	public List<HazardClearRecords> getHazardClearRecordsAll();
	public List<HazardClearRecords> getEnterPriseHazardClearRecords(Long enterpriseId,String hazardType);
	public HazardClearRecords getHazardClearRecordsById(Long id);
	
	public List<HazardClearRecords> getChangeRecordsByType(String hazardType);
	public List<HazardClearRecords> hazardRecordsRecheck(String hazardType);
}
