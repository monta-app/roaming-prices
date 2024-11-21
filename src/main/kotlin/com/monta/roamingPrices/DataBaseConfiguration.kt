package com.monta.roamingPrices

import io.micronaut.context.annotation.ConfigurationProperties
import org.jetbrains.annotations.NotNull

@ConfigurationProperties("datasources.default")
class DatabaseConfiguration {

    @NotNull
    lateinit var jdbcUrl: String

    @NotNull
    lateinit var username: String

    @NotNull
    lateinit var password: String

    var minimumIdle: Int? = null

    var maximumPoolSize: Int? = null

    var driverClassName: String? = null
}