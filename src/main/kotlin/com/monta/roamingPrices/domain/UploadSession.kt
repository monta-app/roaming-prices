package com.monta.roamingPrices.domain

import jakarta.persistence.Column
import jakarta.persistence.Id
import java.util.UUID.randomUUID

data class UploadSession(
    @Id
    @Column(nullable = false, updatable = false, unique = true)
    val id: String = randomUUID().toString(),

    @Column(nullable = false)
    val countryCode: String,

    @Column(nullable = false, unique = true)
    val partyId: String,

    @Column(nullable = false, unique = true)
    val protocol: PROTOCOL,

    @Column(nullable = false, unique = true)
    val totalRows: Int,

    @Column(nullable = false, unique = true)
    val failedRows: Int,

)
