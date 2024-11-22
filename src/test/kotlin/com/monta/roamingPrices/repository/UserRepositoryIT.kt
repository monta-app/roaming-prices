package com.monta.roamingPrices.repository

import com.monta.roamingPrices.domain.User
import com.monta.roamingPrices.test.BaseIT
import io.kotest.matchers.shouldBe


class UserRepositoryIT(private val userRepository: UserRepository) : BaseIT() {
    @Test
    fun `should find user by name`() {
        val user = userRepository.save(
            User(
                name = "John",
                email = "john@example.com"
            )
        )
        val foundUser = userRepository.findByName("John")
        foundUser shouldBe user
    }
}