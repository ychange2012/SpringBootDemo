package com.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;

public interface TestService {
	
	public List<Map<String,Object>> queryById(long id);
	
	public List<Map<String,Object>> menuConf();

}
