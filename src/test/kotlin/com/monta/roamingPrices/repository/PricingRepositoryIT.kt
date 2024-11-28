package com.monta.roamingPrices.repository

import com.monta.roamingPrices.domain.ComponentType.KWH
import com.monta.roamingPrices.domain.ComponentType.MIN
import com.monta.roamingPrices.domain.PROTOCOL.OCPI
import com.monta.roamingPrices.domain.PROTOCOL.OICP
import com.monta.roamingPrices.domain.PricingComponent
import com.monta.roamingPrices.dummyPricing
import com.monta.roamingPrices.test.BaseIT
import io.kotest.matchers.shouldBe
import jakarta.inject.Inject


class PricingRepositoryIT : BaseIT() {

    @Inject
    lateinit var pricingRepository: PricingRepository

    @Test
    fun `save and find pricing by PROTOCOL`() {
        val ocpiPricing = pricingRepository.save(dummyPricing(protocol = OCPI))
        val oicpPricing = pricingRepository.save(dummyPricing(protocol = OICP))
        val foundPricing = pricingRepository.findByProtocol(protocol = OCPI)
        foundPricing shouldBe ocpiPricing
    }

    @Test
    fun `save and find pricing containing components`() {
        val pricing = dummyPricing(
            pricingComponents = mutableSetOf(
                PricingComponent(type = KWH, amount = 10.0f),
                PricingComponent(type = MIN, amount = 5.0f)
            )
        )
        val savedPricing = pricingRepository.save(pricing)

        val foundPricing = pricingRepository.findByProtocol(protocol = OCPI)

        foundPricing shouldBe savedPricing
        foundPricing?.pricingComponents?.size shouldBe 2
    }
}