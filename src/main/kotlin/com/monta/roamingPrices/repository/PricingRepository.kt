package com.monta.roamingPrices.repository

import com.monta.roamingPrices.domain.PROTOCOL
import com.monta.roamingPrices.domain.Pricing
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface PricingRepository : JpaRepository<Pricing, String> {

    fun findByProtocol(protocol: PROTOCOL): Pricing?
}