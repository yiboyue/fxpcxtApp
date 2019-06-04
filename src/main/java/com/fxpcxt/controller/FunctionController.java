package com.fxpcxt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fxpcxt.entity.Function;
import com.fxpcxt.service.FunctionService;

@RestController
@RequestMapping(value = "/function",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FunctionController {
	@Autowired
	private FunctionService functionService;
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public List<Function> selectAll(){
		return functionService.selectAll();
	}
}
