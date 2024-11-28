package com.monta.roamingPrices.controller

import com.monta.roamingPrices.domain.PROTOCOL
import com.monta.roamingPrices.domain.Pricing
import com.monta.roamingPrices.srvice.PricingService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/pricing")
class PricingController(
    private val userService: PricingService
) {

    @Get("/{id}")
    fun findById(id: String): Pricing {
        val pricing = userService.findByProtocol()

        return pricing ?: Pricing(
            tariffId = "TARIFF_ID",
            partyId = "PARTY_ID",
            countryCode = "COUNTRY_CODE",
            vat = 0.0f,
            protocol = PROTOCOL.OCPI,
            rawPayload = "RAW_PAYLOAD"
        )
    }
}