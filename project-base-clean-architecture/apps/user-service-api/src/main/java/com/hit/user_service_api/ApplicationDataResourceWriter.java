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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan(
    basePackages = {"com.hit.user_service_api.infrastructure.repository.database.session.writer"},
    sqlSessionFactoryRef = "sqlSessionFactoryWriter")
public class ApplicationDataResourceWriter extends SpringBootServletInitializer {

  @Value("${datasource.driver-class-name}")
  private String datasourceDriverClassName;

  @Value("${datasource.writer.url}")
  private String datasourceUrlWriter;

  @Value("${datasource.writer.user}")
  private String datasourceUserNameWriter;

  @Value("${datasource.writer.pass}")
  private String datasourcePasswordWriter;

  @Primary
  @Autowired
  @Bean("driverManagerDataSourceWriter")
  public DriverManagerDataSource dataSourceWriter() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(this.datasourceDriverClassName);
    dataSource.setUrl(this.datasourceUrlWriter);
    dataSource.setUsername(this.datasourceUserNameWriter);
    dataSource.setPassword(this.datasourcePasswordWriter);
    return dataSource;
  }

  @Primary
  @Autowired
  @Bean("datasourceTransactionManagerWriter")
  public DataSourceTransactionManager transactionManagerWriter(
      @Qualifier("driverManagerDataSourceWriter") DataSource dataSource) {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(dataSource);
    return transactionManager;
  }

  @Primary
  @Autowired
  @Bean("sqlSessionFactoryWriter")
  public SqlSessionFactory sqlSessionFactoryWriter(
      @Qualifier("driverManagerDataSourceWriter") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    ResourcePatternResolver resolver =
        ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());

    bean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));

    bean.setMapperLocations(resolver.getResources("classpath:repository/writer/*.xml"));

    return bean.getObject();
  }
}
