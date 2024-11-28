package com.monta.roamingPrices.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID.randomUUID
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn

@Entity
@Table(name = "pricing")
data class Pricing(
    @Id
    @Column(nullable = false, updatable = false, unique = true)
    val id: String = randomUUID().toString(),

    @Column(nullable = false)
    val tariffId: String,

    @Column(nullable = false, unique = true)
    val partyId: String,

    @Column(nullable = false, unique = true)
    val countryCode: String,


    @Column(nullable = false, unique = true)
    val vat: Float,


    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    val protocol: PROTOCOL,

    @Column(nullable = false, unique = true)
    val rawPayload: String,

    @OneToMany(cascade = [ALL], orphanRemoval = true)
    @JoinColumn(name = "pricing_id", nullable = false)
    val pricingComponents: MutableSet<PricingComponent> = mutableSetOf()
)