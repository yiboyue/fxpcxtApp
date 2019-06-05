package com.fxpcxt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fxpcxt.entity.Enterprise;
import com.fxpcxt.entity.Industry;
import com.fxpcxt.entity.IndustryAndHazardType;
import com.fxpcxt.service.EnterpriseService;
import com.fxpcxt.service.IndustryAndHazardTypeService;
import com.fxpcxt.service.IndustryService;
@RestController
@RequestMapping(value = {"/IndustryAndHazardType"},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class IndustryAndHazardTypeController {
	@Autowired
	private IndustryAndHazardTypeService industryAndHazardTypeService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private IndustryService industryService;
	@RequestMapping(value = "/selectAllByIndustryName",method = RequestMethod.POST)
	public List<IndustryAndHazardType> selectAllByIndustryName(@RequestParam(required=false) String enterpriseName){
		Enterprise enterprise = enterpriseService.getEnterpriseIdByName(enterpriseName);
		Industry industry = industryService.getIndustryById(enterprise.getIndustryId());
		List<IndustryAndHazardType> list = industryAndHazardTypeService.selectAllByIndustryName(industry.getName());
		return list;
	}
}
