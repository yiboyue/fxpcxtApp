package com.fxpcxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxpcxt.dao.CheckerMapper;
import com.fxpcxt.entity.Checker;
import com.fxpcxt.service.CheckerService;

@Service("CheckerService")
public class CheckerServiceImpl implements CheckerService {
	@Autowired
	private CheckerMapper checkerMapper;

	public Checker getIdByName(String name) {
		return checkerMapper.getIdByName(name);
	}

	@Override
	public void saveChecker(Checker checker) {
		checkerMapper.saveChecker(checker);
		
	}

	@Override
	public Checker getCheckerById(Long id) {
		
		return checkerMapper.getCheckerById(id);
	}

}
