package com.monta.roamingPrices

import com.monta.roamingPrices.domain.ComponentType
import com.monta.roamingPrices.domain.ComponentType.KWH
import com.monta.roamingPrices.domain.ComponentType.MIN
import com.monta.roamingPrices.domain.EVSETariffMapping
import com.monta.roamingPrices.domain.PROTOCOL
import com.monta.roamingPrices.domain.Pricing
import com.monta.roamingPrices.domain.PricingComponent


fun dummyEVSETariffMapping(
    evseEmi3: String = "evseEmi3",
    partyId: String = "partyId",
    countryCode: String = "countryCode",
    protocol: PROTOCOL = PROTOCOL.OCPI,
    pricing: Pricing = dummyPricing()
) = EVSETariffMapping(
    evseEmi3 = evseEmi3,
    partyId = partyId,
    countryCode = countryCode,
    protocol = protocol,
    pricing = pricing
)


fun dummyPriceComponent(
    type: ComponentType = KWH,
    amount: Float = 10.0f
) = PricingComponent(
    type = type,
    amount = amount
)

fun dummyPricing(
    tariffId: String = "TARIFF_ID",
    partyId: String = "TCB",
    countryCode: String = "FR",
    vat: Float = 0.0f,
    protocol: PROTOCOL = PROTOCOL.OCPI,
    rawPayload: String = "RAW_PAYLOAD",
    pricingComponents: MutableSet<PricingComponent> = mutableSetOf(
        dummyPriceComponent(),
        dummyPriceComponent(type = MIN, amount = 5.0f)
    )
) = Pricing(
    tariffId = tariffId,
    partyId = partyId,
    countryCode = countryCode,
    vat = vat,
    protocol = protocol,
    rawPayload = rawPayload,
    pricingComponents = pricingComponents
)

