package com.demo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/** 数据库连接池druid的配置 */  
@Configuration  
public class DruidTool {  
    public static Logger LOG = LoggerFactory.getLogger(DruidTool.class);  
    public Log4jFilter log4jFilter = new Log4jFilter();
    public StatFilter statFilter = new StatFilter();
    @Bean  
    public ServletRegistrationBean druidServlet(){  
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");  
    }  
    @Bean  
    public DataSource druidDataSource(  
            @Value("${spring.datasource.driverClassName}") String driverClass,  
            @Value("${spring.datasource.url}") String url,  
            @Value("${spring.datasource.username}") String username,  
            @Value("${spring.datasource.password}") String password){  
    	
        DruidDataSource ds = new DruidDataSource();  
        ds.setDriverClassName(driverClass);  
        ds.setUrl(url);  
        ds.setUsername(username);  
        ds.setPassword(password); 
        ds.setInitialSize(5);
        ds.setMinIdle(1);
        ds.setMaxActive(10); // 启用监控统计功能
        log4jFilter.setResultSetLogEnabled(false);
        statFilter.setSlowSqlMillis(0);
        statFilter.setLogSlowSql(true);
        List<Filter> list = new ArrayList<Filter>();
        list.add(log4jFilter);
        //list.add(statFilter);
        try {  
            ds.setFilters("stat,log4j"); 
            ds.setProxyFilters(list);
        } catch (SQLException e) {  
            e.printStackTrace();  
            LOG.error("druid err:{}", e);  
        }  
        return ds;  
    }  
    @Bean   
    public FilterRegistrationBean filterRegistrationBean(){  
        FilterRegistrationBean fb = new FilterRegistrationBean();  
        fb.setFilter(new WebStatFilter());  
        fb.addUrlPatterns("/*");  
        fb.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");  
        return fb;  
    }  
} 
