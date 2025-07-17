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


    /**
     * Creates a primary `DataSourceProperties` bean configured with properties prefixed by `spring.datasource.recruit-db`.
     *
     * @return The configured `DataSourceProperties` instance for the recruit database.
     */
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.recruit-db")
    fun dataSourceProperties() = DataSourceProperties()

    /**
         * Creates and configures the primary HikariCP DataSource using properties from the recruit-db configuration.
         *
         * @return The initialized HikariDataSource for database connections.
         */
        @Primary
    @Bean
    fun dataSource() = dataSourceProperties().initializeDataSourceBuilder()
        .type(com.zaxxer.hikari.HikariDataSource::class.java).build()

    /**
     * Creates and configures the primary JPA entity manager factory bean for the application's persistence layer.
     *
     * The factory is set up with the application's data source, scans the specified entity package, assigns a persistence unit name,
     * and applies Hibernate properties derived from the injected JPA and Hibernate configuration.
     *
     * @param builder The builder used to construct the entity manager factory.
     * @return The configured `LocalContainerEntityManagerFactoryBean`.
     */
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

    /**
     * Creates and configures a primary JPA transaction manager using the provided entity manager factory.
     *
     * @param entityManagerFactory The factory used to obtain entity managers for transaction management.
     * @return A configured {@link JpaTransactionManager} for managing JPA transactions.
     */
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