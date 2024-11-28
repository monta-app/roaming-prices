package com.monta.roamingPrices.repository

import com.monta.roamingPrices.domain.ComponentType.KWH
import com.monta.roamingPrices.domain.ComponentType.MIN
import com.monta.roamingPrices.domain.PROTOCOL
import com.monta.roamingPrices.domain.Pricing
import com.monta.roamingPrices.domain.PricingComponent
import com.monta.roamingPrices.test.BaseIT
import io.kotest.matchers.shouldBe
import jakarta.inject.Inject


class PricingRepositoryIT : BaseIT() {

    @Inject
    lateinit var pricingRepository: PricingRepository

    @Test
    fun `save and find pricing by PROTOCOL`() {
        val pricing = pricingRepository.save(
            Pricing(
                tariffId = "TARIFF_ID",
                partyId = "PARTY_ID",
                countryCode = "COUNTRY_CODE",
                vat = 0.0f,
                protocol = PROTOCOL.OCPI,
                rawPayload = "RAW_PAYLOAD"
            )
        )
        val foundPricing = pricingRepository.findByProtocol(PROTOCOL.OCPI)
        foundPricing shouldBe pricing
    }

    @Test
    fun `save and find pricing with components`() {
        val pricing = Pricing(
            tariffId = "TARIFF_ID",
            partyId = "PARTY_ID",
            countryCode = "COUNTRY_CODE",
            vat = 0.0f,
            protocol = PROTOCOL.OCPI,
            rawPayload = "RAW_PAYLOAD",
            pricingComponents = mutableSetOf(
                PricingComponent(
                    type = KWH,
                    amount = 10.0f
                ),
                PricingComponent(
                    type = MIN,
                    amount = 5.0f
                )
            )
        )
        val savedPricing = pricingRepository.save(pricing)
        val foundPricing = pricingRepository.findByProtocol(PROTOCOL.OCPI)

        foundPricing shouldBe savedPricing
        foundPricing?.pricingComponents?.size shouldBe 2
    }
}