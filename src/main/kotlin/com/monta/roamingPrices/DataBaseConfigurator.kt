package com.monta.roamingPrices

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import jakarta.inject.Singleton
import org.flywaydb.core.Flyway

@Singleton
class DataBaseConfigurator(
    private val databaseConfiguration: DatabaseConfiguration
) {
    val defaultDataSource: HikariDataSource by lazy {
        println("$databaseConfiguration")
        HikariDataSource(
            HikariConfig().apply {
                jdbcUrl = databaseConfiguration.jdbcUrl
                username = databaseConfiguration.username
                password = databaseConfiguration.password
                if (!databaseConfiguration.driverClassName.isNullOrEmpty()) {
                    driverClassName = databaseConfiguration.driverClassName
                }
                driverClassName
                poolName = "HikariPool-1"
                minimumIdle = databaseConfiguration.minimumIdle ?: 10
                maximumPoolSize = databaseConfiguration.maximumPoolSize ?: 30
                connectionTestQuery = "SELECT 1"
                this.isAutoCommit = false
                this.addDataSourceProperty("rewriteBatchedStatements", "false")
            },
        )
    }

    fun run() {
        println("Configuring database with url: ${databaseConfiguration.jdbcUrl}")

    }

    fun startMigration() {
        println("Starting migration")
        try {

            Flyway.configure().locations(
                "classpath:db/migration"
            ).dataSource(
                defaultDataSource
            ).validateOnMigrate(true).load().migrate()
        } catch (e: Exception) {
            println("Migration failed")
            e.printStackTrace()
        }
    }
}