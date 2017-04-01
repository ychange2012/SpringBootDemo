package com.demo.dao.impl;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.dao.CommonDao;

@Repository("CommonDao")
public class CommonDaoImpl implements CommonDao {

	@Resource(name="druidDataSource")
	private DataSource dataSource;
	
	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}

	@Override
	public JdbcTemplate getTemplate() {
		return new JdbcTemplate(dataSource);
	}

}
