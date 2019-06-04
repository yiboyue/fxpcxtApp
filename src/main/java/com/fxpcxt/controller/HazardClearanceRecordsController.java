package com.fxpcxt.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fxpcxt.common.CommonResponse;
import com.fxpcxt.entity.HazardClearRecords;
import com.fxpcxt.service.HazardClearRecordsService;

@RestController
@RequestMapping(value = "/hazardclearancerecords",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HazardClearanceRecordsController {
	@Autowired
	private HazardClearRecordsService hazardClearRecordsService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Boolean saveHazardClearanceRecords(@RequestBody HazardClearRecords hazardClearRecords){
		hazardClearRecordsService.saveHazardClearRecords(hazardClearRecords);
		return true;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Boolean deletehazardClearanceRecords(@RequestParam Long id){
		return hazardClearRecordsService.deleteHazardClearRecordsById(id);
				
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Boolean updateHazardClearanceRecords(@RequestBody HazardClearRecords hazardClearRecords){
		hazardClearRecordsService.updateHazardClearRecords(hazardClearRecords);
		//updateHazardClearanceRecords(hazardClearanceRecords);
		return true;
	}
	@RequestMapping(value = "/downloadcheckImage", method = RequestMethod.GET)
	public CommonResponse<String> downloadHazardImage(@RequestParam Long id){
		HazardClearRecords hazardClearRecords=hazardClearRecordsService.getHazardClearRecordsById(id);
				//getHazardClearanceRecordsById(id);
		return CommonResponse.success(hazardClearRecords.getCheckImg());
		/*HazardClearRecords hazardClearRecords=hazardClearRecordsService.getHazardClearRecordsById(id);
		
		CommonResponse commonResponse=new CommonResponse();
		commonResponse.setData(hazardClearRecords.getCheckImg());*/
		
	}
	@RequestMapping(value = "/downloadChangeImage", method = RequestMethod.GET)
	public CommonResponse<String> downloadCheckImage(@RequestParam Long id){
		HazardClearRecords hazardClearRecords=hazardClearRecordsService.getHazardClearRecordsById(id);
		return CommonResponse.success(hazardClearRecords.getChangeImg());
	}
	@RequestMapping(value = "/selectRecordsByEnterpriseId", method = RequestMethod.POST)
	public List<HazardClearRecords> getHazardClearanceRecordsById(@RequestParam(required=false) String enterpriseName,@RequestParam(required=false) String hazardType){
		List<HazardClearRecords> hazardClearRecordsList=new ArrayList<HazardClearRecords>();
		if (enterpriseName !=null || hazardType !=null) {
			
			hazardClearRecordsList=hazardClearRecordsService.getEnterPriseHazardClearRecords(enterpriseName,hazardType);
		}
		return hazardClearRecordsList;
	}
	@RequestMapping(value = "/selectAllRecords", method = RequestMethod.GET)
	public List<HazardClearRecords> getAllRecords(){
		List<HazardClearRecords> hazardClearRecordsList = hazardClearRecordsService.getHazardClearRecordsAll();
		return hazardClearRecordsList;
	}
	
	@RequestMapping(value = "hazardclearancerecords/getInfoByType",method = RequestMethod.POST)
	public List<HazardClearRecords> getChangeRecordsByType(@RequestParam String hazardType){
		List<HazardClearRecords> list = hazardClearRecordsService.getChangeRecordsByType(hazardType);
		return list;
	}
	
	@RequestMapping(value = "hazardclearancerecords/Recheck",method = RequestMethod.POST)
	public List<HazardClearRecords> hazardRecordsRecheck(@RequestParam String hazardType){
		List<HazardClearRecords> list = hazardClearRecordsService.hazardRecordsRecheck(hazardType);
		return list;
	}
	
	
}
