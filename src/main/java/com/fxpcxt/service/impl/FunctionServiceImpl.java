package com.fxpcxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.dao.FunctionMapper;
import com.fxpcxt.entity.Function;
import com.fxpcxt.service.FunctionService;

@Service("FunctionService")
public class FunctionServiceImpl implements FunctionService {
	@Autowired
	private FunctionMapper functionMapper;
	@Override
	public List<Function> selectAll() {
		
		return functionMapper.selectAll();
	}

}
