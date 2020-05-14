//package com.example.stest.common.datasource.mybatis;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
///**
// * MybatisPrimaryConfig
// *
// * @author ccx
// * @date 2018.11.8
// */
//@Configuration
//@MapperScan(basePackages = {"com.wonder.hsbp.analysis.*.repository"}, sqlSessionFactoryRef = "sqlSessionFactory1")
//@EnableTransactionManagement
//public class MybatisPrimaryConfig {
//    @Autowired
//    @Qualifier("primaryMybatisDataSource")
//    private DataSource primaryMybatisDataSource;
//    @Value(value = "${mybatis.mapper-locations}")
//    private String MAPPER_LOCATION;
//    @Bean
//    public SqlSessionFactory sqlSessionFactory1() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(primaryMybatisDataSource);
//        factoryBean.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
//
//        return factoryBean.getObject();
//
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
//        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1()); // 使用上面配置的Factory
//        return template;
//    }
//
//    @Bean(name = "mybatisTransactionPrimary")
//    public PlatformTransactionManager primaryMybatisTransaction() {
//        return new DataSourceTransactionManager(primaryMybatisDataSource);
//    }
//
//}
