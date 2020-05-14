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
//
//import javax.sql.DataSource;
//
///**
// * MybatisSecondaryConfig
// * @date 2018.11.8
// * @author ccx
// */
//@Configuration
//@MapperScan(basePackages = {"com.wonder.hsbp.analysis.*.secRepository"}, sqlSessionFactoryRef = "sqlSessionFactory2")
//public class MybatisSecondaryConfig {
//	@Autowired
//    @Qualifier("secondaryMybatisDataSource")
//    private DataSource secondaryMybatisDataSource;
//    @Value(value = "${mybatis.mapper-locations}")
//    private String MAPPER_LOCATION;
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory2() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(secondaryMybatisDataSource);
//        factoryBean.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
//        return factoryBean.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
//        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory2());
//        return template;
//    }
//
//    @Bean(name = "mybatisTransactionSecondary")
//    public DataSourceTransactionManager secondaryMybatisTransaction() {
//        return new DataSourceTransactionManager(secondaryMybatisDataSource);
//    }
//}
