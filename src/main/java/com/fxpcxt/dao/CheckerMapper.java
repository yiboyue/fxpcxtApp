package com.fxpcxt.dao;

import org.springframework.stereotype.Repository;

import com.fxpcxt.entity.Checker;

@Repository
public interface CheckerMapper {
	public Checker getIdByName(String name);
	public void saveChecker(Checker checker);
	public Checker getCheckerById(Long id);
}
