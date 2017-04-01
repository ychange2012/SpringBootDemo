package com.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.CommonDao;
import com.demo.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	CommonDao commonDao;

	@Override
	public List<Map<String, Object>> queryById(long id) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result = commonDao.getTemplate().queryForList("select id,name from tb_test");
		return result;
	}

	@Override
	public List<Map<String, Object>> menuConf() {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> pList = commonDao.getTemplate().queryForList("select id,link,icon,name,leaf from tb_menu where pid=-1");
		for(int i=0;i<pList.size();i++){
			Map<String,Object> temp = pList.get(i);
			int menuId = (Integer) temp.get("id");
			int leafFlag = (Integer) temp.get("leaf");
			List<Map<String,Object>> leafList = new ArrayList<Map<String,Object>>();
			if(leafFlag == 1){
				leafList = commonDao.getTemplate().queryForList("select id,link,icon,name,leaf from tb_menu where pid=?",menuId);
				temp.put("leafData", leafList);
			}
			result.add(temp);
		}
		return result;

	}

}
