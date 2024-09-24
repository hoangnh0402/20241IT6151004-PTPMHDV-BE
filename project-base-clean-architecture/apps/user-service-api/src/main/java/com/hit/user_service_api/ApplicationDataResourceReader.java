package com.hit.user_service_api;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {
    "com.hit.user_service_api.infrastructure.repository.database.session.reader"},
    sqlSessionFactoryRef = "sqlSessionFactoryReader")
public class ApplicationDataResourceReader extends SpringBootServletInitializer {

  @Value("${datasource.driver-class-name}")
  private String datasourceDriverClassName;

  @Value("${datasource.reader.url}")
  private String datasourceUrlReader;

  @Value("${datasource.reader.user}")
  private String datasourceUserNameReader;

  @Value("${datasource.reader.pass}")
  private String datasourcePasswordReader;

  @Autowired
  @Bean("driverManagerDataSourceReader")
  public DriverManagerDataSource dataSourceRead() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(this.datasourceDriverClassName);
    dataSource.setUrl(this.datasourceUrlReader);
    dataSource.setUsername(this.datasourceUserNameReader);
    dataSource.setPassword(this.datasourcePasswordReader);
    return dataSource;
  }

  @Autowired
  @Bean("datasourceTransactionManagerReader")
  public DataSourceTransactionManager transactionManagerReader(
      @Qualifier("driverManagerDataSourceReader") DataSource dataSource) {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(dataSource);
    return transactionManager;
  }

  @Autowired
  @Bean("sqlSessionFactoryReader")
  public SqlSessionFactory sqlSessionFactoryReader(
      @Qualifier("driverManagerDataSourceReader") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    ResourcePatternResolver resolver =
        ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());

    bean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));

    bean.setMapperLocations(resolver.getResources("classpath:repository/reader/*.xml"));

    return bean.getObject();
  }
}
