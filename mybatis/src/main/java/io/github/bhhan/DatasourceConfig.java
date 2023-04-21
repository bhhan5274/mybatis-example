package io.github.bhhan;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

public class DatasourceConfig {
    @Bean
    @Primary
    @Qualifier("jpaDataSource")
    @ConfigurationProperties(prefix = "spring.jpa-datasource")
    public DataSource jpaDataSource(){
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Qualifier("mybatisDataSource")
    @ConfigurationProperties(prefix = "spring.mybatis-datasource")
    public DataSource mybatisDataSource(){
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

}
