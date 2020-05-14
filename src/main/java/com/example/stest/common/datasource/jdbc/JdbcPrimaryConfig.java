//package com.example.stest.common.datasource.jdbc;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
///**
// * JdbcPrimaryConfig
// * @date 2018.11.8
// * @author ccx
// */
//@Configuration
//public class JdbcPrimaryConfig {
//
//	@Bean(name = "primaryJdbcTemplate")
//	public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryJdbcDataSource") DataSource dataSource) {
//	    return new JdbcTemplate(dataSource);
//	}
//}
