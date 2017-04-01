package com.demo.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public interface CommonDao {
	public DataSource getDataSource();
	public JdbcTemplate getTemplate();
}
