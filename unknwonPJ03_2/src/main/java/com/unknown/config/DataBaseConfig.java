package com.unknown.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class DataBaseConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "UnknownSqlSessionFactory")
    @Primary
    public SqlSessionFactory UnknownSqlSessionFactory(@Qualifier("primaryDataSource") DataSource primaryDataSource,
            ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);

        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setDataSource(primaryDataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:com/aplab/apsite/dbmst/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "UnknownSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate UnknownSqlSessionTemplate(SqlSessionFactory UnknownSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(UnknownSqlSessionFactory);
    }
}
