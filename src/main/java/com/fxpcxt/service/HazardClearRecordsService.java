package com.fxpcxt.service;



import java.util.List;

import com.fxpcxt.entity.HazardClearRecords;

public interface HazardClearRecordsService {
	/*public void saveHazardClearanceRecords(HazardClearanceRecords hazardClearanceRecords);
	public void deleteHazardClearanceRecordsById(String id);
	public void updateHazardClearanceRecords(HazardClearanceRecords hazardClearanceRecordsId);
	public HazardClearanceRecords getHazardClearanceRecordsById(String id);
	public List<HazardClearanceRecords> getEnterPriseHazardClearanceRecords(String id);*/
	public void saveHazardClearRecords(HazardClearRecords hazardClearRecords);
	public Boolean deleteHazardClearRecordsById(Long id);
	public void updateHazardClearRecords(HazardClearRecords hazardClearRecords);
	public List<HazardClearRecords> getHazardClearRecordsAll();
	public List<HazardClearRecords> getEnterPriseHazardClearRecords(String enterpriseName);
	public HazardClearRecords getHazardClearRecordsById(Long id);
	
}
