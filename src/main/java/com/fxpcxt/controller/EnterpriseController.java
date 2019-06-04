package com.fxpcxt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fxpcxt.entity.Enterprise;
import com.fxpcxt.service.EnterpriseService;

@RestController
@RequestMapping(value = {"/enterprise"},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EnterpriseController {
	@Autowired
	private EnterpriseService enterpriseService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Boolean saveEnterprise(@RequestBody Enterprise enterprise){
		enterpriseService.saveEnterprise(enterprise);
		return true;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Boolean deleteEnterprise(@RequestParam Long id){
		return enterpriseService.deleteEnterpeiseById(id);
	
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Boolean updateEnterprise(@RequestBody Enterprise enterprise){
		enterpriseService.updateEnterprise(enterprise);
		return true;
		
	}
	@RequestMapping(value = "/selectAll", method = RequestMethod.POST)
	public List<Enterprise> getAllEnterprise(@RequestParam String address){
		List<Enterprise> entepriseList = enterpriseService.getAllEnterpriseByAddress(address);
		return entepriseList;
	}
	@RequestMapping(value="/selectAllEnterprise",method=RequestMethod.GET)
	public List<Enterprise> selectAllEnterpriseInfo(){
		return enterpriseService.selectAllEnterpriseInfo();
	}
}
