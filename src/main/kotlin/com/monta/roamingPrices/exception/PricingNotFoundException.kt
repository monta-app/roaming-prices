package com.monta.roamingPrices.exception

class PricingNotFoundException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)