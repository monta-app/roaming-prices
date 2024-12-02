package com.monta.roamingPrices.domain

import jakarta.persistence.Column
import jakarta.persistence.Id
import java.util.UUID.randomUUID

data class ParsingError(
    @Id
    @Column(nullable = false, updatable = false, unique = true)
    val id: String = randomUUID().toString(),

    @Column(nullable = false)
    val Type: PARSE_ERROR_TYPE,

    @Column(nullable = false, unique = true)
    val tariffId: String,

    @Column(nullable = false, unique = true)
    val rawPayload: String,

    @Column(nullable = false, unique = true)
    val sessionId: String,

    @Column(nullable = false, unique = true)
    val protocol: PROTOCOL,

    @Column(nullable = false, unique = true)
    val message: String
)

enum class PARSE_ERROR_TYPE {
    TEXT,
    REGEX,
    OTHER
}