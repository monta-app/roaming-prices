package com.monta.roamingPrices.domain

import jakarta.persistence.CascadeType.PERSIST
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID.randomUUID

@Entity
@Table(name = "evse_tariff_mapping")
data class EVSETariffMapping(
    @Id
    @Column(nullable = false, updatable = false, unique = true)
    val id: String = randomUUID().toString(),

    @Column(name = "evse_emi3", nullable = true)
    val evseEmi3: String? = null,

    @Column(name = "connector_id", nullable = true)
    val connectorId: String? = null,

    @Column(name = "uid", nullable = true)
    val uid: String? = null,

    @Column(name = "party_id", nullable = false)
    val partyId: String,

    @Column(name = "country_code", nullable = false)
    val countryCode: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "protocol", nullable = false)
    val protocol: PROTOCOL,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [PERSIST])
    @JoinColumn(name = "pricing_id", nullable = false)
    val pricing: Pricing
)