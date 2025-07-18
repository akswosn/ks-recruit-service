package com.forlks.ksrecruitservice.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean

@Configuration
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
     * Creates and returns the primary DataSourceProperties bean configured with properties
     * prefixed by "spring.datasource.recruit-db".
     *
     * @return The DataSourceProperties for the recruit database.
     */
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.recruit-db")
    fun dataSourceProperties() = DataSourceProperties()

    /**
         * Creates and configures the primary HikariCP DataSource bean using properties from the application's datasource configuration.
         *
         * @return The initialized HikariCP DataSource.
         */
        @Primary
    @Bean
    fun dataSource() = dataSourceProperties().initializeDataSourceBuilder()
        .type(com.zaxxer.hikari.HikariDataSource::class.java).build()

    /**
     * Creates and configures the primary JPA entity manager factory bean for the application.
     *
     * Sets up the entity manager factory with the configured data source, scans the specified package for JPA entities,
     * assigns a persistence unit name, and applies Hibernate properties derived from JPA and Hibernate settings.
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
     * Creates and configures the primary JPA transaction manager using the provided entity manager factory.
     *
     * @param entityManagerFactory The entity manager factory to associate with the transaction manager.
     * @return The configured `JpaTransactionManager` bean.
     */
    @Primary
    @Bean
    fun transactionManager(
        entityManagerFactory : EntityManagerFactory
    ): JpaTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory
        return transactionManager
    }
}