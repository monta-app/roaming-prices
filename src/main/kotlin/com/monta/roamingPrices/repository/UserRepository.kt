package com.monta.roamingPrices.repository

import com.monta.roamingPrices.domain.User
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@Repository
interface UserRepository : CrudRepository<User, UUID> {
    fun findByName(name:String): User?
}