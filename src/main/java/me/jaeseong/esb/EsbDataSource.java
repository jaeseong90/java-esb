package me.jaeseong.esb;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class EsbDataSource {

    private String prefix;
    private Environment env;

    public EsbDataSource(Environment env) {
        this.env = env;
        this.prefix = "TEST_DS"; //
    }

    @Bean("DATASOURCE_")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(getProperty("datasource.driver-class-name"));
        dataSourceBuilder.url(getProperty("datasource.jdbc-url"));
        dataSourceBuilder.username(getProperty("datasource.username"));
        dataSourceBuilder.password(getProperty("datasource.password"));

        HikariDataSource dataSource = (HikariDataSource) dataSourceBuilder.build();
        dataSource.setPoolName(getProperty("datasource.hikari.name"));
        dataSource.setMinimumIdle(Integer.valueOf(getProperty("datasource.hikari.minimum-idle").trim()));
        dataSource.setMaximumPoolSize(Integer.valueOf(getProperty("datasource.hikari.maximum-pool-size").trim()));

        return dataSource;
    }

    @Bean("DATASOURCE_TX_MANAGER")
    DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

    @Bean("DATASOURCE_TX_MANAGER_SQL_SESSION_FACTORY")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("DATASOURCE_") DataSource dataSource,
                                               ApplicationContext applicationContext) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:MyBatisConfig.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:mappers//*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean("SQL_SESSION")
    SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("DATASOURCE_TX_MANAGER_SQL_SESSION_FACTORY") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    protected String getProperty(String key) {
        String prefixKey = String.format("%s.%s", prefix, key);
        return env.getProperty(prefixKey);
    }


}
