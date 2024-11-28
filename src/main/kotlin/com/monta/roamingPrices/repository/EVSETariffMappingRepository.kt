package com.monta.roamingPrices.repository

import com.monta.roamingPrices.domain.EVSETariffMapping
import com.monta.roamingPrices.domain.PROTOCOL
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface EVSETariffMappingRepository : JpaRepository<EVSETariffMapping, String> {
    fun findByProtocol(protocol: PROTOCOL): List<EVSETariffMapping>
}