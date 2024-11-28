import org.testcontainers.containers.MySQLContainer

object MySQLTestContainer {
    val instance: MySQLContainer<Nothing> by lazy {
        println("Starting MySQL container")
        MySQLContainer<Nothing>("mysql:8.0").apply {
            withDatabaseName("testdb")
            withUsername("test")
            withPassword("test")
            start()
            println("=================================================================================")
            println("MySQL container running at: ${instance.jdbcUrl}")
        }
    }
}