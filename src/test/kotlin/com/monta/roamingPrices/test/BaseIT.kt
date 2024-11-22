package com.monta.roamingPrices.test

import io.kotest.core.listeners.AfterSpecListener
import io.kotest.core.listeners.BeforeSpecListener
import io.kotest.core.spec.style.AnnotationSpec
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import org.flywaydb.core.Flyway
import org.testcontainers.containers.MySQLContainer
import io.kotest.core.spec.Spec

@MicronautTest
abstract class BaseIT : AnnotationSpec(), BeforeSpecListener, AfterSpecListener {

    companion object {
        val mysqlContainer: MySQLContainer<Nothing> = MySQLContainer<Nothing>("mysql:8.0").apply {
            withDatabaseName("test")
            withUsername("root")
            withPassword("root")
            portBindings = listOf("54541:3306")
            start()
        }

        init {
            println("MySQL container running at: ${mysqlContainer.jdbcUrl}")
        }
    }

    override suspend fun beforeSpec(spec: Spec) {
        println("Running Flyway migrations")
        Flyway.configure()
            .dataSource(mysqlContainer.jdbcUrl, mysqlContainer.username, mysqlContainer.password)
            .load()
            .migrate()
    }

    override suspend fun afterSpec(spec: Spec) {
        println("Stopping MySQL container")
        mysqlContainer.stop()
    }
}