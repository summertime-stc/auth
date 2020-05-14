//package com.example.stest.common.datasource.mybatis;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * MybatisDatasourceConfig
// * @date 2018.11.8
// * @author ccx
// */
//@Configuration
//public class MybatisDatasourceConfig {
//
//	@Bean(name = "primaryMybatisDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.primary") // application.properteis中对应属性的前缀
//    public DataSource dataSource1() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "secondaryMybatisDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.secondary") // application.properteis中对应属性的前缀
//    public DataSource dataSource2() {
//        return DataSourceBuilder.create().build();
//    }
//}
