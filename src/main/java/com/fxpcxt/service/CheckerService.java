package com.fxpcxt.service;

import com.fxpcxt.entity.Checker;

public interface CheckerService {
	public Checker getIdByName(String name);
	public void saveChecker(Checker checker);
	public Checker getCheckerById(Long id);
}
