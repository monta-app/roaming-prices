import org.testcontainers.containers.MySQLContainer

object MySQLTestContainer {
    val instance: MySQLContainer<Nothing> by lazy {
        MySQLContainer<Nothing>("mysql:8.0").apply {
            withDatabaseName("testdb")
            withUsername("test")
            withPassword("test")
            start()
        }
    }
}