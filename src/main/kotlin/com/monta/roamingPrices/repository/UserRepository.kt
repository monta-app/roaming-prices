package com.monta.roamingPrices.repository

import com.monta.roamingPrices.domain.User
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByName(name:String): User?
}