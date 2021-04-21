package com.hsb.mvpmsshare.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*"})
public class ShareDatabaseConfig {

	@Autowired
	private PaginationInterceptor paginationInterceptor;
	
	public ShareDatabaseConfig() {
        super();
    }

	@Bean
	@ConfigurationProperties(prefix = "spring.share-datasource")
	public DataSource shareDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
    public PropertiesFactoryBean configProperties() throws Exception {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        propertiesFactoryBean.setLocations(resolver.getResources("classpath*:application.yml"));
        return propertiesFactoryBean;
    }
	
	@Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(shareDataSource());
        return dataSourceTransactionManager;
    }
	
	@Bean
	public MybatisSqlSessionFactoryBean sqlSessionFactory() throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
	    sqlSessionFactoryBean.setDataSource(shareDataSource());
	    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	    sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:com/gitee/sunchenbin/mybatis/actable/mapping/*/*.xml"));
	    sqlSessionFactoryBean.setTypeAliasesPackage("com.hsb.mvpmsshare.model.entity.*");
	    Interceptor[] plugins = { paginationInterceptor };
	    sqlSessionFactoryBean.setPlugins(plugins);
	    return sqlSessionFactoryBean;
	}

}
