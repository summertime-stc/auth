//package com.example.stest.common.datasource.jdbc;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * JdbcDataSourceConfig
// * @date 2018.11.8
// * @author ccx
// */
//@Configuration
//public class JdbcDataSourceConfig {
//	@Bean(name = "primaryJdbcDataSource")
//    @Qualifier("primaryJdbcDataSource")
//    @ConfigurationProperties(prefix="spring.datasource.primary")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "secondaryJdbcDataSource")
//    @Qualifier("secondaryJdbcDataSource")
//    @ConfigurationProperties(prefix="spring.datasource.secondary")
//    public DataSource secondaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//}
