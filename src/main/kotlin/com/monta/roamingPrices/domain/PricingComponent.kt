package com.monta.roamingPrices.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.util.UUID.randomUUID
import jakarta.persistence.Id

@Entity
@Table(name = "pricing_component")
data class PricingComponent(
    @Id
    @Column(nullable = false, updatable = false, unique = true)
    val id: String = randomUUID().toString(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: ComponentType,

    @Column(nullable = false)
    val amount: Float,
)
