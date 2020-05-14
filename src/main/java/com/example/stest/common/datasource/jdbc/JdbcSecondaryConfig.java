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
// * JdbcSecondaryConfig
// * @date 2018.11.8
// * @author ccx
// */
//@Configuration
//public class JdbcSecondaryConfig {
//
//	@Bean(name = "secondaryJdbcTemplate")
//	public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryJdbcDataSource") DataSource dataSource) {
//	    return new JdbcTemplate(dataSource);
//	}
//}
