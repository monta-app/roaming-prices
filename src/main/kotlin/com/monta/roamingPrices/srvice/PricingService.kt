package com.monta.roamingPrices.srvice

import com.monta.roamingPrices.domain.PROTOCOL
import com.monta.roamingPrices.repository.PricingRepository
import jakarta.inject.Singleton

@Singleton
class PricingService(
    private val pricingRepository: PricingRepository
) {
    fun findByProtocol() = pricingRepository.findByProtocol(protocol = PROTOCOL.OCPI)
}