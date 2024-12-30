package com.spring.demo

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.bblam")
    fun bblamDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    @ConfigurationProperties("spring.datasource.scbam")
    fun scbam2DataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }
}