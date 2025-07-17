package com.forlks.ksrecruitservice.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.sql.DataSource

@Configurable
@EnableJpaRepositories(
    basePackages = ["com.forlks.ksrecruitservice.database.repository"],
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager"
)
class DatabaseConfig (
    private val jpaProperties: JpaProperties,
    private val hibernateProperties: HibernateProperties
){


    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.recruit-db")
    fun dataSourceProperties() = DataSourceProperties()

    @Primary
    @Bean
    fun dataSource() = dataSourceProperties().initializeDataSourceBuilder()
        .type(com.zaxxer.hikari.HikariDataSource::class.java).build()

    @Primary
    @Bean
    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        val properties = hibernateProperties.determineHibernateProperties(
            jpaProperties.properties, HibernateSettings()
        )
        return builder.dataSource(dataSource())
            .packages("com.forlks.ksrecruitservice.database.entity")
            .persistenceUnit("entityManager")
            .properties(properties)
            .build()
    }

    @Primary
    @Bean
    fun transactionManager(
        entityManagerFactory : LocalContainerEntityManagerFactoryBean
    ): JpaTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory.`object`
        return transactionManager
    }
}