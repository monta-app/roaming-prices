package com.monta.roamingPrices.srvice

import com.monta.roamingPrices.repository.UserRepository
import jakarta.inject.Singleton

@Singleton
class UserService(
    private val userRepository: UserRepository
) {
    fun findByName(email: String) = userRepository.findByName(email)
}