package com.monta.roamingPrices.test

import io.kotest.core.listeners.AfterSpecListener
import io.kotest.core.listeners.BeforeSpecListener
import io.kotest.core.spec.style.AnnotationSpec
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import org.flywaydb.core.Flyway
import org.testcontainers.containers.MySQLContainer
import io.kotest.core.spec.Spec
import java.sql.Connection
import java.sql.DriverManager

@MicronautTest
abstract class BaseIT : AnnotationSpec(), BeforeSpecListener, AfterSpecListener {

    companion object {
        const val TEST_DATABASE_NAME = "test"
        const val TEST_DATABASE_USERNAME = "root"
        const val TEST_DATABASE_PASSWORD = "root"
        const val TEST_DATABASE_PORT = "54541"

        val mysqlContainer: MySQLContainer<Nothing> = MySQLContainer<Nothing>("mysql:8.0").apply {
            withDatabaseName(TEST_DATABASE_NAME)
            withUsername(TEST_DATABASE_USERNAME)
            withPassword(TEST_DATABASE_PASSWORD)
            portBindings = listOf("$TEST_DATABASE_PORT:3306")
            withReuse(true)
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


    @BeforeEach
    fun cleanDataBase() {
        println("Cleaning database before test")
        val connection: Connection = DriverManager.getConnection(
            mysqlContainer.jdbcUrl,
            mysqlContainer.username,
            mysqlContainer.password
        )

        connection.use {
            val stmt = connection.createStatement()
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0;")
            val tables = stmt.executeQuery(
                "SELECT table_name FROM information_schema.tables WHERE table_schema = '$TEST_DATABASE_NAME';"
            )

            val truncateStatements = mutableListOf<String>()
            while (tables.next()) {
                truncateStatements.add("TRUNCATE TABLE ${tables.getString(1)};")
            }

            truncateStatements.forEach { stmt.execute(it) }
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1;")
        }
    }

    override suspend fun afterSpec(spec: Spec) {
        println("Stopping MySQL container")
        mysqlContainer.stop()
    }
}