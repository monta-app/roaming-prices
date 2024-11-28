package com.monta.roamingPrices.repository

import com.monta.roamingPrices.domain.EVSETariffMapping
import com.monta.roamingPrices.domain.PROTOCOL.OCPI
import com.monta.roamingPrices.dummyEVSETariffMapping
import com.monta.roamingPrices.dummyPricing
import com.monta.roamingPrices.test.BaseIT
import io.kotest.matchers.collections.shouldContainExactly
import jakarta.inject.Inject

class EVSETariffMappingRepositoryIT : BaseIT() {
    @Inject
    lateinit var evseTariffMappingRepository: EVSETariffMappingRepository

    @Test
    fun `can find and save EVSE tariff mapping`() {
        val savedTariffMapping = evseTariffMappingRepository.save(
            dummyEVSETariffMapping(pricing = dummyPricing())
        )

        val foundTariffMapping = evseTariffMappingRepository.findByProtocol(OCPI)

        foundTariffMapping shouldContainExactly listOf(savedTariffMapping)
    }
}