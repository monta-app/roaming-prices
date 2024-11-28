package com.monta.roamingPrices.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn

@Entity
@Table(name = "pricing_component")
data class PricingComponent(
    @Id
    @Column(nullable = false, updatable = false, unique = true)
    val id: UUID = UUID.randomUUID(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: ComponentType,

    @Column(nullable = false)
    val amount: Float,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pricing_id", nullable = false)
    val pricing: Pricing
)
